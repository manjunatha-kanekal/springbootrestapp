package com.postgresql.springbootrestapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "department")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department extends Audit {

    @Id
    @GeneratedValue(generator = "dept_gen")
    @SequenceGenerator(
            name = "dept_gen",
            sequenceName = "dept_seq"
    )
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(name = "dept_name")
    private String departmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
