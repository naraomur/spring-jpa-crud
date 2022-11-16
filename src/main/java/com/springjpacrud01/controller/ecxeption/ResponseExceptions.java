package com.springjpacrud01.controller.ecxeption;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class ResponseExceptions extends ResponseEntityExceptionHandler {
    private Response response;
    private Translator translator = new Translator();
    private String lang;
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElement(HttpServletRequest req) {
        response = new Response(HttpStatus.NOT_FOUND);
        Locale locale = req.getLocale();
        lang = translator.getMsg(locale.getLanguage());
        //lang = translator.getMsg("ru");
        response.setMsg(req.getRequestURI() + " with " + req.getQueryString() + lang);
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
