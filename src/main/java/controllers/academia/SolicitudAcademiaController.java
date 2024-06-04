
package controllers.academia;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Curso;
import domain.Solicitud;
import domain.actores.Academia;
import domain.enumeraciones.SolicitudEstado;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.CursoService;
import services.SolicitudService;

@Controller
@RequestMapping("/academy/request")
public class SolicitudAcademiaController extends AbstractController {

	SolicitudService	solicitudService;
	CursoService		cursoService;
	AcademiaService		academiaService;


	@Autowired
	public SolicitudAcademiaController(final SolicitudService solicitudService, final CursoService cursoService, final AcademiaService academiaService) {
		this.solicitudService = solicitudService;
		this.cursoService = cursoService;
		this.academiaService = academiaService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Solicitud> solicitudes = new ArrayList();

		try {
			final UserAccount user = LoginService.getPrincipal();
			final Academia academia = this.academiaService.findByUserAccount(user.getId());
			final Collection<Curso> cursos = academia.getCursos();

			for (final Curso curso : cursos) {
				final Collection<Solicitud> aux = this.solicitudService.findAllByCursoIdAndEstado(curso.getId(), SolicitudEstado.PENDIENTE);
				solicitudes.addAll(aux);
			}

			result = new ModelAndView("academy/request/list");
			result.addObject("requests", solicitudes);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	@RequestMapping(value = "/acceptrequest", method = RequestMethod.POST)
	public ModelAndView requestAccept(@RequestParam(value = "resquestId") final int id) {
		ModelAndView result;
		Solicitud solicitud;

		try {
			solicitud = this.solicitudService.findById(id);
			solicitud.setEstado(SolicitudEstado.ACEPTADO);
			this.solicitudService.save(solicitud);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/rejectrequest", method = RequestMethod.POST)
	public ModelAndView rejectRequest(@RequestParam(value = "resquestId") final int id) {
		ModelAndView result;
		Solicitud solicitud;

		try {
			solicitud = this.solicitudService.findById(id);
			solicitud.setEstado(SolicitudEstado.RECHAZADO);
			this.solicitudService.save(solicitud);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;

	}
}
