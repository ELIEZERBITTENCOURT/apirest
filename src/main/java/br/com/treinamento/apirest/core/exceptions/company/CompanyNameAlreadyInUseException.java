package br.com.treinamento.apirest.core.exceptions.company;

import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;

public class CompanyNameAlreadyInUseException extends ModelAlreadyExistsException {

    public CompanyNameAlreadyInUseException() {
        super("Company name already in use");
    }
}