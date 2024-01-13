package de.eldecker.dhbw.spring.sla.model;

/**
 * Record-Klasse für Ergebnis von SLA-Umrechung.
 * Die Anzahl Sekunden (pro Jahr), denen ein SLA-Wert (Prozentwert)
 * entspricht, wird in Tage, Stunden, Minuten und Sekunden umgerechnet.
 * Zunächst werden volle Wochen berechnet, dann volle Tage, Stunden, usw.
 */
public record ZeitenRecord( int wochen,
                            int tage,
                            int stunden,
                            int minuten,
                            int sekunden ) {}