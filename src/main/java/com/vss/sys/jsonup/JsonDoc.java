package com.vss.sys.jsonup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by dujunliang on 17/1/16.
 */
public abstract class JsonDoc {

    public Document document;

    public String key;

    public String type="class";

    public JsonDoc(String body){
        this.document = paseDoc(body);
    }

    public JsonDoc(String body,String key){
        this.document = paseDoc(body);
        this.key = key;
    }

    public Document paseDoc(String body){
        return Jsoup.parse(body);
    }

}
