package com.springjpacrud01.controller.ecxeption;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class ResponseExceptions extends ResponseEntityExceptionHandler {
    private Response response;
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElement(HttpServletRequest req, NoSuchElementException e){
        response = new Response(HttpStatus.NOT_FOUND);
        response.setMsg(req.getRequestURI() + " with " + req.getQueryString() + " not found!");
        return buildResponse(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException mex){
        response = new Response(HttpStatus.OK);
        response.setMsg("Could not convert " + req.getQueryString() + " to required type 'number': 1, 2, 3...");
        return buildResponse(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(HttpServletRequest req, NullPointerException nex){
        response = new Response(HttpStatus.OK);
        response.setMsg("Entry can't be "+req.getQueryString()+ ". Enter valid key of type number for ID: 1, 2, 3...");
        return buildResponse(response);
    }

    private ResponseEntity<Object>  buildResponse(Response response) {
        return new ResponseEntity<>(response, response.getStatus());
    }

}
