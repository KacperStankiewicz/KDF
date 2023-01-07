package pl.pja.edu.KDF;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KdfApplication {

	private static final Logger log = LoggerFactory.getLogger(KdfApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KdfApplication.class, args);
		logApplicationStartup();
	}

	private static void logApplicationStartup(){
		log.info(
				"""
      
						----------------------------------------------------------
						\tApplication KDF is running! Access URLs:
						\tLocal: \t\thttp://localhost:8080
						\t"""
		);
	}

}
