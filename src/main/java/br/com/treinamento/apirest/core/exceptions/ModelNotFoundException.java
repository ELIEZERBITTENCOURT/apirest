package br.com.treinamento.apirest.core.exceptions;

public class ModelNotFoundException extends RuntimeException {
    
    public ModelNotFoundException(String message) {
        super(message);
    }
    
}
