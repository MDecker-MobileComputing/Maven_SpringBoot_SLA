package de.eldecker.dhbw.spring.sla;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.eldecker.dhbw.spring.sla.logik.SLARechner;
import de.eldecker.dhbw.spring.sla.model.Dauer;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Tests für {@code toString()}-Methode der Record-Klasse {@code Dauer}.
 */
@SpringBootTest
public class DauerTest {


    /** Class/code under Test (CUT). */
    @Autowired
    private SLARechner _cut;
    
    @Test
    void dauer0Sekunden() {
        
        final Dauer ergebnisDauer = _cut.sekundenZuZeitenProJahr(0);
        
        final String expectedString = "0 Wochen, 0 Tage, 0 Stunden, 0 Minuten und 0 Sekunden"; 
        
        assertEquals( expectedString, ergebnisDauer.toString() );
        
    }
    
}
