package br.com.treinamento.apirest.api.candidates.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {

    @NotBlank
    @Size(min=3, max= 255)
    private String name;
    
    @NotBlank
    @Size(min= 3, max= 255)
    private String description;

    @Email
    @NotBlank
    @Size(min=3, max= 255)
    private String email;

    @NotBlank
    @Size(min=6, max= 255)
    private String password;

    @NotBlank
    @Size(min=3, max= 255)
    private String linkedin;

    @NotBlank
    @Size(min=3, max= 255)
    private String github;
}
