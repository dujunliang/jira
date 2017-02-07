package com.vss.sys.service.impl;

import com.vss.sys.batis.mapper.JiraIssueMapper;
import com.vss.sys.batis.model.JiraIssueWithBLOBs;
import com.vss.sys.service.JiraIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dujunliang on 17/1/11.
 */
@Transactional
@Service
public class JiraIssueServiceImpl implements JiraIssueService {

    private static final Logger logger = LoggerFactory.getLogger(JiraIssueServiceImpl.class);

    @Autowired
    public JiraIssueMapper jiraIssueMapper;



    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Boolean save(List<JiraIssueWithBLOBs> jiraIssueList)  {

        logger.info("數據開始導入");
        if(jiraIssueList.size()>0 || jiraIssueList != null){
            for (JiraIssueWithBLOBs jiraIssue : jiraIssueList){
                if(jiraIssueMapper.selectByPKey(jiraIssue.getPkey()) == null) {
                    if(jiraIssueMapper.selectByPrimaryKey(jiraIssue.getId()) == null){
                        if (jiraIssueMapper.insert(jiraIssue) < 1) {
                            return false;
                        }
                    }else{
                        logger.info(jiraIssue.getPkey()+" pid 已存在 issueId:"+jiraIssue.getId());
                    }
                }else{

                    logger.info(jiraIssue.getPkey()+" pkey 已存在 issueId:"+jiraIssue.getPkey());
                }
            }
        }
        logger.info("數據導入結束");
        return true;
    }


}
