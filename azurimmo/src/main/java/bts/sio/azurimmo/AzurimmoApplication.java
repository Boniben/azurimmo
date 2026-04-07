package bts.sio.azurimmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot Azurimmo.
 *
 * @SpringBootApplication regroupe 3 annotations en une seule :
 *   - @Configuration       : cette classe peut déclarer des beans Spring
 *   - @EnableAutoConfiguration : Spring Boot configure automatiquement
 *                               l'application selon les dépendances présentes
 *                               (ex: JPA, MariaDB, Web...)
 *   - @ComponentScan       : Spring scanne automatiquement tous les packages
 *                            sous "bts.sio.azurimmo" pour trouver les classes
 *                            annotées (@RestController, @Service, @Repository...)
 */
@SpringBootApplication
public class AzurimmoApplication {

	/**
	 * Point d'entrée de l'application.
	 * Lance le serveur Tomcat embarqué sur le port défini dans application.properties.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AzurimmoApplication.class, args);
	}

}
