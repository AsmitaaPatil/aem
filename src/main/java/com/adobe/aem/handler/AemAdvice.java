package com.adobe.aem.handler;


import com.adobe.aem.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/***
 * Generic advice to handle all exceptions in the API.
 *
 */
@ControllerAdvice
public class AemAdvice extends ResponseEntityExceptionHandler {

    /***
     * Handles {@link ConstraintViolationException} that validates null and size constraints of input params
     *
     * @param ex
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolationExceptions(
            ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /***
     * Handles exceptions {@link Exception} unhandled by developers
     *
     * @param ex
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleRuntimeExceptions(
            Throwable ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("Something went wrong. Please try again later or contact support");
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}