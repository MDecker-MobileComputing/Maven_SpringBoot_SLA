package de.eldecker.dhbw.spring.sla.model;


/**
 * Ein Objekt dieser Record-Klasse enthält die max. Ausfallzeit
 * pro Jahr, pro Woche und pro Tag.
 */
public record Ausfallzeiten( Dauer ausfallzeitProJahr, 
                             Dauer ausfallzeitProWoche, 
                             Dauer ausfallzeitProTag ) {
}
