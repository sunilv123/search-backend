package com.assignment.controller;

import com.assignment.exception.SearchException;
import com.assignment.response.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public GenericResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        logger.error("etStackTrace {}:" ,e);
        return new GenericResponse(HttpStatus.BAD_REQUEST.value(), error!=null?error.getDefaultMessage():null);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public GenericResponse methodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        logger.error("getStackTrace {}:" ,ex);
        return new GenericResponse(HttpStatus.BAD_REQUEST.value(),  String.format("'%s' should be a valid '%s' and '%s' isn't",
                ex.getName(), null,  ex.getValue()));
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = java.lang.IllegalArgumentException.class)
    public GenericResponse illegalArgumentException(final java.lang.IllegalArgumentException e) {
        logger.error("getStackTrace {} ",e.getMessage());
        //e.printStackTrace();
        return new GenericResponse(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public GenericResponse missingServletRequestParameterException(final MissingServletRequestParameterException e) {
        //e.printStackTrace();
        logger.error("Error {}: ",e.getMessage());
        return new GenericResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = com.fasterxml.jackson.databind.exc.InvalidFormatException.class)
    public GenericResponse invalidFormatException(final com.fasterxml.jackson.databind.exc.InvalidFormatException e) {
        //e.printStackTrace();
        logger.error("Error {}:" ,e);
        return new GenericResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.OK)
    public final GenericResponse handleAccessDeniedException(JsonProcessingException ex) {
        logger.error("Error {}:" ,ex);
        return new GenericResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = SearchException.class)
  public GenericResponse searchException(final Exception e) {
    //e.printStackTrace();
    logger.error("getStackTrace {}:" ,e);
    return new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Oops, something went wrong");
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public GenericResponse handleException(final Exception e) {
    //e.printStackTrace();
    logger.error("getStackTrace {}:" ,e);
    return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Oops, something went wrong");
  }


}
