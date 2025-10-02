package br.com.treinamento.apirest.testutils.factories;

import br.com.treinamento.apirest.api.companies.dtos.CompanyRequest;

public class CompanyRequestFactory {
    public static CompanyRequest createTreinamentoWeb() {
        return CompanyRequest.builder()
            .name("TreinamentoWeb")
            .website("https://www.treinamentoweb.com.br")
            .description("Empresa especializada em cursos de programação")
            .email("contato@treinamentoweb.com.br")
            .password("senha@123")
            .build();
    }

    public static CompanyRequest createAVMakers() {
        return CompanyRequest.builder()
            .name("AVMakers")
            .website("https://www.avmakers.com.br")
            .description("Empresa especializada em cursos de áudio visual")
            .email("contato@avmakers.com.br")
            .password("senha@123")
            .build();
    }
}
