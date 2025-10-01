package br.com.treinamento.apirest.core.exceptions.company;

import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;

public class CompanyNameAlreadyInUseException extends ModelAlreadyExistsException {

    public CompanyNameAlredyInUseException() {
        super("Company name already in use");
    }
}