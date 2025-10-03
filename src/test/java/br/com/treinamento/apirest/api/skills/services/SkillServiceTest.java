package br.com.treinamento.apirest.api.skills.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.treinamento.apirest.api.skills.mappers.SkillMapper;
import br.com.treinamento.apirest.core.exceptions.skill.SkillNotFoundException;
import br.com.treinamento.apirest.core.repositories.SkillRepository;
import br.com.treinamento.apirest.testutils.factories.SkillFactory;
import br.com.treinamento.apirest.testutils.factories.SkillResponseFactory;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {
     @Mock
    private SkillMapper skillMapper;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    @Tags({@Tag("service"), @Tag("fast")})
    @DisplayName("findById() should return a SkillResponse when valid id is provided")
    void findById_shouldReturnSkillResponse_whenValidId() {
        // Arrange
        var skill = SkillFactory.createJava();
        var expectedSkillResponse = SkillResponseFactory.createJava();

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        when(skillMapper.toResponse(skill)).thenReturn(expectedSkillResponse);

        // Act
        var actual = skillService.findById(1L);

        // Assert
        assertNotNull(actual);
        assertEquals(expectedSkillResponse.getId(), actual.getId());
        assertEquals(expectedSkillResponse.getName(), actual.getName());
    }
    
    @Test
    @Tags({@Tag("service"), @Tag("fast")})
    @DisplayName("findById() should throw SkillNotFoundException when invalid id")
    void findById_shouldThrowsException_whenInvalidId() {
        // Arrange
        when(skillRepository.findById(1L)).thenReturn(Optional.empty());

        // Act / Assert
        assertThrows(SkillNotFoundException.class, () -> skillService.findById(1L));
    }

    @Test
    @Tags({@Tag("service"), @Tag("fast")})
    @DisplayName("findAll() should return a list of SkillResponse")
    void findAll_shouldReturnListOfSkillResponse_whenCalled() {
        // Arrange
        var skills = SkillFactory.createSkills();
        var expectedSkillsResponse = SkillResponseFactory.createSkills();
        var sort = Sort.by(Direction.ASC, "name");

        when(skillRepository.findAll(sort)).thenReturn(skills);
        when(skillMapper.toResponse(skills.get(0))).thenReturn(expectedSkillsResponse.get(0));
        when(skillMapper.toResponse(skills.get(1))).thenReturn(expectedSkillsResponse.get(1));

        // Act
        var actual = skillService.findAll(sort);

        // Assert
        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals(expectedSkillsResponse.get(0).getId(), actual.get(0).getId());
        assertEquals(expectedSkillsResponse.get(0).getName(), actual.get(0).getName());
        assertEquals(expectedSkillsResponse.get(1).getId(), actual.get(1).getId());
        assertEquals(expectedSkillsResponse.get(1).getName(), actual.get(1).getName());
    }

}
