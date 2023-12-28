package com.application.marathonplanner.web;

public class Runner {
    private int skillLevel;

    public Runner(int skillLevel) {
        setSkillLevel(skillLevel);
    }

    private void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getSkillLevel() {
        return this.skillLevel;
    }
}
