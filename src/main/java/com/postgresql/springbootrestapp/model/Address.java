package com.postgresql.springbootrestapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address extends Audit {
    @Id
    @GeneratedValue(generator = "address_gen")
    @SequenceGenerator(
            name = "address_gen",
            sequenceName = "address_seq"
    )
    private Long Id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "house_no")
    private String houseNumber;

    @Size(max = 200)
    @Column(name = "address_line2")
    private String addressLine2;

    @NotBlank
    @Size(max = 100)
    @Column(name = "street")
    private String street;

    @NotBlank
    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Size(max = 10)
    @Column(name = "pincode")
    private String pincode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AddressType addressType;

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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
