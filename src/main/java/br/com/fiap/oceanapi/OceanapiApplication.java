package br.com.fiap.oceanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Controller
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Ocean API", version = "1.0.0", description = "API de Monitoramento e Conservação dos Habitats Marinhos", contact = @Contact(name = "DaVinci Group", email = "davinci@fiap.com.br")))
public class OceanapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanapiApplication.class, args);
	}

	@RequestMapping
	@ResponseBody
	public String home() {
		return "WebApp DaVinci Ocean";
	}

}
