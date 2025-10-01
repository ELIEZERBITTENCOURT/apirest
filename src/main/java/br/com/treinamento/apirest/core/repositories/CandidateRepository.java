package br.com.treinamento.apirest.core.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.treinamento.apirest.core.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    boolean existsByEmail(String email);
    boolean existsByLinkedin(String linkedin);
    boolean existsByGithub(String github);
    Optional<Candidate> findByEmail(String email);
    
}