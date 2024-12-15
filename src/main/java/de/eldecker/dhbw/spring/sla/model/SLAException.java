package de.eldecker.dhbw.spring.sla.model;


/**
 * Exception-Klasse für Fehler bei SLA-Berechnungen.
 */
@SuppressWarnings("serial")
public class SLAException extends Exception {

    /**
     * Erzeugt eine neue SLAException mit der übergebenen Beschreibung.
     *
     * @param beschreibung Beschreibung, was genau schief gegangen ist.
     */
    public SLAException( String beschreibung ) {

        super( beschreibung );
    }
    
}
