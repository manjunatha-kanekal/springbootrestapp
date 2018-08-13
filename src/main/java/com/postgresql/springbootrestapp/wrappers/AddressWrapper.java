package com.postgresql.springbootrestapp.wrappers;

import com.postgresql.springbootrestapp.model.Address;

import java.util.List;

public class AddressWrapper {
    private List<Address> address;

    public AddressWrapper() {}

    public AddressWrapper(List<Address> address) {
        this.address = address;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
