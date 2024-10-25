package com.example.ruleEngine.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class RuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleString;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ASTNodeEntity> nodes;

    public RuleEntity() {
    }

    public RuleEntity(String ruleString, List<ASTNodeEntity> nodes) {
        this.ruleString = ruleString;
        this.nodes = nodes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

    public List<ASTNodeEntity> getNodes() {
        return nodes;
    }

    public void setNodes(List<ASTNodeEntity> nodes) {
        this.nodes = nodes;
    }
}
