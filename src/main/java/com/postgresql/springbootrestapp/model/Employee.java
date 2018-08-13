package com.postgresql.springbootrestapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends Audit {

    @Id
    @GenericGenerator(name="seq_id", strategy="com.postgresql.springbootrestapp.id.EmployeeCodeGenerator")
    @GeneratedValue(generator="seq_id")
    @Column(name = "emp_code", unique = true, nullable = false, length = 20)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "doj")
    private Date dateOfJoining;

    @Temporal(TemporalType.DATE)
    @Column(name = "doe")
    private Date dateOfExit;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "department_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Department department;

    @NotBlank
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "employee" )
    @JsonManagedReference
    private List<Address> addresses;

    @OneToMany(mappedBy = "employee" )
    @JsonManagedReference
    private List<Skillset> skillsets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Date getDateOfExit() {
        return dateOfExit;
    }

    public void setDateOfExit(Date dateOfExit) {
        this.dateOfExit = dateOfExit;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Skillset> getSkillsets() {
        return skillsets;
    }

    public void setSkillsets(List<Skillset> skillsets) {
        this.skillsets = skillsets;
    }
}
