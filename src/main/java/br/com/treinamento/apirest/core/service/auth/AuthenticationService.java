package br.com.treinamento.apirest.core.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.treinamento.apirest.core.repositories.CandidateRepository;
import br.com.treinamento.apirest.core.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
     private final CompanyRepository companyRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var company = companyRepository.findByEmail(username);
        if (company.isPresent()) {
            return AuthenticatedUser.builder()
                .id(company.get().getId())
                .email(company.get().getEmail())
                .password(company.get().getPassword())
                .role("COMPANY")
                .build();
        }

        var candidate = candidateRepository.findByEmail(username);
        if (candidate.isPresent()) {
            return AuthenticatedUser.builder()
                .id(candidate.get().getId())
                .email(candidate.get().getEmail())
                .password(candidate.get().getPassword())
                .role("CANDIDATE")
                .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
    
}