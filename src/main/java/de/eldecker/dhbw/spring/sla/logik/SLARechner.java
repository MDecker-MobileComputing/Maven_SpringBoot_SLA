package de.eldecker.dhbw.spring.sla.logik;

 import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheitKonstanten.SEKUNDEN_PRO_JAHR;
 import static de.eldecker.dhbw.spring.sla.model.Dauer.sekundenZuDauer;
 
import de.eldecker.dhbw.spring.sla.model.Ausfallzeiten;
import de.eldecker.dhbw.spring.sla.model.Dauer;
import de.eldecker.dhbw.spring.sla.model.SLAException;

import org.springframework.stereotype.Service;


/**
 * Klasse mit Gechäftslogik für SLA-Berechnungen, deshalb mit {@code @Service} annotiert.
 */
@Service
public class SLARechner {
    
    /**
     * Berechnet maximale Ausfallzeit für verschiedene Zeiträume.
     *
     * @param slaProzentWert Prozentwert für die Verfügbarkeit, z.B. 99,99%;
     *                       muss zwischen 0.0 und 100.0 liegen
     *
     * @return Maximale Ausfallzeit pro Jahr, pro Woche und pro Tag 
     *
     * @throws SLAException wenn der übergebene SLA-Wert nicht zwischen 0.0 
     *                      und 100.0 liegt
     */    
    public Ausfallzeiten berechneAusfallzeiten( double slaProzentWert ) throws SLAException {
    
        final int maxSekundenAusfallzeitProJahr = slaProzentZuSekunden( slaProzentWert );
        
        final int maxSekundenAusfallProTag   = maxSekundenAusfallzeitProJahr / 365;                 
        final int maxSekundenAusfallProWoche = maxSekundenAusfallProTag      * 7; 
               
        Dauer ausfallDauerProJahr  = sekundenZuDauer( maxSekundenAusfallzeitProJahr );
        Dauer ausfallDauerProTag   = sekundenZuDauer( maxSekundenAusfallProTag      );
        Dauer ausfallDauerProWoche = sekundenZuDauer( maxSekundenAusfallProWoche    ); 
        
        return new Ausfallzeiten( ausfallDauerProJahr, 
                                  ausfallDauerProWoche, 
                                  ausfallDauerProTag  
                                );
    }
    
    
    /**
     * Rechnet SLA-Prozentwert in erlaubte Ausfallzeit pro Jahr in Sekunden um.
     *
     * @param slaProzentWert Prozentwert für die Verfügbarkeit, z.B. 99,99%;
     *                       muss zwischen 0.0 und 100.0 liegen
     *
     * @return Anzahl Sekunden pro Jahr, die die Applikation ausfallen darf,
     *         ohne dass der SLA-Wert unterschritten wird
     *
     * @throws SLAException wenn der übergebene SLA-Wert nicht zwischen 0.0 und
     *                      100.0 liegt
     */
    public int slaProzentZuSekunden( double slaProzentWert ) throws SLAException {

        if ( slaProzentWert < 0.0 || slaProzentWert > 100.0 ) {

            throw new SLAException( "Ungültiger SLA-Wert " + slaProzentWert +
                                    " (muss zwischen 0.0 und 100.0 liegen)" );
        }

        final double ausfallzeitErlaubtProzent = 100.0 - slaProzentWert;

        return (int) ( ausfallzeitErlaubtProzent * SEKUNDEN_PRO_JAHR / 100.0 );
    }

}
