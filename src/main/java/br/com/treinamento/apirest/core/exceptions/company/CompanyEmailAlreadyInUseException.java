package br.com.treinamento.apirest.core.exceptions.company;

import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;

public class CompanyEmailAlreadyInUseException extends ModelAlreadyExistsException {

    public CompanyEmailAlreadyInUseException() {
        super("Company email already in use");
    }
}