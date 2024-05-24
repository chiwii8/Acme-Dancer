package controllers.administrador.dashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministradorService;

@Controller
@RequestMapping("/administrator")
public class AcademiaAdministradorController {

    /// Servicios
    private AdministradorService administradorService;

    @Autowired
    public AcademiaAdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView stadistict() {
        ModelAndView result;
        Map<String, Double> estadisticas;

        estadisticas = administradorService.calcularCursosPorAcademia();
        result = new ModelAndView("/");
        estadisticas.forEach((metrica, estadistica) -> {
            result.addObject(metrica, estadistica);
        });

        return result;

    }
}
