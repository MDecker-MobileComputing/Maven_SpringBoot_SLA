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
                     int sekunden ) {
    
    /**
     * Liefer gut lesbare String-Repräsentation der Dauer zurück.
     * 
     * @return Zeitdauer, z.B. "2 Wochen, 1 Tag, 3 Stunden, 1 Minute und 33 Sekunden"
     */
    @Override
    public String toString() {
        
        // Einheit in Plural oder Singular
        final String einheitWochen   = wochen   == 1 ? "Woche"   : "Wochen"; 
        final String einheitTage     = tage     == 1 ? "Tag"     : "Tage";
        final String einheitStunden  = stunden  == 1 ? "Stunde"  : "Stunden";
        final String einheitMinuten  = minuten  == 1 ? "Minute"  : "Minuten";
        final String einheitSekunden = sekunden == 1 ? "Sekunde" : "Sekunden";
        
        return String.format( "%d %s, %d %s, %d %s, %d %s und %d %s", 
                              wochen   , einheitWochen,
                              tage    , einheitTage,
                              stunden , einheitStunden,
                              minuten , einheitMinuten,
                              sekunden, einheitSekunden );
    }
    
}