package br.com.treinamento.apirest.testutils.factories;

import java.util.UUID;

import br.com.treinamento.apirest.api.candidates.dtos.CandidateResponse;

public class CandidateResponseFactory {
    public static CandidateResponse createJohnDoe() {
        return CandidateResponse.builder()
            .id(UUID.fromString("f4b3b8b1-22f0-4f3c-8b58-560f7e5f3a46"))
            .name("John Doe")
            .description("Fullstack Developer")
            .email("john@mail.com")
            .linkedin("john-doe")
            .github("john-doe")
            .build();
    }
    
    public static CandidateResponse createJaneDoe() {
        return CandidateResponse.builder()
            .id(UUID.fromString("f4b3b8b1-22f0-4f3c-8b58-560f7e5f3a41"))
            .name("Jane Doe")
            .description("Backend Developer")
            .email("jane@mail.com")
            .linkedin("jane-doe")
            .github("jane-doe")
            .build();
    }
}
