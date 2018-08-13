package com.postgresql.springbootrestapp.controller;

import com.postgresql.springbootrestapp.exception.ResourceNotFoundException;
import com.postgresql.springbootrestapp.model.Department;
import com.postgresql.springbootrestapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public Page<Department> getDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @PostMapping("/departments")
    public Department createDepartment(@Valid @RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/department/{departmentId}")
    public Department updateDepartment(@PathVariable Long departmentId, @Valid @RequestBody Department departmentRequest) {
        return departmentRepository.findById(departmentId)
                .map(department -> {
                    department.setDepartmentName(departmentRequest.getDepartmentName());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with the code " + departmentId));
    }
}
