package br.com.logiquesistemas.task.api.error;

import java.time.ZonedDateTime;


public record ApiException(
    String message, 
    int status, 
    ZonedDateTime timestamp
) {
    
}
