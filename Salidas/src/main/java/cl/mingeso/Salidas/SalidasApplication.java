package cl.mingeso.Salidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SalidasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalidasApplication.class, args);
	}

}
