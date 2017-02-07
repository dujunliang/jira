package com.vss.sys.http;

import com.vss.sys.param.SysParams;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by dujunliang on 17/1/16.
 */
public abstract class Http {

    //请求连接
    public String url;

    public Http(String url){
            this.url =  SysParams.httpurl+url;
    }

    public NameValuePair[] requestParams(){
        NameValuePair[] pair = {
                new NameValuePair("os_username",SysParams.username),
                new NameValuePair("os_password",SysParams.password)
        };
        return pair;
    }

    public void releaseConnection(PostMethod postMethod){
        postMethod.releaseConnection();
    }






}
