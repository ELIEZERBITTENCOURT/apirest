package br.com.treinamento.apirest.core.exceptions;

public class ModelAlreadyExistsException extends RuntimeException {
    
    public ModelAlreadyExistsException(String message) {
        super(message);
    }
}