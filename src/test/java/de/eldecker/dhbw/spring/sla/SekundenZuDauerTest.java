package de.eldecker.dhbw.spring.sla;

import static de.eldecker.dhbw.spring.sla.logik.SekundenProZeiteinheit.SEKUNDEN_PRO_JAHR;
import static de.eldecker.dhbw.spring.sla.model.Dauer.sekundenZuDauer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import de.eldecker.dhbw.spring.sla.model.Dauer;
import de.eldecker.dhbw.spring.sla.model.SLAException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Tests für die Methoden {@code sekundenZuDauer(int)} und {@code toString()}
 * in der Klasse {@code Dauer}.
 */
@SpringBootTest
public class SekundenZuDauerTest {
       
    @ParameterizedTest
    @CsvSource(value = {
        "     0; 0 Wochen, 0 Tage, 0 Stunden, 0 Minuten und 0 Sekunden",
        "     1; 0 Wochen, 0 Tage, 0 Stunden, 0 Minuten und 1 Sekunde",
        "    61; 0 Wochen, 0 Tage, 0 Stunden, 1 Minute und 1 Sekunde",        
        "  3600; 0 Wochen, 0 Tage, 1 Stunde, 0 Minuten und 0 Sekunden",
        "  3601; 0 Wochen, 0 Tage, 1 Stunde, 0 Minuten und 1 Sekunde",
        "  3661; 0 Wochen, 0 Tage, 1 Stunde, 1 Minute und 1 Sekunde",
        " 86400; 0 Wochen, 1 Tag, 0 Stunden, 0 Minuten und 0 Sekunden", 
        "604800; 1 Woche, 0 Tage, 0 Stunden, 0 Minuten und 0 Sekunden",
        "694861; 1 Woche, 1 Tag, 1 Stunde, 1 Minute und 1 Sekunde"
    }, delimiter = ';')
    void dauerVonSekunden(int sekunden, String erwarteterString) throws SLAException {
        
        final Dauer ergebnisDauer = sekundenZuDauer(sekunden);
                 
        assertEquals( erwarteterString, ergebnisDauer.toString() );        
    }
    
    
    @Test
    void mehrAlsEinJahr() {
        
        final Executable executable = () -> sekundenZuDauer(SEKUNDEN_PRO_JAHR + 1);         
        assertThrows(SLAException.class, executable );
    }
    
}
