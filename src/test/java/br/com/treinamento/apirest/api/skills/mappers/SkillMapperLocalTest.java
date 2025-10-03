package br.com.treinamento.apirest.api.skills.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import br.com.treinamento.apirest.testutils.factories.SkillFactory;
import br.com.treinamento.apirest.testutils.factories.SkillResponseFactory;

public class SkillMapperLocalTest {
    private SkillMapperLocal skillMapper;

    @BeforeEach
    void setUp() {
        skillMapper = new SkillMapperLocal();
    }

    @Test
    @Tags({@Tag("mapper"), @Tag("fast")})
    @DisplayName("toResponse() should convert a Skill into a SkillResponse")
    void toResponse_shouldConvertSkillToSkillResponse_whenSkillIsProvided() {
        // Arrange
        var skill = SkillFactory.createJava();
        var expectedSkillResponse = SkillResponseFactory.createJava();
        
        // Act
        var actual = skillMapper.toResponse(skill);

        // Assert
        assertNotNull(actual);
        assertEquals(expectedSkillResponse.getId(), actual.getId());
        assertEquals(expectedSkillResponse.getName(), actual.getName());
    }

    @Test
    @Tags({@Tag("mapper"), @Tag("fast")})
    @DisplayName("toResponse() should convert throw IllegalArgumentException when skill is null")
    void toResponse_shouldThrowException_whenSkillNull() {
        // Act / Assertion
        var exception = assertThrows(IllegalArgumentException.class, () -> skillMapper.toResponse(null));
        assertEquals("skill cannot be null", exception.getMessage());
    }

}
