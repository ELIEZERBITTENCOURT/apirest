package br.com.treinamento.apirest.testutils.factories;

import br.com.treinamento.apirest.api.candidates.dtos.CandidateRequest;

public class CandidateRequestFactory {
    public static CandidateRequest createJohnDoe() {
        return CandidateRequest.builder()
            .name("John Doe")
            .description("Fullstack Developer")
            .email("john@mail.com")
            .linkedin("john-doe")
            .github("john-doe")
            .password("senha@123")
            .build();
    }
    
    public static CandidateRequest createJaneDoe() {
        return CandidateRequest.builder()
            .name("Jane Doe")
            .description("Backend Developer")
            .email("jane@mail.com")
            .linkedin("jane-doe")
            .github("jane-doe")
            .password("senha@123")
            .build();
    }
}
