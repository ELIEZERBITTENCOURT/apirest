# SpringFramework - Desenvolvimento de API REST

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar vagas de emprego (Jobs) e habilidades (Skills), utilizando MySQL como banco de dados.

## Funcionalidades

- Cadastro, listagem, atualização e remoção de vagas de emprego (`Job`)
- Cadastro, listagem, atualização e remoção de habilidades (`Skill`)
- Associação de habilidades a vagas de emprego
- Paginação e HATEOAS nos endpoints
- Validação de dados com mensagens detalhadas de erro
- Validação de unicidade para nomes de habilidades
- Tratamento centralizado de exceções

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Validation
- Spring HATEOAS
- ModelMapper
- Lombok
- MySQL

## Configuração

1. **Banco de Dados:**  
   Certifique-se de ter um banco MySQL rodando e configure as credenciais em `src/main/resources/application.properties`:

 ```sh
 spring.datasource.url=jdbc:mysql://localhost:3306/apirest 
 spring.datasource.username=root 
 spring.datasource.password=root
 ```

2. **Build e Execução:**  
Para compilar e rodar o projeto:

```sh
./mvnw spring-boot:run
```

3. **Testes:**
Para rodar os testes automatizados:

```sh
./mvnw test
```

4. **Endpoints Principais:**

`GET /api/jobs` — Lista vagas de emprego (com paginação)

`POST /api/jobs` — Cria uma nova vaga

`GET /api/jobs/{id}` — Detalha uma vaga

`PUT /api/jobs/{id}` — Atualiza uma vaga

`DELETE /api/jobs/{id}` — Remove uma vaga

`GET /api/jobs/{id}/skills` — Lista habilidades de uma vaga

`GET /api/skills` — Lista habilidades (com paginação)

`POST /api/skills` — Cria uma nova habilidade

`GET /api/skills/{id}` — Detalha uma habilidade

`PUT /api/skills/{id}` — Atualiza uma habilidade

`DELETE /api/skills/{id}` — Remove uma habilidade

### **Observações**

O projeto utiliza validação de dados e retorna mensagens amigáveis em caso de erro.
Os endpoints retornam links HATEOAS para facilitar a navegação pela API.
O nome da habilidade é único e validado automaticamente.
Desenvolvido para fins de estudo e treinamento com Spring Boot.
