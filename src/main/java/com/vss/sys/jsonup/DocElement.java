package com.vss.sys.jsonup;

import org.jsoup.select.Elements;

/**
 * Created by dujunliang on 17/1/16.
 */
public class DocElement extends JsonDoc {

    public String[] tag = {"p","div","href"};

    public DocElement(String body,String key) {
        super(body,key);
    }

    public Elements getElementstList(){
        return document.getElementsByAttributeValue(type, key);
    }


}
