package com.noice.xxxx.lobbies.app.resources.lobbies.v1.controllers;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.noice.xxxx.lobbies.app.execeptions.ErrorDetails;
import com.noice.xxxx.lobbies.app.execeptions.LobbyException;

@ControllerAdvice	
@RestController
public class AppErrorController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String msg = (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? msg: exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    
    @ExceptionHandler(LobbyException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(LobbyException ex, WebRequest request) {
      ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
          request.getDescription(false), ex.getCode());
      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}