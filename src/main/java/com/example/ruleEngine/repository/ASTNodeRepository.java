package com.example.ruleEngine.repository;

import com.example.ruleEngine.entity.ASTNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ASTNodeRepository extends JpaRepository<ASTNodeEntity, Long> {
}

