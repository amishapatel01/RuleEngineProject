package com.example.ruleEngine.service;

import com.example.ruleEngine.entity.RuleEntity;
import com.example.ruleEngine.repository.RuleRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public RuleEntity createRule(String ruleString) {
        RuleEntity rule = new RuleEntity();
        rule.setRuleString(ruleString);
        return ruleRepository.save(rule);
    }
   

    public RuleEntity combineRules(List<Long> ruleIds) {
        List<RuleEntity> rules = ruleRepository.findAllById(ruleIds);

        StringBuilder combinedRuleString = new StringBuilder();
        for (RuleEntity rule : rules) {
            if (combinedRuleString.length() > 0) {
                combinedRuleString.append(" OR ");
            }
            combinedRuleString.append("(").append(rule.getRuleString()).append(")");
        }

        RuleEntity combinedRule = new RuleEntity();
        combinedRule.setRuleString(combinedRuleString.toString());

        return ruleRepository.save(combinedRule);
    }


    public boolean evaluateRule(Long ruleId, Map<String, Object> userData) {
        Optional<RuleEntity> optionalRule = ruleRepository.findById(ruleId);

        if (!optionalRule.isPresent()) {
            throw new ResourceNotFoundException("Rule not found with ID: " + ruleId);
        }

        RuleEntity rule = optionalRule.get();
        String ruleString = rule.getRuleString();

        // Replace user data keys with their values in the rule string
        for (Map.Entry<String, Object> entry : userData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Replace the key with its value
            if (value instanceof String) {
                // Escape single quotes for string comparison in JavaScript
                ruleString = ruleString.replace(key, "'" + value + "'");
            } else {
                ruleString = ruleString.replace(key, value.toString());
            }
        }

        return true;
    }

   public List<RuleEntity> getRulesByIds(List<Long> ruleIds) {

        return ruleRepository.findAllById(ruleIds);
    }

    public boolean existsById(Long ruleId) {

        return true;
    }


}
