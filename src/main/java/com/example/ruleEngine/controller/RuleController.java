package com.example.ruleEngine.controller;

import com.example.ruleEngine.entity.RuleEntity;
import com.example.ruleEngine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/create")
    public ResponseEntity<RuleEntity> createRule(@RequestBody Map<String, String> request) {
        String ruleString = request.get("ruleString");
        if (ruleString == null || ruleString.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Bad request if ruleString is missing
        }

        RuleEntity rule = ruleService.createRule(ruleString);
        return new ResponseEntity<>(rule, HttpStatus.CREATED);
    }


    @PostMapping("/combine")
    public ResponseEntity<RuleEntity> combineRules(@RequestBody List<Long> ruleIds) {
        if (ruleIds == null || ruleIds.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if ruleIds are empty
        }

        List<RuleEntity> rules = ruleService.getRulesByIds(ruleIds); // Check if this returns null
        if (rules == null || rules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if no rules found
        }

        RuleEntity combinedRule = ruleService.combineRules(ruleIds);
        return new ResponseEntity<>(combinedRule, HttpStatus.OK);
    }


    @PostMapping("/evaluate/{ruleId}")
    public ResponseEntity<Boolean> evaluateRule(@PathVariable Long ruleId, @RequestBody Map<String, Object> userData) {
        if (userData == null || userData.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if userData is empty
        }

     if (!ruleService.existsById(ruleId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if rule not found
        }

        boolean result = ruleService.evaluateRule(ruleId, userData);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
