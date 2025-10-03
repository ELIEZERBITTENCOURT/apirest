package br.com.treinamento.apirest.api.companies.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treinamento.apirest.E2ETestCommon;
import br.com.treinamento.apirest.testutils.factories.CompanyRequestFactory;
import br.com.treinamento.apirest.testutils.factories.CompanyResponseFactory;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/companies/companies-insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/companies/companies-delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class CompanyRestControllerTest extends E2ETestCommon {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when POST /api/companies with valid body then return 201")
    void whePostApiCompaniesWithValidBodyThenReturns201() throws Exception {
        var companyRequest = CompanyRequestFactory.createAVMakers();
        var expected = CompanyResponseFactory.createAVMakers();
        var requestBuilder = post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(companyRequest));
        mockMvc.perform(requestBuilder)
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(expected.getName()))
            .andExpect(jsonPath("$.website").value(expected.getWebsite()))
            .andExpect(jsonPath("$.description").value(expected.getDescription()))
            .andExpect(jsonPath("$.email").value(expected.getEmail()));
    }

}
