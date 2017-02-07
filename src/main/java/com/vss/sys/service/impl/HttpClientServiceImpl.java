package com.vss.sys.service.impl;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vss.sys.http.HttpPostMethod;
import com.vss.sys.jsonup.DocElement;
import com.vss.sys.param.SysParams;
import com.vss.sys.service.HttpClientService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Created by dujunliang on 17/1/13.
 */
@Service
public class HttpClientServiceImpl implements HttpClientService {


    /***
     * 获取请求body
     *
     * @param pkey
     * @return
     * @throws IOException
     */
    public String httpBody(String pkey) throws IOException {

        HttpClient client = new HttpClient();
        HttpPostMethod post = new HttpPostMethod(pkey);
        //使用POST方法
        PostMethod postMethod = new PostMethod(post.url);
        /**
         * 使用POST方式提交数据
         */
        NameValuePair[] orderInfo = post.requestParams();
        postMethod.setRequestBody(orderInfo);//设置参数
        post.executeMethod(client, postMethod);//执行请求
        post.getResponseInfo(postMethod);//返回内容
        //释放连接
        post.releaseConnection(postMethod);

        return post.response;

    }

    /**
     * 解析html
     *
     * @param body
     * @return
     */
    @Override
    public String getDesc(String body) {

        String key = "user-content-block";

        DocElement doc = new DocElement(body, key);

        Elements ListDiv = doc.getElementstList();

        StringBuffer buffer = new StringBuffer();

        for (org.jsoup.nodes.Element element : ListDiv) {
            Elements elements = element.getElementsByTag(doc.tag[0]);
            for (org.jsoup.nodes.Element elementinfo : elements) {
                String elemtText = elementinfo.text().trim();
                buffer.append(elemtText);
                System.out.println(elemtText);
            }
        }

        return buffer.toString();

    }


}
