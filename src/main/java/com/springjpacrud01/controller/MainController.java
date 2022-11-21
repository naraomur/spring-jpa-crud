package com.springjpacrud01.controller;

import com.springjpacrud01.dto.TransferRequest;
import com.springjpacrud01.model.Employee;
import okhttp3.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.io.IOException;
@RestController
@RequestMapping(value = "main")
public class MainController {
    public static final MediaType mediaType=MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client;
    private static final String baseUrl = "http://localhost:8080/";

    @RequestMapping(value = "/post-get", method = RequestMethod.POST)
    public String main(@RequestBody TransferRequest request) throws IOException{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl +"employee/"+request.getEmId()+"/department/"+request.getDepId()).newBuilder();
        String url = urlBuilder.build().toString();
        return postDepToEmployee(url)+"\r\n"+getDepById(request.getDepId());
    }
    @PostMapping(value = "add-employee")
    public String postEmployee(@RequestBody Employee employee) throws IOException{

        client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.RequestBody body = okhttp3.RequestBody.create(String.valueOf(employee), mediaType);
        Request request = new Request.Builder()
                .url(baseUrl+"employee")
                .method("POST", body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string()+"\r\n" + getAllEmployees();
        }
    }

    public String getAllEmployees() throws IOException {
        client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(baseUrl+"employee/employees")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String postDepToEmployee(String url) throws IOException {
        client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.RequestBody body = okhttp3.RequestBody.create("", mediaType);
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
