
package controllers.Comun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.actores.Academia;
import services.AcademiaService;

@Controller
@RequestMapping("/comun")
public class AcademiaController extends AbstractController {

	/// Cargamos los servicios que emplea la vista
	AcademiaService academiaService;


	@Autowired
	public AcademiaController(final AcademiaService academiaService) {
		this.academiaService = academiaService;
	}

	//Listar

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Academia> academias;

		academias = this.academiaService.findAll();

		result = new ModelAndView("academia/list");
		result.addObject("academias", academias);
		return result;

	}
}
