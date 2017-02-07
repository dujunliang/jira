package com.vss.sys.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Created by dujunliang on 17/1/16.
 */
public abstract class HttpMothod extends Http {

    public String response;

    public HttpMothod(String url) {
        super(url);
    }
    public void executeMethod(HttpClient client, PostMethod postMethod) {
        try {
            client.executeMethod(postMethod);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public void getResponseInfo(PostMethod postMethod) {
        try {
            int code = postMethod.getStatusCode();
            if (code == HttpStatus.SC_OK) {
                this.response = new String(postMethod.getResponseBodyAsString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
