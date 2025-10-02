package br.com.treinamento.apirest;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@SuppressWarnings("resource")
public class E2ETestCommon {

    static final MySQLContainer<?> mysql;

    static {
        mysql = new MySQLContainer<>(DockerImageName.parse("mysql:9.2.0"))
            .withReuse(true);
        mysql.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }
    
}
