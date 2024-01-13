package de.eldecker.dhbw.spring.sla.logik;

import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_JAHR;

import de.eldecker.dhbw.spring.sla.model.SLAException;
import de.eldecker.dhbw.spring.sla.model.Dauer;

import org.springframework.stereotype.Service;


/**
 * Klasse mit Gechäftslogik für SLA-Berechnungen, deshalb mit {@code @Service} annotiert.
 */
@Service
public class SLARechner {




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
    public int slaProzentZuSekunden(double slaProzentWert) throws SLAException {

        if (slaProzentWert < 0.0 || slaProzentWert > 100.0) {

            throw new SLAException("Ungültiger SLA-Wert (muss zwischen 0.0 und 100.0 liegen): " +
                                   slaProzentWert);
        }

        final double ausfallzeitErlaubtProzent = 100.0 - slaProzentWert;

        return (int) (ausfallzeitErlaubtProzent * SEKUNDEN_PRO_JAHR / 100.0);
    }

}
