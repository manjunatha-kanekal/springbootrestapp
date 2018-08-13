package com.postgresql.springbootrestapp.repository;

import com.postgresql.springbootrestapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findBySkillsetsSkillIgnoreCaseContainingAndSkillsetsRatingGreaterThanEqual(String skill, Integer rating);
}
