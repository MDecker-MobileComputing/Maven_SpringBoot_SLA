package de.eldecker.dhbw.spring.sla;

import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_JAHR;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.eldecker.dhbw.spring.sla.logik.SLARechner;
import de.eldecker.dhbw.spring.sla.model.SLAException;


/**
 * Unit-Tests für Klasse mit Business-Logik.
 */
@SpringBootTest
class SLARechnerTest {

    /** Class/code under Test (CUT). */
    @Autowired
    private SLARechner _cut;

    
    @ParameterizedTest
    @ValueSource(doubles = { -1.0, 101.0 })
    void exceptionWegenUngueltigemWert(double slaWert) {
        
        final Executable executable = () -> _cut.slaProzentZuSekunden(slaWert);         
        assertThrows(SLAException.class, executable );
    }

    
    @Test
    void slaZuSekunden_0Prozent() throws SLAException {

        int ergebnisSekunden = _cut.slaProzentZuSekunden(0.0);
        assertEquals( SEKUNDEN_PRO_JAHR, ergebnisSekunden );
    }

    
    @Test
    void slaZuSekunden_100Prozent() throws SLAException {

        int ergebnisSekunden = _cut.slaProzentZuSekunden(100.0);
        assertEquals( 0, ergebnisSekunden );
    }

    
    @Test
    void slaZuSekunden_993() throws SLAException {
        
        int ergebnisSekunden = _cut.slaProzentZuSekunden(99.3);
        assertEquals( 220752, ergebnisSekunden);
    }
    
}
