package com.postgresql.springbootrestapp.controller;

import com.postgresql.springbootrestapp.exception.ResourceNotFoundException;
import com.postgresql.springbootrestapp.model.Address;
import com.postgresql.springbootrestapp.repository.AddressRepository;
import com.postgresql.springbootrestapp.repository.EmployeeRepository;
import com.postgresql.springbootrestapp.wrappers.AddressWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees/{employeeId}/address")
    public AddressWrapper getAddressByEmployeeId(@PathVariable String employeeId) {
        List<Address> addresses = addressRepository.findByEmployeeId(employeeId);
        return new AddressWrapper(addresses);
    }

    /*@PostMapping("/employees/{employeeId}/address")
    public Address addAddress(@PathVariable String employeeId, @Valid @RequestBody Address address) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    address.setEmployee(employee);
                    return addressRepository.save(address);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the code " + employeeId));
    }*/

    /* Changed with new ability. Multiple address can be sent in a single request */
    @PostMapping("/employees/{employeeId}/address")
    public List<Address> addAddress(@PathVariable String employeeId, @Valid @RequestBody AddressWrapper addressWrapper) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    for (Address addr : addressWrapper.getAddress()) {
                        addr.setEmployee(employee);
                    }
                    return addressRepository.saveAll(addressWrapper.getAddress());
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the code " + employeeId));
    }

    @PutMapping("/employees/{employeeId}/address/{addressId}")
    public Address updateAddress(@PathVariable String employeeId, @PathVariable Long addressId, @Valid @RequestBody Address addressRequest) {
        if(! employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with the code " + employeeId);
        }

        return addressRepository.findById(addressId)
                .map(address -> {
                    address.setHouseNumber(addressRequest.getHouseNumber());
                    address.setAddressLine2(addressRequest.getAddressLine2());
                    address.setStreet(addressRequest.getStreet());
                    address.setCity(addressRequest.getCity());
                    address.setPincode(addressRequest.getPincode());
                    address.setAddressType(addressRequest.getAddressType());
                    return addressRepository.save(address);
                }).orElseThrow(() -> new ResourceNotFoundException("Address not found with the id " + addressId));
    }

    @DeleteMapping("/employees/{employeeId}/address/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable String employeeId, @PathVariable Long addressId) {
        if(! employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with the code " + employeeId);
        }

        return addressRepository.findById(addressId)
                .map(address -> {
                    addressRepository.delete(address);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Address not found with the id " + addressId));
    }
}
