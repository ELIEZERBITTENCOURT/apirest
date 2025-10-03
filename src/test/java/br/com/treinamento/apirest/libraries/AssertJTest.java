package br.com.treinamento.apirest.libraries;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class AssertJTest {
    
    @Test
    void testSomething() {
        assertThat("TEST")
            .startsWith("T")
            .endsWith("T")
            .isEqualTo("TEST")
            .isEqualToIgnoringCase("test");
    }
}
