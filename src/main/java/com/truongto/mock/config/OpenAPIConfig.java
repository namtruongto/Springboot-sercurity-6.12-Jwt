package com.truongto.mock.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    
    @Value("${swagger.dev-url}")
    private String devUrl;

    @Value("${swagger.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server Dev URL API TRUONGTN");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server Prod URL API TRUONGTN");

        Contact contact = new Contact();
        contact.setEmail("tonamtruong17062000@gmail.com");
        contact.setName("Tô Nam Trường");
        contact.setUrl("https://truongto.github.io/");

        License license = new License().name("My license").url("https://truongto.github.io/");

        Info info = new Info()
            .title("API TRUONGTN")
            .version("1.0.0")
            .contact(contact)
            .description("API TRUONGTN")
            .license(license);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
