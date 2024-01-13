package de.eldecker.dhbw.spring.sla.model;


/**
 * Exception-Klasse für Fehler bei SLA-Berechnungen.
 */
public class SLAException extends Exception {

    /**
     * Erzeugt eine neue SLAException mit der übergebenen Nachricht.
     *
     * @param nachricht Nachricht, die den Fehler beschreibt.
     */
    public SLAException(String nachricht) {

        super(nachricht);
    }
}
