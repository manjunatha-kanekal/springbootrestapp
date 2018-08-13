package com.postgresql.springbootrestapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "skillset")
public class Skillset extends Audit {
    @Id
    @GeneratedValue(generator = "skill_gen")
    @SequenceGenerator(
            name = "skill_gen",
            sequenceName = "skill_seq"
    )
    private Long Id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "skill")
    private String skill;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "endorsed", columnDefinition = "boolean default false")
    private Boolean endorsed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emp_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Employee employee;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getEndorsed() {
        return endorsed;
    }

    public void setEndorsed(Boolean endorsed) {
        this.endorsed = endorsed;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

