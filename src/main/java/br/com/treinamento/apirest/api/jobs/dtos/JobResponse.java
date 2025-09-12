package br.com.treinamento.apirest.api.jobs.dtos;

import java.math.BigDecimal;

import br.com.treinamento.apirest.core.enums.JobLevel;
import br.com.treinamento.apirest.core.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    
    private Long id;
    private String title;
    private String description;
    private String company;
    private String location;
    private JobType type;
    private JobLevel level;
    private BigDecimal salary;

}
