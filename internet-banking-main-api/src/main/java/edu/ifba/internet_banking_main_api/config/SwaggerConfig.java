package edu.ifba.internet_banking_main_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Internet Banking API")
                        .description("""
                            ## Internet Banking API
                            
                            This API provides a complete internet banking solution developed for a Web Development 
                            Class at Instituto Federal da Bahia (IFBA).
                            
                            ### Features
                            - User registration and authentication
                            - Account balance inquiry
                            - Money deposits
                            - Money withdrawals  
                            - Inter-account payments
                            - Account statement retrieval
                            
                            ### Authentication
                            Most endpoints require JWT authentication. Use the `/auth` endpoint to obtain a token,
                            then include it in the `Authorization` header as `Bearer <token>`.
                            
                            ### Error Handling
                            The API uses standard HTTP status codes and returns error messages in JSON format.
                            """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Alexandre Morais")
                                .url("https://github.com/Ednaxx"))
                        .license(new License()
                                .name("GNU General Public License v3.0")
                                .url("https://www.gnu.org/licenses/gpl-3.0.en.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8081")
                                .description("Development server"),
                        new Server()
                                .url("http://localhost:8080/api")
                                .description("API Gateway (Production)")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT token for authentication. Obtain it via /auth endpoint.")));
    }
}
