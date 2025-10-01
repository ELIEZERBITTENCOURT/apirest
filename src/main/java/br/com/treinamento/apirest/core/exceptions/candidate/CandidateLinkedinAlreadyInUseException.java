package br.com.treinamento.apirest.core.exceptions.candidate;

import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;

public class CandidateLinkedinAlreadyInUseException extends ModelAlreadyExistsException {

    public CandidateLinkedinAlreadyInUseException() {
        super("Candidate LinkedIn already in use");
    }
}