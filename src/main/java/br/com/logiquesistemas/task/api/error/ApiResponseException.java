package br.com.logiquesistemas.task.api.error;

public class ApiResponseException extends RuntimeException {

    private final Exception error;

    public ApiResponseException(Exception e) {
        super(e);
        this.error = e;
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }
    
}
