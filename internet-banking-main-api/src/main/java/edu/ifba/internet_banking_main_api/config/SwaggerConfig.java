package edu.ifba.internet_banking_main_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Internet Banking API")
                        .description("Internet Banking API developed for a Web Development Class in Instituto Federal da Bahia")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Alexandre Morais")
                                .email("20232160016@ifba.edu.br"))
                        .license(new License()
                                .name("GNU General Public License")
                                .url("https://www.gnu.org/licenses/gpl-3.0.en.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT token for authentication")));
    }
}
