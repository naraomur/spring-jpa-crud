package com.springjpacrud01.ecxeption;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Response {
    //to convert the timestamp to a normal time format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date time;
    private String msg;
    private String bugMsg;

    public Response() {
        time = new Date();
    }






}
