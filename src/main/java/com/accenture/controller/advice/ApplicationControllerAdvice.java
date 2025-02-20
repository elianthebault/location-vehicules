package com.accenture.controller.advice;

import com.accenture.exception.UtilisateurException;
import com.accenture.shared.ResponseError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(UtilisateurException.class)
    public ResponseEntity<ResponseError> utilisateurExveptionManagement(UtilisateurException utilisateurException) {
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Functional error" ,utilisateurException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> utilisateurExceptionManagement(EntityNotFoundException entityNotFoundException) {
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Bad request" ,entityNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }
}
