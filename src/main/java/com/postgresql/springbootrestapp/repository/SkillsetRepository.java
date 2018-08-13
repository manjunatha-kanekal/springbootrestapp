package com.postgresql.springbootrestapp.repository;

import com.postgresql.springbootrestapp.model.Skillset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsetRepository extends JpaRepository<Skillset, Long> {
    List<Skillset> findByEmployeeId(String employeeId);
}
