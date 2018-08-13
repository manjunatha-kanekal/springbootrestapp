package com.postgresql.springbootrestapp.controller;

import com.postgresql.springbootrestapp.exception.ResourceNotFoundException;
import com.postgresql.springbootrestapp.model.Skillset;
import com.postgresql.springbootrestapp.repository.EmployeeRepository;
import com.postgresql.springbootrestapp.repository.SkillsetRepository;
import com.postgresql.springbootrestapp.wrappers.SkillsetWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SkillsetController {

    @Autowired
    private SkillsetRepository skillsetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees/{employeeId}/skillsets")
    public SkillsetWrapper getSkillsetByEmployeeId(@PathVariable String employeeId) {
        List<Skillset> skillsets = skillsetRepository.findByEmployeeId(employeeId);
        return new SkillsetWrapper(skillsets);
    }

    /*@PostMapping("/employees/{employeeId}/skillsets")
    public List<Skillset> addSkillset(@PathVariable String employeeId, @Valid @RequestBody ArrayList<Skillset> skillset) {
        //skillsetRepository.saveAll(skillset);
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    for (Skillset ss : skillset) {
                        ss.setEmployee(employee);
                    }
                    //skillset.setEmployee(employee);
                    return skillsetRepository.saveAll(skillset);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the code " + employeeId));
    }*/

    /* Changed with new ability. Multiple skills can be sent in a single request */
    @PostMapping("/employees/{employeeId}/skillsets")
    public List<Skillset> addSkillset(@PathVariable String employeeId, @Valid @RequestBody SkillsetWrapper skillsetWrapper) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    for (Skillset ss : skillsetWrapper.getSkillsets()) {
                        ss.setEmployee(employee);
                    }
                    return skillsetRepository.saveAll(skillsetWrapper.getSkillsets());
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the code " + employeeId));
    }

    @PutMapping("/employees/{employeeId}/skillsets/{skillsetId}")
    public Skillset updateSkillset(@PathVariable String employeeId, @PathVariable Long skillsetId, @Valid @RequestBody Skillset skillsetRequest) {
        if(! employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with the code " + employeeId);
        }

        return skillsetRepository.findById(skillsetId)
                .map(skillset -> {
                    skillset.setSkill(skillsetRequest.getSkill());
                    skillset.setRating(skillsetRequest.getRating());
                    skillset.setEndorsed(skillsetRequest.getEndorsed());
                    return skillsetRepository.save(skillset);
                }).orElseThrow(() -> new ResourceNotFoundException("Skillset not found with the id " + skillsetId));
    }

    @DeleteMapping("/employees/{employeeId}/skillsets/{skillsetId}")
    public ResponseEntity<?> deleteSkillset(@PathVariable String employeeId, @PathVariable Long skillsetId) {
        if(! employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with the code " + employeeId);
        }

        return skillsetRepository.findById(skillsetId)
                .map(skillset -> {
                    skillsetRepository.delete(skillset);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Skillset not found with the id " + skillsetId));
    }
}
