package br.gabriel.api.spring_mvc_api.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Clientes")
                        .description("""
                            API REST completa para gerenciamento de clientes seguindo o padrão MVC.

                            **Funcionalidades:**
                            - CRUD completo de clientes
                            - Validação de dados com Bean Validation
                            - Tratamento global de exceções
                            - Documentação interativa com Swagger UI

                            **Tecnologias utilizadas:**
                            - Spring Boot 3.5.0
                            - Spring Data JPA
                            - PostgreSQL
                            - Bean Validation
                            - Swagger/OpenAPI 3
                            """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gabriel Miranda")
                                .url("https://github.com/Gabriel-Miranda-98"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento")
                ));
    }
}
