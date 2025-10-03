package br.com.treinamento.apirest.libraries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import com.jayway.jsonpath.JsonPath;

public class JSONTest {
    @Test
    void testSomething() throws JSONException {
        var actual = simulateAPICall();
        var expected = """
            [
                {
                    "title": "Estudar Java",
                    "id": 1,
                    "completed": false
                },
                {
                    "id": 2,
                    "completed": true,
                    "title": "Estudar Spring"
                }
            ]
        """;

        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void testSomething2() {
        var actual = simulateAPICall();

        var title = JsonPath.read(actual, "$[0].title");
        assertEquals("Estudar Java", title);

        var length = JsonPath.read(actual, "$.length()");
        assertEquals(2, length);
    }

    private String simulateAPICall() {
        return """
            [
                {
                    "id": 1,
                    "title": "Estudar Java",
                    "completed": false
                },
                {
                    "id": 2,
                    "title": "Estudar Spring",
                    "completed": true
                }
            ]
        """;
    }
}
