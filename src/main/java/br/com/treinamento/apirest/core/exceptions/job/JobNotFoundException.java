package br.com.treinamento.apirest.core.exceptions.job;

import br.com.treinamento.apirest.core.exceptions.ModelNotFoundException;

public class JobNotFoundException extends ModelNotFoundException {
    
    public JobNotFoundException() {
        super("Job not found");
    }
    
}
