package ec.gob.mag.rna.predio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableResourceServer
@EnableSwagger2
@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MicroRnaPredio extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MicroRnaPredio.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<MicroRnaPredio> applicationClass = MicroRnaPredio.class;

}
