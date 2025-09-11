package br.com.treinamento.apirest.core.service.http;

import java.util.Optional;

public interface HttpService {

    <T> Optional<T> getPathVariable(String name, Class<T> type);
    
}
