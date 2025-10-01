package br.com.treinamento.apirest.core.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface JobsPermissions {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("isAuthenticated()")
    public @interface IsAuthenticated {
        
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAuthority('COMPANY')")
    public @interface IsCompany {
        
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public @interface IsCandidate {
        
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("""
        hasAuthority('COMPANY') and
        @securityService.isCompanyOwnerOfJob(#id)
    """)
    public @interface IsCompanyOwnerOfJob {
        
    }

}