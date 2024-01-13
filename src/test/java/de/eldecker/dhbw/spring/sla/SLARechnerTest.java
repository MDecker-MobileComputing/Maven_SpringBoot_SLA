package de.eldecker.dhbw.spring.sla;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @CsvSource({ "100.0,0", 
                 " 99.3,220752",
                 "  0.0,31536000"})                   
    void prozentZuSekunden(double prozentwert, int ausfallSekundenProJahr) throws SLAException {

        int ergebnisSekunden = _cut.slaProzentZuSekunden(prozentwert);
        assertEquals( ausfallSekundenProJahr, ergebnisSekunden );
    }

    
    @ParameterizedTest
    @ValueSource(doubles = { -1.0, 101.0 })
    void exceptionWegenUngueltigemWert(double slaWert) {
        
        final Executable executable = () -> _cut.slaProzentZuSekunden(slaWert);         
        assertThrows(SLAException.class, executable );
    }

}
