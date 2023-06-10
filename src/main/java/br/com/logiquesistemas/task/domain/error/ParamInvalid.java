package br.com.logiquesistemas.task.domain.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParamInvalid extends IllegalArgumentException{
    
    public ParamInvalid() {
        super("Parâmetro inválido!");
    }

    public ParamInvalid(String message) {
        super(message);
    }

}
