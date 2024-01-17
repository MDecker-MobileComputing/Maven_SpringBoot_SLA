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
    public ThymeleafWebController(SLARechner slaRechner) {

        _slaRechner = slaRechner;
    }


    @GetMapping("/sla/{slaWertProzent}")
    public String slaWertUmrechnen(@PathVariable String slaWertProzent,
                                   Model model) {

        return "todo";
    }

}
