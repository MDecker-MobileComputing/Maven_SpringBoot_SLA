package de.eldecker.dhbw.spring.sla.model;

import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_WOCHE;
import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_TAG;
import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_STUNDE;
import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_MINUTE;
import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_JAHR;


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
                              wochen  , einheitWochen ,
                              tage    , einheitTage   ,
                              stunden , einheitStunden,
                              minuten , einheitMinuten,
                              sekunden, einheitSekunden );
    }
    
    

    /**
     * Rechnet Anzahl Sekunden in verschiedene Zeitgranularitäten um:
     * zuerst volle Wochen, dann volle Tage, Stunden, Minuten und Sekunden.
     *
     * @param sekunden Anzahl Sekunden, die umgerechnet werden soll;
     *                 darf nicht mehr als ein Jahr sein!
     *
     * @return Unveränderbares Ergebnisobjekt mit Anzahl Wochen, Tage, 
     *         Stunden, Minuten und Sekunden
     *         
     * @throws SLAException {@code sekunden} für mehr als ein (Standard-Jahr
     */    
    public static Dauer sekundenZuDauer(int sekunden) throws SLAException {
        
        if (sekunden > SEKUNDEN_PRO_JAHR) {
            
            throw new SLAException(
                        "Dauer von mehr als einem Jahr nicht erlaubt: " + sekunden + "s");
        }

        final int anzahlWochen  = sekunden / SEKUNDEN_PRO_WOCHE;
        final int restSekunden1 = sekunden % SEKUNDEN_PRO_WOCHE;

        final int anzahlTage    = restSekunden1 / SEKUNDEN_PRO_TAG;
        final int restSekunden2 = restSekunden1 % SEKUNDEN_PRO_TAG;

        final int anzahlStunden = restSekunden2 / SEKUNDEN_PRO_STUNDE;
        final int restSekunden3 = restSekunden2 % SEKUNDEN_PRO_STUNDE;

        final int anzahlMinuten = restSekunden3 / SEKUNDEN_PRO_MINUTE;
        final int restSekunden4 = restSekunden3 % SEKUNDEN_PRO_MINUTE;

        return new Dauer( anzahlWochen ,
                          anzahlTage,
                          anzahlStunden,
                          anzahlMinuten,
                          restSekunden4 
                        );
    }    
}