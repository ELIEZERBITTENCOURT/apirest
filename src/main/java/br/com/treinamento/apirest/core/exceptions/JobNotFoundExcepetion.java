package br.com.treinamento.apirest.core.exceptions;

public class JobNotFoundExcepetion extends ModelNotFoundException {
    
    public JobNotFoundExcepetion() {
        super("Job not found");
    }

    public JobNotFoundExcepetion(String message) {
        super(message);
    }
    
}
