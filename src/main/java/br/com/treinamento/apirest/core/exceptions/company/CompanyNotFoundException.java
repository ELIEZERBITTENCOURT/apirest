package br.com.treinamento.apirest.core.exceptions.company;

import br.com.treinamento.apirest.core.exceptions.ModelNotFoundException;

public class CompanyNotFoundException extends ModelNotFoundException {

    public CompanyNotFoundException() {
        super("Company not found");
    }
}