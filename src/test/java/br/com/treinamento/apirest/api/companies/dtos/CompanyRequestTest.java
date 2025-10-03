package br.com.treinamento.apirest.api.companies.dtos;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.treinamento.apirest.testutils.factories.CompanyRequestFactory;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class CompanyRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @Tags({@Tag("validate"), @Tag("fast")})
    @DisplayName("when validate CompanyRequest with valid data then no violations should be returned")
    void whenValidateValidCompanyRequest_thenNoErrors() {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();

        // Act
        var actual = validator.validate(companyRequest);

        // Assert
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "ab"})
    @MethodSource("moreThan255Chars")
    @Tags({@Tag("validate"), @Tag("fast")})
    @DisplayName("when validate CompanyRequest with invalid name violations should be returned")
    void whenValidateInvalidCompanyRequestName_thenErrors(String name) {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();
        companyRequest.setName(name);

        // Act
        var actual = validator.validate(companyRequest);

        // Assert
        assertFalse(actual.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "ab", "invalidwebsite", "http://www.treinamentoweb.com.br"})
    @MethodSource("moreThan255Chars")
    @Tags({@Tag("validate"), @Tag("fast")})
    @DisplayName("when validate CompanyRequest with invalid website violations should be returned")
    void whenValidateInvalidCompanyRequestWebsite_thenErrors(String website) {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();
        companyRequest.setWebsite(website);

        // Act
        var actual = validator.validate(companyRequest);

        // Assert
        assertFalse(actual.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "ab"})
    @MethodSource("moreThan255Chars")
    @Tags({@Tag("validate"), @Tag("fast")})
    @DisplayName("when validate CompanyRequest with invalid description violations should be returned")
    void whenValidateInvalidCompanyRequestDescription_thenErrors(String description) {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();
        companyRequest.setDescription(description);

        // Act
        var actual = validator.validate(companyRequest);

        // Assert
        assertFalse(actual.isEmpty());
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "ab", "invalidemail"})
    @MethodSource("moreThan255Chars")
    @Tags({@Tag("validate"), @Tag("fast")})
    @DisplayName("when validate CompanyRequest with invalid email violations should be returned")
    void whenValidateInvalidCompanyRequestEmail_thenErrors(String email) {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();
        companyRequest.setEmail(email);

        // Act
        var actual = validator.validate(companyRequest);

        // Assert
        assertFalse(actual.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "ab"})
    @MethodSource("moreThan255Chars")
    @Tags({@Tag("validate"), @Tag("fast")})
    @DisplayName("when validate CompanyRequest with invalid password violations should be returned")
    void whenValidateInvalidCompanyRequestPassword_thenErrors(String password) {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();
        companyRequest.setPassword(password);

        // Act
        var actual = validator.validate(companyRequest);

        // Assert
        assertFalse(actual.isEmpty());
    }

    static Stream<String> moreThan255Chars() {
        return Stream.of("a".repeat(300));
    }

}
