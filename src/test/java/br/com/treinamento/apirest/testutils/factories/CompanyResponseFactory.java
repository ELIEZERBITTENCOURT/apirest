package br.com.treinamento.apirest.testutils.factories;

import java.util.UUID;

import br.com.treinamento.apirest.api.companies.dtos.CompanyResponse;

public class CompanyResponseFactory {

     public static CompanyResponse createTreinamentoWeb() {
        return CompanyResponse.builder()
            .id(UUID.fromString("2ab0f478-26af-4cf3-a2ec-7be5102417f8"))
            .name("TreinamentoWeb")
            .website("https://www.treinamentoweb.com.br")
            .description("Empresa especializada em cursos de programação")
            .email("contato@treinamentoweb.com.br")
            .build();
    }

    public static CompanyResponse createAVMakers() {
        return CompanyResponse.builder()
            .id(UUID.fromString("928ff78f-058a-43d0-92da-a53996c19200"))
            .name("AVMakers")
            .website("https://www.avmakers.com.br")
            .description("Empresa especializada em cursos de áudio visual")
            .email("contato@avmakers.com.br")
            .build();
    }
}
