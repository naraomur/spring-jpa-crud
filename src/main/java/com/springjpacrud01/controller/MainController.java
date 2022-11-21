package com.springjpacrud01.controller;

import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping(value = "main")
public class MainController {
    public static final MediaType mediaType=MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client;
    private static final String baseUrl = "http://localhost:8080/";

    @RequestMapping(value = "/post-get", method = RequestMethod.POST)
    public String main(@RequestParam("empId") Long empId, @RequestParam("depId") Long depId) throws IOException{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl +"employee/"+empId+"/department/"+depId).newBuilder();
        String url = urlBuilder.build().toString();
        return postDepToEmployee(url)+"\r\n"+getDepById(depId);
    }

    public static String postDepToEmployee(String url) throws IOException {
        client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static String getDepById(Long id) throws IOException{
        //created the client
        client = new OkHttpClient().newBuilder()
                .build();
        //got the Url
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(baseUrl+"department/id").newBuilder();
        urlBuilder.addQueryParameter("id", id.toString());
        String url = urlBuilder.build().toString();
        //request
        Request request = new Request.Builder()
                .url(url)
                .build();
        //response
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
