
package controllers.alumno;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

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
import domain.actores.Alumno;
import domain.enumeraciones.SolicitudEstado;
import security.LoginService;
import security.UserAccount;
import services.AlumnoService;
import services.CursoService;
import services.SolicitudService;

@Controller
@RequestMapping("student/request")
public class SolicitudAlumnoController extends AbstractController {

	/// Servicios
	SolicitudService	solicitudService;
	AlumnoService		alumnoService;
	CursoService		cursoService;


	@Autowired
	public SolicitudAlumnoController(final SolicitudService solicitudService, final AlumnoService alumnoService, final CursoService cursoService) {
		this.alumnoService = alumnoService;
		this.solicitudService = solicitudService;
		this.cursoService = cursoService;
	}

	/// Listar
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Solicitud> solicitudes;
		final UserAccount user = LoginService.getPrincipal();

		try {
			final Alumno alumno = this.alumnoService.findByUserAccount(user.getId());
			solicitudes = this.solicitudService.findAllByAlumnoId(alumno.getId());

			result = new ModelAndView("student/request/list");
			result.addObject("requests", solicitudes);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Solicitud solicitud;

		solicitud = this.solicitudService.create();
		result = this.createEditModelAndView(solicitud);

		return result;
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ModelAndView listRequest() {
		ModelAndView result;
		try {

			final Collection<Curso> cursos;
			final UserAccount user = LoginService.getPrincipal();
			final Alumno alumno = this.alumnoService.findByUserAccount(user.getId());

			cursos = this.cursoService.findAllCursosSinCursoByAlumno(alumno.getId());
			result = new ModelAndView("student/request/request");
			result.addObject("courses", cursos);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
			System.out.println("Excepción: " + e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public ModelAndView request(@RequestParam(value = "courseId") final int courseId) {
		ModelAndView result;
		Solicitud solicitud;

		try {
			final Curso curso = this.cursoService.findById(courseId);
			solicitud = this.solicitudService.create();

			final LocalDate fecha = LocalDate.now();
			final ZoneId zone = ZoneId.systemDefault();
			final Instant instant = fecha.atStartOfDay(zone).toInstant();
			final Date fechaRealizacion = Date.from(instant);

			final UserAccount user = LoginService.getPrincipal();
			final Alumno alumno = this.alumnoService.findByUserAccount(user.getId());

			solicitud.setCurso(curso);
			solicitud.setAlumno(alumno);
			solicitud.setFecha(fechaRealizacion);
			solicitud.setEstado(SolicitudEstado.PENDIENTE);

			solicitud = this.solicitudService.save(solicitud);
			alumno.addSolicitud(solicitud);
			this.alumnoService.save(alumno);
			result = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			result = new ModelAndView("student/request/request");
		}

		return result;
	}

	///
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int solicitudId) {
		ModelAndView result;
		Solicitud solicitud;

		solicitud = this.solicitudService.findById(solicitudId);
		Assert.notNull(solicitud);
		result = this.createEditModelAndView(solicitud);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView save(@Valid final Solicitud solicitud, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(solicitud, "No se ha producido realizar la operacion");
		else
			try {
				this.solicitudService.save(solicitud);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable e) {
				result = this.createEditModelAndView(solicitud, "solicitud.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Solicitud solicitud) {
		ModelAndView result;

		result = new ModelAndView("request/edit");

		result.addObject("request", solicitud);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Solicitud solicitud, final String mensaje) {
		ModelAndView result;

		result = new ModelAndView("request/edit");

		result.addObject("request", solicitud);
		result.addObject("mensaje", mensaje);

		return result;
	}

}
