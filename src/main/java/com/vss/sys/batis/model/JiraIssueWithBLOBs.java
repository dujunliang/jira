package com.vss.sys.batis.model;

public class JiraIssueWithBLOBs extends JiraIssue {
    private String description;

    private String environment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment == null ? null : environment.trim();
    }
}