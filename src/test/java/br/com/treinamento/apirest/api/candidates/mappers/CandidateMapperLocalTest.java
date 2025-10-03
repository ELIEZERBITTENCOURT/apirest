package br.com.treinamento.apirest.api.candidates.mappers;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CandidateMapperLocalTest {
    
    @Test
    void toResponse() {
        var mapper = new CandidateMapperLocal();
        assertThrows(IllegalArgumentException.class, () -> mapper.toResponse(null));
    }
    
    @Test
    void toModel() {
        var mapper = new CandidateMapperLocal();
        assertThrows(IllegalArgumentException.class, () -> mapper.toModel(null));
    }

}
