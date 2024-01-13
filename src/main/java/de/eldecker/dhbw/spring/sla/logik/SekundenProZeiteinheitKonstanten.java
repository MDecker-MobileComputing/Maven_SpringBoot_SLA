package de.eldecker.dhbw.spring.sla.logik;


/**
 * Klasse enthält Konstanten mit Anzahl von Sekunden für verschiedene Zeiteinheiten,
 * z.B. Sekunden pro Jahr.
 */
public class SekundenProZeiteinheitKonstanten {
    
    /** Dummy-Konstruktor um zu verhindern, dass diese Klasse instanziiert wird. */
    private SekundenProZeiteinheitKonstanten() {}        
    

    /** Sekunden pro Jahr (kein Schaltjahr, also 365 Tage): 31.536.000 */
    public static final int SEKUNDEN_PRO_JAHR   = 60 * 60 * 24 * 365;

    /** Sekunden pro Woche: 604.800 */
    public static final int SEKUNDEN_PRO_WOCHE  = 60 * 60 * 24 * 7;
    
    /** Sekunden pro Tag: 86.400 */
    public static final int SEKUNDEN_PRO_TAG    = 60 * 60 * 24;
    
    /** Sekunden pro Stunde: 3600 */ 
    public static final int SEKUNDEN_PRO_STUNDE = 60 * 60;
    
    /** Sekunden pro Minute: 60 (klar!) */
    public static final int SEKUNDEN_PRO_MINUTE = 60;    
    
}
