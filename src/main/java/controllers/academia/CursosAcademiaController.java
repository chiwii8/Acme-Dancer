package controllers.academia;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    CursoService cursoService;

    /// Servicio de apoyo
    SolicitudService solicitudService;

    @Autowired
    public CursosAcademiaController(CursoService cursoService, SolicitudService solicitudService) {
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
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
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
    public ModelAndView save(@Valid Curso curso, final BindingResult binding) {
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
    public ModelAndView delete(@Valid Curso course, final BindingResult binding) {
        ModelAndView result;
        Collection<Solicitud> solicitudes;

        /// Verificamos que la fecha final es próxima
        Date fechaActual = new Date();
        if (fechaActual.compareTo(course.getFechaFin()) < 0) {
            this.cursoService.delete(course);
            result = new ModelAndView("redirect:list.do");
            return result;
        }

        /// Verificamos que no hay estudiantes o solicitudes abiertas
        solicitudes = this.solicitudService.findAllByCursoId(course.getId());

        try {
            boolean is_all_Rechazado = solicitudes.stream()
                    .allMatch(courseAux -> courseAux.getEstado() == SolicitudEstado.RECHAZADO);

            if (is_all_Rechazado) {
                this.cursoService.delete(course);
                result = new ModelAndView("redirect:list.do");
            } else {
                result = this.createEditModelAndView(course, "course.commit.error.student");
            }
        } catch (Throwable e) {
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

}
