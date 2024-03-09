package com.kpo.restaurantsystem.web.controlleradvice;

import com.kpo.restaurantsystem.web.controlleradvice.interfaces.InterfaceControllerAdvice;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
public class ControllerAdvice implements InterfaceControllerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    @Override
    public ResponseEntity<String> handleIllegalState(final IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @Override
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Override
    public String handleConstraintViolation(ConstraintViolationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Override
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body("Valid error: " + e.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @Override
    public ResponseEntity<String> handleInvalidFormat(InvalidFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @Override
    public ResponseEntity<String> handleUndeclaredThrowable(UndeclaredThrowableException e) {
        var cause = e.getCause().getCause();
        if (cause instanceof IllegalArgumentException) {
            return handleIllegalArgument((IllegalArgumentException) cause);
        } else if (cause instanceof IllegalStateException) {
            return handleIllegalState((IllegalStateException) cause);
        } else {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @Override
    public String handleException(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
