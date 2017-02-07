package com.vss.sys.auto;

import com.vss.sys.batis.model.JiraIssueWithBLOBs;
import com.vss.sys.param.SysParams;
import com.vss.sys.service.FileService;
import com.vss.sys.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dujunliang on 17/1/17.
 */
@Component
public class FileProcess {

    @Autowired
    protected FileService fileService;

    @Autowired
    protected HttpClientService httpClientService;

    public String path;

    public String sep = File.separator;




    public Boolean exitPath(String path) {
        return fileService.createFilePath(path);
    }
    /**
     * 獲取SQL系統時間
     * @return
     */
    public Date getdate() {

        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        return timestamp;

    }

    public String getCsvPath(){
        this.path = SysParams.path +sep+ SysParams.csvPath+sep;
        return path;
    }

    public String getWritePath(){
        this.path = SysParams.path +sep+ SysParams.writefile+sep;
        return path;
    }

    public String desc(String pkey) {
        String desc = "";
        try {
            if (pkey != null && pkey != "") {
                String body = null;
                body = httpClientService.httpBody(pkey);
                if (body != null && body != "") {
                    desc = httpClientService.getDesc(body);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desc;
    }

    public JiraIssueWithBLOBs contendInfo(String[] data) {

        JiraIssueWithBLOBs issue = new JiraIssueWithBLOBs();
        try {
            issue.setId(Long.valueOf(data[1]));
            issue.setPkey(data[10]);
            issue.setProject(SysParams.Project);
            issue.setReporter(data[3]);
            issue.setAssignee(data[4]);
            String type = data[7];
            issue.setIssuetype("3");
            issue.setSummary(data[2]);
            issue.setPriority(data[6].equals("中") ? "2" : data[6].equals("低") ? "3" : "1");
            issue.setResolution("1");
            issue.setEnvironment("");
            String status = data[5];
            issue.setIssuestatus(status.equals("已關閉") ? "1" : status.equals("新建") ? "2" : status.equals("解決中") ? "3" : status.equals("已解決") ? "5" : status.equals("已關閉") ? "6" : "0");
            issue.setCreated(getdate());
            issue.setDescription(data[11]);

        } catch (Exception e) {

        }
        return issue;

    }

    public String parsefilepath(String filePath){
        return filePath.replace("\\", "");
    }


}
