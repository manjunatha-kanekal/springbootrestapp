package com.postgresql.springbootrestapp.controller;

import com.postgresql.springbootrestapp.exception.ResourceNotFoundException;
import com.postgresql.springbootrestapp.model.Employee;
import com.postgresql.springbootrestapp.repository.DepartmentRepository;
import com.postgresql.springbootrestapp.repository.EmployeeRepository;
import com.postgresql.springbootrestapp.util.Constants;
import com.postgresql.springbootrestapp.wrappers.EmployeeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable String employeeId) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if(emp.isPresent()) {
            return emp.get();
        } else {
            throw new ResourceNotFoundException("Employee not found with the code " + employeeId);
        }
    }

    @GetMapping("/employees")
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @GetMapping("/employees/department/{departmentId}")
    public List<Employee> getByDepartmentId(@PathVariable Long departmentId) {
        if(! departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with the Id " + departmentId);
        }

        return employeeRepository.findByDepartmentId(departmentId);
    }

    @PostMapping("/employees/department/{departmentId}")
    public Employee createEmployee(@PathVariable Long departmentId, @Valid @RequestBody Employee employee) {
        return departmentRepository.findById(departmentId)
                .map(department -> {
                    employee.setDepartment(department);
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with the Id " + departmentId));
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId, @Valid @RequestBody Employee employeeRequest) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employee.setDesignation(employeeRequest.getDesignation());
                    employee.setDateOfExit(employeeRequest.getDateOfExit());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the code " + employeeId));
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the code " + employeeId));
    }

    @GetMapping("/employees/skill/{skill}/rating/{minRating}")
    public EmployeeWrapper getEmployeesWithSkillAndRating(@PathVariable String skill, @PathVariable Optional<Integer> minRating) {
        int rating = Constants.DEFALUT_MIN_SKILL_RATING;
        if(minRating.isPresent()) {
            rating = minRating.get();
        }
        List<Employee> employees = employeeRepository.findBySkillsetsSkillIgnoreCaseContainingAndSkillsetsRatingGreaterThanEqual(skill, rating);
        return new EmployeeWrapper(employees);
    }
}
