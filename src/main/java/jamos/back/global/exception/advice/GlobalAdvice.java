package jamos.back.global.exception.advice;

import jamos.back.global.exception.ErrorResult;
import jamos.back.global.exception.SameLoginIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(SameLoginIdException.class)
    public ErrorResult sameLoginId(SameLoginIdException e){
        return new ErrorResult("SAME_ID", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        return new ErrorResult("BAD_ARGUMENT", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        return new ErrorResult("internal", "Internal");
    }
}
