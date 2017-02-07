package com.vss.sys.auto;

import com.vss.sys.batis.model.JiraIssueWithBLOBs;
import com.vss.sys.param.SysParams;
import com.vss.sys.service.FileService;
import com.vss.sys.service.HttpClientService;
import com.vss.sys.service.JiraIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * Created by dujunliang on 12/23/16.
 */
@Component
public class AutoWriteJob extends FileProcess {

    private static final Logger logger = LoggerFactory.getLogger(AutoWriteJob.class);

    @Autowired
    protected FileService fileService;

    @Autowired
    protected JiraIssueService jiraIssueService;


    public void run() {
        /**
         * 如果文件目錄不存在則不執行
         */
        String csvPath = getCsvPath();
        String wirtePath = getWritePath();
        logger.info("開始掃描文件目錄csvPath"+csvPath);
        logger.info("開始掃描文件目錄csvWritePath"+wirtePath);
        if (!exitPath(csvPath) && !exitPath(wirtePath)) {
            return;
        }

        List<File> filelist = fileService.readfile(csvPath);

        if (filelist != null) {
            /**
             * 查看該目錄是否存在需要入庫的文件
             */
            for (File file : filelist) {

                List<String[]> listeria = new ArrayList<String[]>();
                /**
                 * 单文件读取
                 */



                logger.info("開始讀取文件PATH:" + file.getPath());

                List<String[]> dalis = fileService.readFileByLines(file);

                Map map = new HashMap<String,String>();
                /**
                 * bean 信息組合
                 */
                for (String[] str : dalis) {
                    List<String> listA = Arrays.asList(str);
                    List<String> listB = new ArrayList<String>(listA);
                    String desc = desc(str[10]);
                    listB.add(11,desc);
                    String[] b = listB.toArray(new String[listB.size()]);

                    listeria.add(b);
                }

                if(fileService.writeCsv(file,wirtePath,listeria)){
                    fileService.deleteFile(file);
                }

                logger.info("結束寫入文件PATH:" + file.getPath());

            }


        }

    }







}
