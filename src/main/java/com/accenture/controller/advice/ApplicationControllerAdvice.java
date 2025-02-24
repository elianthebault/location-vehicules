package com.accenture.controller.advice;

import com.accenture.exception.UtilisateurException;
import com.accenture.exception.VehiculeException;
import com.accenture.shared.ResponseError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(UtilisateurException.class)
    public ResponseEntity<ResponseError> utilisateurExveptionManagement(UtilisateurException utilisateurException) {
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Functional error" ,utilisateurException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(VehiculeException.class)
    public ResponseEntity<ResponseError> vehiculeExveptionManagement(VehiculeException vehiculeException) {
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Functional error" ,vehiculeException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> utilisateurExceptionManagement(EntityNotFoundException entityNotFoundException) {
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Bad request" ,entityNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> utilisateurExceptionManagement(IllegalArgumentException illegalArgumentException) {
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Bad request" ,illegalArgumentException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> invalidMethodArgument(MethodArgumentNotValidException methodArgumentNotValidException) {
        String msg = methodArgumentNotValidException.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ResponseError responseError = new ResponseError(LocalDateTime.now(), "Argument invalide" , msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}
