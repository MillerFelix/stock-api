package com.kaspperacademy.stockapi.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${stock.openapi.dev-url}")
    public String devUrl;

    @Value("${stock.openapi.prod-url}")
    public String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServ = new Server();
        devServ.setUrl(devUrl);
        devServ.setDescription("Development URL");

        Server prodServ = new Server();
        devServ.setUrl(prodUrl);
        devServ.setDescription("Production URL");

        Contact contact = new Contact();
        contact.setEmail("millerredigolo@gmail.com");
        contact.setName("Miller Redigolo Felix");
        contact.setUrl("https://github.com/MillerFelix");

        Info info = new Info().title("Stock API").version("1.0").contact(contact).description("Inventory API Documentation");

        return new OpenAPI().info(info).servers(List.of(devServ, prodServ));
    }
}
