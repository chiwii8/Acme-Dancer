package controllers.administrador;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Estilo;
import services.EstiloService;

@Controller
public class EstiloAdministradorController extends AbstractController {

    /// Cargamos los servicios que emplea la vista
    EstiloService estiloService;

    @Autowired
    public EstiloAdministradorController(EstiloService estiloService) {
        this.estiloService = estiloService;

    }

    /// Listado
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        Collection<Estilo> estilos;

        estilos = estiloService.findAll();
        result = new ModelAndView("estilo/list");
        result.addObject("estilos", estilos);

        return result;
    }

    /// Creado
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Estilo estilo;

        estilo = this.estiloService.create();
        result = this.createEditModelAndView(estilo);

        return result;

    }

    /// Editar
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int estiloId) {
        ModelAndView result;
        Estilo estilo;

        estilo = estiloService.findById(estiloId);
        Assert.notNull(estilo);
        result = createEditModelAndView(estilo);

        return result;
    }

    /// Guardar
    @RequestMapping(value = "/edit", method = RequestMethod.GET, params = "save")
    public ModelAndView save(@Valid Estilo estilo, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(estilo);
        } else {
            try {
                estiloService.save(estilo);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable e) {

                result = createEditModelAndView(estilo, "estilo.commit.error");
            }
        }
        return result;
    }

    protected ModelAndView createEditModelAndView(final Estilo estilo) {
        ModelAndView result;

        result = new ModelAndView("estilo/edit");

        result.addObject("estilo", estilo);

        return result;
    }

    protected ModelAndView createEditModelAndView(final Estilo estilo, final String mensaje) {
        ModelAndView result;

        result = new ModelAndView("estilo/edit");

        result.addObject("estilo", estilo);
        result.addObject("mensaje", mensaje);

        return result;
    }

}
