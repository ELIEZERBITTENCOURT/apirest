package br.com.treinamento.apirest.core.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.treinamento.apirest.core.enums.JobLevel;
import br.com.treinamento.apirest.core.enums.JobType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Job {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private JobType type;

    @Enumerated(EnumType.STRING)
    private JobLevel level;

    private BigDecimal salary;

    @ManyToMany
    private List<Skill> skills;

    @ManyToOne(optional = false)
    private Company company;

    @ManyToMany
    private List<Candidate> candidates;
    
}
