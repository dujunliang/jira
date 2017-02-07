package com.vss.sys.springmain;

import ch.qos.logback.core.joran.spi.JoranException;
import com.vss.sys.logs.LogBackConfigLoader;
import com.vss.sys.param.SysParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by dujunliang on 12/23/16.
 */
public class JiraStart {

    private static final Logger logger = LoggerFactory.getLogger(JiraStart.class);

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:jira-context.xml"});//spring
        try {
            loadfile();
//            URL url = Thread.currentThread().getContextClassLoader().getResource("logback-log.xml");//logback
//            LogBackConfigLoader.load(url.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        context.start();
        logger.info("服务启动成功!");
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }


    public static void loadfile() {

        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jira.properties");
            Properties p = new Properties();

            p.load(in);

            SysParams.csvPath = p.getProperty("jira.file");//文件读取路径

            SysParams.writefile = p.getProperty("jira.writefile");//文件读取路径

            SysParams.username = p.getProperty("jira.username");//用户名

            SysParams.password = p.getProperty("jira.password");//用户名密码

            SysParams.httpurl = p.getProperty("jira.httpurl");//请求URL

            SysParams.Project = Long.parseLong(p.getProperty("jira.Project"));//项目编号

            File directory = new File("");//设定为当前文件夹
            SysParams.sysPath = directory.getCanonicalPath();//获取标准的路径
            SysParams.path = SysParams.sysPath;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
