package br.com.logiquesistemas.task.domain.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFound extends Exception {
    
    public TaskNotFound(){
        super("Tarefa não encontrada!");
    }

    public TaskNotFound(String message){
        super(message);
    }

}
