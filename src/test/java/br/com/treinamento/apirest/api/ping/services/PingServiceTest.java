package br.com.treinamento.apirest.api.ping.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class PingServiceTest {
    
    @Test
    @Tags({@Tag("service"), @Tag("fast")})
    @DisplayName("ping() should return a map with 'message' key and 'pong' value")
    void ping_shouldReturnMessagePong_whenCalled() {
        // Arrange
        var pingService = new PingService();

        // Act
        var actual = pingService.ping();

        // Assert
        assertNotNull(actual);
        assertNotNull(actual.get("message"));
        assertEquals("pong", actual.get("message"));
    }

}
