package de.eldecker.dhbw.spring.sla.model;

/**
 * Record-Klasse repräsentiert eine Zeitdauer aufgeteilt auf Wochen,
 * Tage, Stunden, Minuten und Sekunden.
 * <br><br>
 *  
 * Es handelt sich um einen {@code record}, also sind die Werte nach
 * Erzeugung des Objekts nicht änderbar.
 */
public record Dauer( int wochen,
                     int tage,
                     int stunden,
                     int minuten,
                     int sekunden ) {}