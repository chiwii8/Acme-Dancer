
package controllers.comun;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Curso;
import domain.actores.Academia;
import services.AcademiaService;

@Controller
@RequestMapping("/academy")
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

		result = new ModelAndView("academy/list");
		result.addObject("academias", academias);
		return result;

	}

	@RequestMapping(value = "/listByCurso", method = RequestMethod.GET)
	public ModelAndView listByCursoId(@Valid final Curso curso) {
		ModelAndView result;
		Academia academia;

		academia = this.academiaService.findByCursoId(curso.getId());

		result = new ModelAndView("academy/list");
		result.addObject("academia", academia);

		return result;
	}
}
