package com.example.ruleEngine.entity;

import jakarta.persistence.*;

@Entity
public class ASTNodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String value;

    @ManyToOne
    private ASTNodeEntity left;

    @ManyToOne
    private ASTNodeEntity right;


    public ASTNodeEntity() {
    }


    public ASTNodeEntity(String type, String value, ASTNodeEntity left, ASTNodeEntity right) {
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ASTNodeEntity getLeft() {
        return left;
    }

    public void setLeft(ASTNodeEntity left) {
        this.left = left;
    }

    public ASTNodeEntity getRight() {
        return right;
    }

    public void setRight(ASTNodeEntity right) {
        this.right = right;
    }
}
