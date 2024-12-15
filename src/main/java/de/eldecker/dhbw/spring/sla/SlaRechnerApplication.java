package de.eldecker.dhbw.spring.sla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Diese Klasse enthält die main-Methode, die die Anwendung startet.
 */
@SpringBootApplication
public class SlaRechnerApplication {


    /**
     * Diese Methode startet die Anwendung.
     *
     * @param args Kommandozeilenargumente, werden an Spring durchgereicht
     */
	public static void main( String[] args ) {

		SpringApplication.run( SlaRechnerApplication.class, args );
	}

}
