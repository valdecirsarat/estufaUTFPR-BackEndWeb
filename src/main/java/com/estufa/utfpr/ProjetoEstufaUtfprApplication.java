package com.estufa.utfpr;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class ProjetoEstufaUtfprApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEstufaUtfprApplication.class, args);
		
		 
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/niveis/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		/* Liberando o mapeamento de usuario para todas as origens
		 * 
		 * CORS DE FORMA CENTRALIZADA
		 * */
	}

}
