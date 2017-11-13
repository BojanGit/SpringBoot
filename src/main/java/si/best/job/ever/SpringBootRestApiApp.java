package si.best.job.ever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "si.best.job.ever.mongo.AppConfig")
@SpringBootApplication//(scanBasePackages={"si.best.job.ever.dao"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class SpringBootRestApiApp  {

	public static void main(String[] args) {
		System.out.println("\n\tHi SpringBootRestApiApp! You rox!\n");
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}
