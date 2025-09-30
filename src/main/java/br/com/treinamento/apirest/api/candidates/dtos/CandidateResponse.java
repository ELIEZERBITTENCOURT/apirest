package br.com.treinamento.apirest.api.candidates.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponse {
    private UUID id;
    private String name;
    private String description;
    private String email;
    private String linkedin;
    private String github;
}