package com.postgresql.springbootrestapp.wrappers;

import com.postgresql.springbootrestapp.model.Skillset;

import java.util.List;

public class SkillsetWrapper {
    private List<Skillset> skillsets;

    public SkillsetWrapper() {}

    public SkillsetWrapper(List<Skillset> skillsets) {
        this.skillsets = skillsets;
    }

    public List<Skillset> getSkillsets() {
        return skillsets;
    }

    public void setSkillsets(List<Skillset> skillsets) {
        this.skillsets = skillsets;
    }
}
