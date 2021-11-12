package com.teciopgrid.ecommerceprice.controller;

import com.teciopgrid.ecommerceprice.exception.NotFoundEcommerceEntityException;
import com.teciopgrid.ecommerceprice.exception.NotFoundEcommercePriceCriteriaDtoException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalErrorHandler {

    private static final String MESSAGE = "message";
    private static final String STATUS_CODE = "status-code";
    private static final String URI = "uri";
    private static final String METHOD = "method";


    @ExceptionHandler({NotFoundEcommercePriceCriteriaDtoException.class})
    public ResponseEntity<Map<String, String>> handlerNotFoundCriteriaError(final HttpServletRequest request,
                                                                            final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(METHOD, request.getMethod(),
                        URI, request.getRequestURI(),
                        MESSAGE, Objects.requireNonNull(e.getMessage()),
                        STATUS_CODE, HttpStatus.BAD_REQUEST.toString()
                ));
    }

    @ExceptionHandler({NotFoundEcommerceEntityException.class})
    public ResponseEntity<Map<String, String>> handlerNotFoundResultError(final HttpServletRequest request,
                                                                          final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Map.of(METHOD, request.getMethod(),
                        URI, request.getRequestURI(),
                        MESSAGE, Objects.requireNonNull(e.getMessage()),
                        STATUS_CODE, HttpStatus.NO_CONTENT.toString()
                ));
    }

    @ExceptionHandler({DataAccessResourceFailureException.class, RuntimeException.class})
    public ResponseEntity<Map<String, String>> handlerInternalServerError(final HttpServletRequest request,
                                                                          final RuntimeException e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(METHOD, request.getMethod(),
                        URI, request.getRequestURI(),
                        MESSAGE, Objects.requireNonNull(e.getMessage()),
                        STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.toString()
                ));
    }


}
