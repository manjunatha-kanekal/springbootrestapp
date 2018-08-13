package com.postgresql.springbootrestapp.wrappers;

import com.postgresql.springbootrestapp.model.Employee;

import java.util.List;

public class EmployeeWrapper {
    private List<Employee> employees;

    public EmployeeWrapper() {}

    public EmployeeWrapper(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
