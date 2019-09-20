package ec.gob.mag.rna.predio;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@SpringBootApplication
@EnableSwagger2
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MicroRnaPredio extends SpringBootServletInitializer { 
	
	public static void main(String[] args) {
		SpringApplication.run(MicroRnaPredio.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<MicroRnaPredio> applicationClass = MicroRnaPredio.class;
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages"); 
	    return messageSource;
	}
	

}
