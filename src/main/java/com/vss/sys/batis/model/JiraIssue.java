package com.vss.sys.batis.model;

import java.util.Date;

public class JiraIssue {
    private Long id;

    private String pkey;

    private Long project;

    private String reporter;

    private String assignee;

    private String issuetype;

    private String summary;

    private String priority;

    private String resolution;

    private String issuestatus;

    private Date created;

    private Date updated;

    private Date duedate;

    private Date resolutiondate;

    private Long votes;

    private Long watches;

    private Long timeoriginalestimate;

    private Long timeestimate;

    private Long timespent;

    private Long workflowId;

    private Long security;

    private Long fixfor;

    private Long component;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey == null ? null : pkey.trim();
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter == null ? null : reporter.trim();
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee == null ? null : assignee.trim();
    }

    public String getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype == null ? null : issuetype.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    public String getIssuestatus() {
        return issuestatus;
    }

    public void setIssuestatus(String issuestatus) {
        this.issuestatus = issuestatus == null ? null : issuestatus.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getResolutiondate() {
        return resolutiondate;
    }

    public void setResolutiondate(Date resolutiondate) {
        this.resolutiondate = resolutiondate;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getWatches() {
        return watches;
    }

    public void setWatches(Long watches) {
        this.watches = watches;
    }

    public Long getTimeoriginalestimate() {
        return timeoriginalestimate;
    }

    public void setTimeoriginalestimate(Long timeoriginalestimate) {
        this.timeoriginalestimate = timeoriginalestimate;
    }

    public Long getTimeestimate() {
        return timeestimate;
    }

    public void setTimeestimate(Long timeestimate) {
        this.timeestimate = timeestimate;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Long getSecurity() {
        return security;
    }

    public void setSecurity(Long security) {
        this.security = security;
    }

    public Long getFixfor() {
        return fixfor;
    }

    public void setFixfor(Long fixfor) {
        this.fixfor = fixfor;
    }

    public Long getComponent() {
        return component;
    }

    public void setComponent(Long component) {
        this.component = component;
    }
}