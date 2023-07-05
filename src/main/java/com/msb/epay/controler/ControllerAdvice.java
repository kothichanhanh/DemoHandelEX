package com.msb.epay.controler;

import com.msb.epay.Dto.ResponeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponeDto<String>> handleMethodArgumentTypeMismatch(
            ValidationException ex, WebRequest request) {
        String error =
                ex.getMessage();
        ResponeDto<String> responeDto = new ResponeDto<String>();
        responeDto.setCode("400");
        responeDto.setMesg("Validation error");
        responeDto.setData(error);
        return new ResponseEntity<>(responeDto, HttpStatus.BAD_REQUEST);
    }


}
