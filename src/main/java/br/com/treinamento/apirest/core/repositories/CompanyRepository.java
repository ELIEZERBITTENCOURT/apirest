package br.com.treinamento.apirest.core.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.treinamento.apirest.core.models.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, UUID id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
    Optional<Company> findByEmail(String email);
}