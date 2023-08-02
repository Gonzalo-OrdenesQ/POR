package cl.mingeso.Entradas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EntradasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntradasApplication.class, args);
	}

}
