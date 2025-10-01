package br.com.treinamento.apirest.core.exceptions.candidate;

import br.com.treinamento.apirest.core.exceptions.ModelNotFoundException;

public class CandidateNotFoundException extends ModelNotFoundException {
    
    public CandidateNotFoundException() {
        super("Candidate not found");
    }
    
}