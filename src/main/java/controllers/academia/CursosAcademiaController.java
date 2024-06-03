
package controllers.academia;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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
import domain.Curso;
import domain.Solicitud;
import domain.enumeraciones.SolicitudEstado;
import services.CursoService;
import services.SolicitudService;

@Controller
@RequestMapping("/academy/course")
public class CursosAcademiaController extends AbstractController {

	/// Cargamos los servicios que emplea el controlador
	CursoService		cursoService;

	/// Servicio de apoyo
	SolicitudService	solicitudService;


	@Autowired
	public CursosAcademiaController(final CursoService cursoService, final SolicitudService solicitudService) {
		this.cursoService = cursoService;
		this.solicitudService = solicitudService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAll();
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

		return result;
	}

	@RequestMapping(value = "/listByAcademia", method = RequestMethod.GET)
	public ModelAndView listByAcademia(@RequestParam final int id) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAllByAcademiaId(id);
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

		return result;
	}

	@RequestMapping(value = "/listBySearch", method = RequestMethod.GET)
	public ModelAndView listBySearch(@RequestParam final String busqueda) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findByString(busqueda);
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

		return result;
	}

	@RequestMapping(value = "/listByEstiloId", method = RequestMethod.GET)
	public ModelAndView listByEstiloId(@RequestParam final int id) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAllByEstiloId(id);
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

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
	public ModelAndView edit(@RequestParam("courseID") final int cursoId) {
		ModelAndView result;
		Curso curso;

		curso = this.cursoService.findById(cursoId);
		Assert.notNull(curso);
		result = this.createEditModelAndView(curso);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curso curso, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(curso);
		else
			try {
				this.cursoService.save(curso);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable e) {

				result = this.createEditModelAndView(curso, "course.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Curso course, final BindingResult binding) {
		ModelAndView result;
		Collection<Solicitud> solicitudes;

		/// Verificamos que la fecha final es próxima
		final Date fechaActual = new Date();
		if (fechaActual.compareTo(course.getFechaFin()) < 0) {
			this.cursoService.delete(course);
			result = new ModelAndView("redirect:list.do");
			return result;
		}

		/// Verificamos que no hay estudiantes o solicitudes abiertas
		solicitudes = this.solicitudService.findAllByCursoId(course.getId());

		try {
			final boolean is_all_Rechazado = solicitudes.stream().allMatch(courseAux -> courseAux.getEstado() == SolicitudEstado.RECHAZADO);

			if (is_all_Rechazado) {
				this.cursoService.delete(course);
				result = new ModelAndView("redirect:list.do");
			} else
				result = this.createEditModelAndView(course, "course.commit.error.student");
		} catch (final Throwable e) {
			this.cursoService.delete(course);
			result = new ModelAndView("redirect:list.do");

		}

		return result;
	}
	/// Métodos Auxilires

	protected ModelAndView createEditModelAndView(final Curso curso) {
		ModelAndView result;

		result = new ModelAndView("course/edit");

		result.addObject("course", curso);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Curso curso, final String mensaje) {
		ModelAndView result;

		result = new ModelAndView("course/edit");

		result.addObject("course", curso);
		result.addObject("mensaje", mensaje);
		return result;
	}

	/////Metodos que trabajan con las solicitudes
	@RequestMapping(value = "/listactiverequest", method = RequestMethod.GET)
	public ModelAndView listRequest(@RequestParam(value = "courseId") final int courseId) {
		ModelAndView result;
		final SolicitudEstado estado = SolicitudEstado.PENDIENTE;
		Collection<Solicitud> solicitudes = this.solicitudService.findAllByCursoId(courseId);

		solicitudes = solicitudes.stream().filter(element -> element.getEstado() == SolicitudEstado.PENDIENTE).collect(Collectors.toList());

		result = new ModelAndView("request/list");
		result.addObject("requests", solicitudes);

		return result;
	}

	@RequestMapping(value = "/acceptrequest", method = RequestMethod.GET)
	public ModelAndView acceptRequest(@RequestParam(value = "requestId") final int id) {
		ModelAndView result;

		final Solicitud solicitud = this.solicitudService.findById(id);
		solicitud.setEstado(SolicitudEstado.ACEPTADO);
		this.solicitudService.save(solicitud);

		result = new ModelAndView("redirect:courses/list");
		return result;

	}

	@RequestMapping(value = "/rejectrequest", method = RequestMethod.GET)
	public ModelAndView rejectRequest(@RequestParam(value = "requestId") final int id) {
		ModelAndView result;

		final Solicitud solicitud = this.solicitudService.findById(id);
		solicitud.setEstado(SolicitudEstado.RECHAZADO);
		this.solicitudService.save(solicitud);

		result = new ModelAndView("redirect:courses/list");
		return result;

	}

}
