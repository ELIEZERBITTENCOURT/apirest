package br.com.treinamento.apirest.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.apirest.core.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    
}
