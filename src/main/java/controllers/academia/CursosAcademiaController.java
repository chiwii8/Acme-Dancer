
package controllers.academia;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Curso;
import domain.Estilo;
import domain.actores.Academia;
import domain.enumeraciones.CursoNivel;
import domain.enumeraciones.DiaSemana;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.CursoService;
import services.EstiloService;

@Controller
@RequestMapping("/academy/course")
public class CursosAcademiaController extends AbstractController {

	/// Cargamos los servicios que emplea el controlador
	CursoService	cursoService;
	AcademiaService	academiaService;
	EstiloService	estiloService;


	@Autowired
	public CursosAcademiaController(final CursoService cursoService, final AcademiaService academiaService, final EstiloService estiloService) {
		this.cursoService = cursoService;
		this.academiaService = academiaService;
		this.estiloService = estiloService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByAcademia() {
		ModelAndView result;
		Collection<Curso> cursos;

		try {
			final UserAccount user = LoginService.getPrincipal();
			final Academia academia = this.academiaService.findByUserAccount(user.getId());
			cursos = academia.getCursos();
			result = new ModelAndView("academy/course/list");
			result.addObject("courses", cursos);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}

		return result;
	}

	/// Creacion
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Curso curso;

		curso = this.cursoService.create();
		result = this.createEditModelAndView(curso);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("courseId") final int cursoId) {
		ModelAndView result;
		Curso curso;

		try {
			curso = this.cursoService.findById(cursoId);
			result = this.createEditModelAndView(curso);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curso curso, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("Fecha" + curso.getFechaInicio());
			binding.getAllErrors().forEach(error -> System.out.println(error.toString()));
			result = this.createEditModelAndView(curso, "course.commit.error");
		} else
			try {
				final UserAccount user = LoginService.getPrincipal();
				final Academia academia = this.academiaService.findByUserAccount(user.getId());

				curso.setFechaInicio(this.updateDate(curso.getFechaInicio()));
				curso.setFechaFin(this.updateDate(curso.getFechaFin()));
				if (curso.getId() != 0)
					this.cursoService.save(curso);
				else {

					final Curso cursoAux = this.cursoService.save(curso);
					academia.addCurso(cursoAux);
					this.academiaService.save(academia);
				}

				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable e) {
				System.out.println("Excepción:" + e.getMessage());
				result = this.createEditModelAndView(curso, "course.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Curso curso) {
		ModelAndView result;

		try {
			final UserAccount user = LoginService.getPrincipal();

			final Academia academia = this.academiaService.findByUserAccount(user.getId());
			academia.removeCurso(curso);

			this.cursoService.delete(curso);
			result = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	/// Metodos Auxilires

	protected ModelAndView createEditModelAndView(final Curso curso) {
		ModelAndView result;
		Collection<Estilo> estilos;
		result = new ModelAndView("academy/course/edit");

		estilos = this.estiloService.findAll();
		result.addObject("course", curso);
		result.addObject("days", DiaSemana.values());
		result.addObject("levels", CursoNivel.values());
		result.addObject("styles", estilos);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Curso curso, final String mensaje) {
		ModelAndView result;
		Collection<Estilo> estilos;
		result = new ModelAndView("academy/course/edit");

		estilos = this.estiloService.findAll();
		result.addObject("course", curso);
		result.addObject("days", DiaSemana.values());
		result.addObject("levels", CursoNivel.values());
		result.addObject("styles", estilos);
		result.addObject("mensaje", mensaje);
		return result;
	}

	private Date updateDate(final Date date) {
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

}
