package mx.gob.imss.cit.sci.mssci.accesodatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class MssciAccesodatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MssciAccesodatosApplication.class, args);
	}

}
