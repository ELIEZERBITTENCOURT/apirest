package br.com.treinamento.apirest.libraries;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class HamcrestTest {
    @Test
    void testSomething() {
        var actual = "TEST";
        assertThat(actual, equalTo("TEST"));
        assertThat(actual, startsWith("T"));
        assertThat(actual, endsWith("T"));
        assertThat(actual, containsString("ES"));
    }
}
