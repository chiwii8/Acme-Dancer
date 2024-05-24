package controllers.comun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Estilo;
import services.EstiloService;

@Controller
@RequestMapping("/Estilo")
public class EstiloController {

    /// Cargamos el servicio
    EstiloService estiloService;

    @Autowired
    public EstiloController(EstiloService estiloService) {
        this.estiloService = estiloService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        Collection<Estilo> estilos;

        estilos = this.estiloService.findAll();
        result = new ModelAndView();

        result.addObject("estilos", estilos);

        return result;
    }
}
