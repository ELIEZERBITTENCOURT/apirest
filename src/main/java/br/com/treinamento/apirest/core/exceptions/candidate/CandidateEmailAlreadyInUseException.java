package br.com.treinamento.apirest.core.exceptions.candidate;

import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;

public class CandidateEmailAlreadyInUseException extends ModelAlreadyExistsException {

    public CandidateEmailAlreadyInUseException() {
        super("Candidate email already in use");
    }
}