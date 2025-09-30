package br.com.treinamento.apirest.api.auth.dtos;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class MeCompanyResponse implements MeResponse {
    private UUID id;
    private String name;
    private String website;
    private String description;
    private String email;

    @Builder.Default
    private String role = "COMPANY";
}