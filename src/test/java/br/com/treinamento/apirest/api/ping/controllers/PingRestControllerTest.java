package br.com.treinamento.apirest.api.ping.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import br.com.treinamento.apirest.E2ETestCommon;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingRestControllerTest extends E2ETestCommon {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tags({@Tag("controller"), @Tag("slow")})
    @DisplayName("when GET to /api/ping then return 200")
    void whenGetToApiPingThenReturns200() throws Exception {
        var requestBuilder = get("/api/ping");
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.message").value("pong"))
            .andDo(print());
    }

}
