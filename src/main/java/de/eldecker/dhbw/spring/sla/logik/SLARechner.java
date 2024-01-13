package de.eldecker.dhbw.spring.sla.logik;

import de.eldecker.dhbw.spring.sla.model.SLAException;
import de.eldecker.dhbw.spring.sla.model.ZeitenRecord;

import org.springframework.stereotype.Service;


/**
 * Klasse mit Gechäftslogik für SLA-Berechnungen, deshalb mit {@code @Service} annotiert.
 */
@Service
public class SLARechner {

    /** Sekunden pro Jahr (kein Schaltjahr, also 365 Tage) */
    public static final int SEKUNDEN_PRO_JAHR   = 60 * 60 * 24 * 365;

    public static final int SEKUNDEN_PRO_WOCHE  = 60 * 60 * 24 * 7;
    public static final int SEKUNDEN_PRO_TAG    = 60 * 60 * 24;
    public static final int SEKUNDEN_PRO_STUNDE = 60 * 60;
    public static final int SEKUNDEN_PRO_MINUTE = 60;


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


    /**
     * Rechnet Anzahl Sekunden pro Jahr in verschiedene Zeitgranularitäten um:
     * zuerst volle Wochen, dann volle Tage, Stunden, Minuten und Sekunden.
     *
     * @param sekundenImJahr Anzahl Sekunden pro Jahr
     *
     * @return Unveränderbares Ergebnisobjekt mit Anzahl Wochen, Tage, Stunden,
     *         Minuten und Sekunden
     */
    public ZeitenRecord sekundenZuZeitenProJahr(int sekundenImJahr) {

        final int anzahlWoche = sekundenImJahr / SEKUNDEN_PRO_WOCHE;
        int restSekunden      = sekundenImJahr % SEKUNDEN_PRO_WOCHE;

        final int anzahlTage = restSekunden / SEKUNDEN_PRO_TAG;
        restSekunden         = restSekunden % SEKUNDEN_PRO_TAG;

        final int anzahlStunden = restSekunden / SEKUNDEN_PRO_STUNDE;
        restSekunden            = restSekunden % SEKUNDEN_PRO_STUNDE;

        final int anzahlMinuten = restSekunden / SEKUNDEN_PRO_MINUTE;
        restSekunden            = restSekunden % SEKUNDEN_PRO_MINUTE;

        return new ZeitenRecord( anzahlWoche,
                                 anzahlTage,
                                 anzahlStunden,
                                 anzahlMinuten,
                                 restSekunden  );
    }

}
