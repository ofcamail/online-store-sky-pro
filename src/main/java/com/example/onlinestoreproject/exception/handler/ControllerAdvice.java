package com.example.onlinestoreproject.exception.handler;

import com.example.onlinestoreproject.exception.NotFoundCategory;
import com.example.onlinestoreproject.exception.NotFoundProduct;
import com.example.onlinestoreproject.exception.NotIllegalArgument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({NotFoundCategory.class,NotFoundProduct.class})
    public ResponseEntity<?> notFound(){
        return ResponseEntity.notFound().build();
    }
//    @ExceptionHandler()
//    public ResponseEntity<?> notFound(){
//        return ResponseEntity.notFound().build();
//    }
    @ExceptionHandler(NotIllegalArgument.class)
    public ResponseEntity<?> notCorrect() {
        return ResponseEntity.badRequest().build();
    }
}
