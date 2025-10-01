package br.com.treinamento.apirest.core.service.auth;

import java.util.UUID;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.treinamento.apirest.core.repositories.JobRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final JobRepository jobRepository;

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated(){
        var authentication = getAuthentication();
        return authentication != null 
            && authentication.isAuthenticated() 
            && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public AuthenticatedUser getAuthenticatedUser(){
        if (!isAuthenticated()){
            throw new IllegalStateException("User is not authenticated");
        }

        var authentication = getAuthentication();
        if (!(authentication.getPrincipal() instanceof AuthenticatedUser)){
            throw new IllegalStateException("Principal is not of type AuthenticatedUser");
        }
        return (AuthenticatedUser) authentication.getPrincipal();
    }

    public boolean isCompanyOwnerOfJob(UUID jobId){
        return isAuthenticated() &&
            jobRepository.existsByIdAndCompanyId(jobId, getAuthenticatedUser().getId());
    }
    
}