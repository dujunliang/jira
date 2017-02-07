package com.vss.sys.batis.mapper;

import com.vss.sys.batis.model.JiraIssue;
import com.vss.sys.batis.model.JiraIssueWithBLOBs;

public interface JiraIssueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JiraIssueWithBLOBs record);

    int insertSelective(JiraIssueWithBLOBs record);

    JiraIssueWithBLOBs selectByPrimaryKey(Long id);

    JiraIssueWithBLOBs selectByPKey(String pkey);

    int updateByPrimaryKeySelective(JiraIssueWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JiraIssueWithBLOBs record);

    int updateByPrimaryKey(JiraIssue record);
}