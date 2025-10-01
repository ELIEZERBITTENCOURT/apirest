package br.com.treinamento.apirest.api.jobs.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    private UUID id;
    private String name;
    private String description;
    private String location;
    private JobType type;
    private JobLevel level;
    private BigDecimal salary;
    private List<String> skills;
    private UUID companyId;
    
}
