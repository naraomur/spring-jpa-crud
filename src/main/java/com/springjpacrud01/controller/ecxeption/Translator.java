package com.springjpacrud01.controller.ecxeption;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

/*
for the messages of exception handler to be multilingual
 */
public class Translator {

    private ResourceBundle resourceBundle;
    private String text;
    public String getMsg(String lang) {
        resourceBundle = ResourceBundle.getBundle("texts");

        if(resourceBundle.containsKey(lang)){
            text = resourceBundle.getString(lang);
            if(lang.equals("ru") || lang.equals("ky")){
                text = new String(text.getBytes(StandardCharsets.UTF_8));
            }
        }
        return text;
    }
}
