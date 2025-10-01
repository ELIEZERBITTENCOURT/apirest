package br.com.treinamento.apirest.api.companies.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {

    private UUID id;
    private String name;
    private String website;
    private String description;
    private String email;
}