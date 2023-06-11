package br.com.logiquesistemas.task.domain.error;

public class ParamInvalid extends Exception{
    
    public ParamInvalid() {
        super("Parâmetro inválido!");
    }

    public ParamInvalid(String message) {
        super(message);
    }

}
