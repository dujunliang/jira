package com.vss.sys.service;

import com.vss.sys.batis.model.JiraIssueWithBLOBs;

import java.util.List;

/**
 * Created by dujunliang on 17/1/11.
 */
public interface JiraIssueService {

    public Boolean save(List<JiraIssueWithBLOBs> jiraIssueList);

}
