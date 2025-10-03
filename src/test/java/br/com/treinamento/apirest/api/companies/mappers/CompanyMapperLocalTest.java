package br.com.treinamento.apirest.api.companies.mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import br.com.treinamento.apirest.testutils.factories.CompanyFactory;
import br.com.treinamento.apirest.testutils.factories.CompanyRequestFactory;
import br.com.treinamento.apirest.testutils.factories.CompanyResponseFactory;

public class CompanyMapperLocalTest {

    private CompanyMapperLocal companyMapper;

    @BeforeEach
    void setUp() {
        companyMapper = new CompanyMapperLocal();
    }

    @Test
    @Tags({@Tag("mapper"), @Tag("fast")})
    @DisplayName("toResponse() should convert a Company into a CompanyResponse")
    void toResponse_shouldConvertCompanyIntoCompanyResponse_whenCompanyIsProvided() {
        // Arrange
        var company = CompanyFactory.createTreinamentoWeb();
        var expectedCompanyResponse = CompanyResponseFactory.createTreinamentoWeb();

        // Act
        var actual = companyMapper.toResponse(company);

        // Assert
        assertNotNull(actual);
        assertEquals(expectedCompanyResponse.getId(), actual.getId());
        assertEquals(expectedCompanyResponse.getName(), actual.getName());
        assertEquals(expectedCompanyResponse.getWebsite(), actual.getWebsite());
        assertEquals(expectedCompanyResponse.getDescription(), actual.getDescription());
        assertEquals(expectedCompanyResponse.getEmail(), actual.getEmail());
    }
    
    @Test
    @Tags({@Tag("mapper"), @Tag("fast")})
    @DisplayName("toResponse() should throw IllegalArgumentException when company is null")
    void toResponse_shouldThrowException_whenComanyIsNull() {
        // Act / Assert
        assertThrows(IllegalArgumentException.class, () -> companyMapper.toResponse(null));
    }

    @Test
    @Tags({@Tag("mapper"), @Tag("fast")})
    @DisplayName("toModel() should convert a CompanyRequest into a Company")
    void toModel_shouldConvertCompanyRequestIntoCompany_whenCompanyRequestIsProvided() {
        // Arrange
        var companyRequest = CompanyRequestFactory.createTreinamentoWeb();
        var expectedCompany = CompanyFactory.createTreinamentoWeb();

        // Act
        var actual = companyMapper.toModel(companyRequest);

        // Assert
        assertNotNull(actual);
        assertEquals(expectedCompany.getName(), actual.getName());
        assertEquals(expectedCompany.getWebsite(), actual.getWebsite());
        assertEquals(expectedCompany.getDescription(), actual.getDescription());
        assertEquals(expectedCompany.getEmail(), actual.getEmail());
        assertEquals(expectedCompany.getPassword(), actual.getPassword());
    }

    @Test
    @Tags({@Tag("mapper"), @Tag("fast")})
    @DisplayName("toModel() should throw IllegalArgumentException when companyRequest is null")
    void toModel_shouldThrowException_whenComanyRequestIsNull() {
        // Act / Assert
        assertThrows(IllegalArgumentException.class, () -> companyMapper.toModel(null));
    }

}
