package br.com.logiquesistemas.task.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.logiquesistemas.task.api.error.ApiException;
import br.com.logiquesistemas.task.api.error.ApiResponseException;
import br.com.logiquesistemas.task.domain.error.ParamInvalid;

@ControllerAdvice
public class TaskExceptionHandler {
    
    @ExceptionHandler(value = {ParamInvalid.class})
    public ResponseEntity<Object> x400(ApiResponseException e){
        var status = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
            e.getMessage(),
            status.value(),
            ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, status);
    }

}
