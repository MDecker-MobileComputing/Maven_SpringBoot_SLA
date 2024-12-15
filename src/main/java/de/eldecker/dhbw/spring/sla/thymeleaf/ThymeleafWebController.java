package de.eldecker.dhbw.spring.sla.thymeleaf;

import de.eldecker.dhbw.spring.sla.logik.SLARechner;
import de.eldecker.dhbw.spring.sla.model.Ausfallzeiten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Controller für Thymeleaf-Templates.
 */
@Controller
public class ThymeleafWebController {

    private Logger LOG = LoggerFactory.getLogger( ThymeleafWebController.class );

    /** Bean mit Business-Logik für SLA-Berechnung.  */
    private SLARechner _slaRechner;


    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public ThymeleafWebController( SLARechner slaRechner ) {

        _slaRechner = slaRechner;
    }


    /**
     * Seite mit Ausfallzeiten für bestimmten SLA-Wert anzeigen.
     * 
     * @param slaWertProzent Prozent-Wert für SLA
     * 
     * @param model Objekt um Werte für Platzhalter in Template zu setzen
     * 
     * @return Template-Datei 
     */
    @GetMapping("/sla/{slaWertProzent}")
    public String slaWertUmrechnen( @PathVariable String slaWertProzent,
                                    Model model ) {
    	
        LOG.info( "Pfad /sla aufgerufen mit Pfadparademeter \"{}\".", slaWertProzent );
                  
        try {
        	
            double slaDouble = Double.parseDouble( slaWertProzent );
            
            Ausfallzeiten ausfallzeiten = _slaRechner.berechneAusfallzeiten( slaDouble ); 
                        
            LOG.info( "Ausfallzeilen für SLA=\"%s\": " + ausfallzeiten, slaWertProzent );
                     
            model.addAttribute( "slaProzentWert"    , slaDouble );
            model.addAttribute( "maxAusfallProJahr" , ausfallzeiten.ausfallzeitProJahr() );
                               
            model.addAttribute( "maxAusfallProWoche", ausfallzeiten.ausfallzeitProWoche() );
                               
            model.addAttribute( "maxAusfallProTag"  , ausfallzeiten.ausfallzeitProTag() );
                               
            return "ergebnis"; // template "ergebnis.html"
        }
        catch ( Exception ex ) {
        	
            final String fehlerText =
                  String.format( "Fehler für SLA-Wert \"%s\": " + ex,  slaWertProzent );
                               
            LOG.error( fehlerText, ex );

            model.addAttribute( "fehlertext", fehlerText );

            return "fehler"; // template "fehler.html"
        }
    }

}
