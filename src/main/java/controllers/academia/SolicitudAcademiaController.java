package controllers.academia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
public class SolicitudAcademiaController {

    SolicitudService solicitudService;
    CursoService cursoService;
    AcademiaService academiaService;

    @Autowired
    public SolicitudAcademiaController(SolicitudService solicitudService, CursoService cursoService,
            AcademiaService academiaService) {
        this.solicitudService = solicitudService;
        this.cursoService = cursoService;
        this.academiaService = academiaService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView result;
        Collection<Solicitud> solicitudes = new ArrayList();

        try {
            UserAccount user = LoginService.getPrincipal();
            Academia academia = this.academiaService.findByUserAccount(user);
            Collection<Curso> cursos = academia.getCursos();

            for (Curso curso : cursos) {
                Collection<Solicitud> aux = this.solicitudService.findAllByCursoId(curso.getId());
                solicitudes.addAll(aux);
            }

            result = new ModelAndView("request/list");
            result.addObject("requests", solicitudes);

        } catch (Exception e) {
            result = new ModelAndView("redirect:/");
        }
        return result;
    }

    @RequestMapping(value = "/acceptrequest", method = RequestMethod.GET)
    public ModelAndView requestAccept(@RequestParam(value = "resquestId") int id) {
        ModelAndView result;
        Solicitud solicitud;

        try {
            solicitud = this.solicitudService.findById(id);
            solicitud.setEstado(SolicitudEstado.ACEPTADO);
            this.solicitudService.save(solicitud);
            result = new ModelAndView("redirect:/academy/request/list");

        } catch (Exception e) {
            result = new ModelAndView("redirect:/academy/request/list");
        }

        return result;
    }

    @RequestMapping(value = "/rejectrequest", method = RequestMethod.GET)
    public ModelAndView rejectRequest(@RequestParam(value = "requestId") final int id) {
        ModelAndView result;
        Solicitud solicitud;

        try {
            solicitud = this.solicitudService.findById(id);
            solicitud.setEstado(SolicitudEstado.RECHAZADO);
            this.solicitudService.save(solicitud);
            result = new ModelAndView("redirect:/academy/request/list");

        } catch (Exception e) {
            result = new ModelAndView("redirect:/academy/request/list");
        }
        return result;

    }
}
