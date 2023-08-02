package cl.mingeso.Resumenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResumenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumenesApplication.class, args);
	}

}
