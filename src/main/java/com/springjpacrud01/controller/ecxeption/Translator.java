package com.springjpacrud01.controller.ecxeption;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/*
for the messages of exception handler to be multilingual
 */
public class Translator {

    private static ResourceBundle resourceBundle;
    private String text;
    public String getStringNotFound(String language, String requestURI, String queryString) {
        resourceBundle = ResourceBundle.getBundle("texts");
            if(resourceBundle.containsKey(language)){
                resourceBundle = ResourceBundle.getBundle("texts_"+language);
            text = resourceBundle.getString(language);
            if(language.equals("ru") || language.equals("ky")){
                text = new String(text.getBytes(StandardCharsets.UTF_8));
            }
        }
        return MessageFormat.format(text,requestURI, queryString);
    }
    public String getStringMistype(String language, String queryString) {
        resourceBundle = ResourceBundle.getBundle("mistype");
        if(resourceBundle.containsKey(language)){
            resourceBundle = ResourceBundle.getBundle("mistype_"+language);
            text = resourceBundle.getString(language);
            if(language.equals("ru") || language.equals("ky")){
                text = new String(text.getBytes(StandardCharsets.UTF_8));
            }
        }
        return MessageFormat.format(text, queryString);
    }

    public String getStringNullPointer(String language, String queryString) {
        resourceBundle = ResourceBundle.getBundle("nullpointer");
        if(resourceBundle.containsKey(language)){
            resourceBundle = ResourceBundle.getBundle("nullpointer_"+language);
            text = resourceBundle.getString(language);
            if(language.equals("ru") || language.equals("ky")){
                text = new String(text.getBytes(StandardCharsets.UTF_8));
            }
        }
        return MessageFormat.format(text, queryString);
    }

}
