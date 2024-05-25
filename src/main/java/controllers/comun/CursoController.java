
package controllers.comun;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curso;
import domain.Estilo;
import domain.actores.Academia;
import services.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	/// Cargamos el servicio
	CursoService cursoService;


	@Autowired
	public CursoController(final CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAll();
		result = new ModelAndView("curso/list");

		result.addObject("cursos", cursos);

		return result;
	}

	@RequestMapping(value = "/listByAcademia", method = RequestMethod.GET)
	public ModelAndView list(@Valid final Academia academia) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAllByAcademiaId(academia.getId());
		result = new ModelAndView("curso/list");

		result.addObject("cursos", cursos);

		return result;
	}

	@RequestMapping(value = "/listBySearch", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String busqueda) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findByString(busqueda);
		result = new ModelAndView("curso/list");

		result.addObject("cursos", cursos);

		return result;
	}

	@RequestMapping(value = "/listByCursoId", method = RequestMethod.GET)
	public ModelAndView list(@Valid final Estilo estilo) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAllByEstiloId(estilo.getId());
		result = new ModelAndView("curso/list");

		result.addObject("cursos", cursos);

		return result;
	}
}
