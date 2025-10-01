package br.com.treinamento.apirest.core.exceptions.candidate;

import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;

public class CandidateGithubAlreadyInUseException extends ModelAlreadyExistsException {

    public CandidateGithubAlreadyInUseException() {
        super("Candidate GitHub already in use");
    }
}