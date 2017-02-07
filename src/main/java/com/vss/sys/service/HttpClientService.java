package com.vss.sys.service;

import java.io.IOException;

/**
 * Created by dujunliang on 17/1/13.
 */
public interface HttpClientService {

    public abstract String httpBody(String pkey) throws IOException;

    public abstract String getDesc(String body);

}
