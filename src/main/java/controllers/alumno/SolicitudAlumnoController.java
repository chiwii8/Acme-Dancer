
package controllers.alumno;

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
import domain.Solicitud;
import domain.actores.Alumno;
import security.LoginService;
import security.UserAccount;
import services.AlumnoService;
import services.SolicitudService;

@Controller
@RequestMapping("student/request")
public class SolicitudAlumnoController extends AbstractController {

	/// Servicios
	SolicitudService solicitudService;
	AlumnoService alumnoService;


	@Autowired
	public SolicitudAlumnoController(final SolicitudService solicitudService, AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
		this.solicitudService = solicitudService;
	}

	/// Listar
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Solicitud> solicitudes;
		UserAccount user = LoginService.getPrincipal();

		try {
			Alumno alumno = this.alumnoService.findByUserAccount(user);
			solicitudes = this.solicitudService.findAllByAlumnoId(alumno.getId());

			result = new ModelAndView("request/list");
			result.addObject("requests", solicitudes);

		} catch (Exception e) {
			result = new ModelAndView("redirect:/academy/request/list");
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
