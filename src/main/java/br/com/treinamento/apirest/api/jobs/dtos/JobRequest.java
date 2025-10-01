package br.com.treinamento.apirest.api.jobs.dtos;

import java.math.BigDecimal;
import java.util.List;

import br.com.treinamento.apirest.core.enums.JobLevel;
import br.com.treinamento.apirest.core.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @NotBlank
    @Size(min = 5, max = 100)
    private String name;

    @NotBlank
    @Size(min = 10, max = 500)
    private String description;

    @NotBlank
    @Size(min = 5, max = 100)
    private String location;

    @NotNull
    private JobType type;

    @NotNull
    private JobLevel level;

    @Positive
    private BigDecimal salary;

    @NotEmpty
    private List<@NotBlank String> skills;
    
}