package br.com.treinamento.apirest.api.auth.controllers;

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
import br.com.treinamento.apirest.api.auth.dtos.LoginRequest;
import br.com.treinamento.apirest.core.service.jwt.JwtService;
import br.com.treinamento.apirest.testutils.factories.CompanyResponseFactory;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/companies/companies-insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/companies/companies-delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class AuthRestControllerTest extends E2ETestCommon {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when POST to /api/auth/login with valid login request then returns 200")
    void whenPostLoginWithValidBodyThenReturns200() throws Exception {
        var loginRequest = LoginRequest.builder()
            .email("contato@treinamentoweb.com.br")
            .password("senha@123")
            .build();

        var requestBuilder = post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest));
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.token").exists())
            .andExpect(jsonPath("$.token").isString());
    }
    
    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when POST to /api/auth/login with invalid email then returns 401")
    void whenPostLoginWithInvalidEmailThenReturns401() throws Exception {
        var loginRequest = LoginRequest.builder()
            .email("contato@avmakers.com.br")
            .password("senha@123")
            .build();

        var requestBuilder = post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest));
        mockMvc.perform(requestBuilder)
            .andExpect(status().isUnauthorized());
    }
    
    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when POST to /api/auth/login with invalid password then returns 401")
    void whenPostLoginWithInvalidPasswordThenReturns401() throws Exception {
        var loginRequest = LoginRequest.builder()
            .email("contato@treinamentoweb.com.br")
            .password("senha@1234")
            .build();

        var requestBuilder = post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest));
        mockMvc.perform(requestBuilder)
            .andExpect(status().isUnauthorized());
    }

    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when GET to /api/auth/me with valid company token then returns 200")
    void whenGetMeWithValidCompanyTokenThenReturns200() throws Exception {
        var expected = CompanyResponseFactory.createTreinamentoWeb();
        var token = jwtService.generateToken(expected.getEmail());

        var requestBuilder = get("/api/auth/me")
            .header("Authorization", "Bearer " + token);
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(expected.getName()))
            .andExpect(jsonPath("$.description").value(expected.getDescription()))
            .andExpect(jsonPath("$.email").value(expected.getEmail()))
            .andExpect(jsonPath("$.website").value(expected.getWebsite()));
    }
    
    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when GET to /api/auth/me with no token then returns 401")
    void whenGetMeWithNoTokenThenReturns401() throws Exception {
        var requestBuilder = get("/api/auth/me");
        mockMvc.perform(requestBuilder)
            .andExpect(status().isUnauthorized());
    }
    
    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when GET to /api/auth/me with invalid token then returns 401")
    void whenGetMeWithInvalidTokenThenReturns401() throws Exception {
        var requestBuilder = get("/api/auth/me")
            .header("Authorizarion", "Bearer invalid-token");
        mockMvc.perform(requestBuilder)
            .andExpect(status().isUnauthorized());
    }
    
    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when GET to /api/auth/me with valid token but no 'Bearer ' prefix then returns 401")
    void whenGetMeWithValidTokenButNoBearerThenReturns401() throws Exception {
        var expected = CompanyResponseFactory.createTreinamentoWeb();
        var token = jwtService.generateToken(expected.getEmail());

        var requestBuilder = get("/api/auth/me")
            .header("Authorization", token);
        mockMvc.perform(requestBuilder)
            .andExpect(status().isUnauthorized());
    }

}
