package projectstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class ProjectStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStackApplication.class, args);
	}

}
