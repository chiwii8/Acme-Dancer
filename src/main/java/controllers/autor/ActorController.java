package controllers.autor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.actores.Academia;
import domain.actores.Actor;
import domain.actores.Alumno;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import services.AcademiaService;
import services.ActorService;
import services.AlumnoService;

@Controller
@RequestMapping("/actor")
public class ActorController {

    ActorService actorService;

    UserAccountService userAccountService;

    AcademiaService academiaService;

    AlumnoService alumnoService;

    @Autowired
    public ActorController(ActorService actorService, UserAccountService userAccountService,
            AcademiaService academiaService, AlumnoService alumnoService) {
        this.actorService = actorService;
        this.userAccountService = userAccountService;
        this.academiaService = academiaService;
        this.alumnoService = alumnoService;

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        UserAccount userAccount;
        Collection<String> authorities = new ArrayList<>();

        userAccount = this.userAccountService.create();

        authorities.add(Authority.ACADEMIA);
        authorities.add(Authority.ALUMNO);

        result = new ModelAndView("actor/createAccount");
        result.addObject("authorities", authorities);
        result.addObject("userAccount", userAccount);

        return result;
    }

    @RequestMapping(value = "/createuseraccount", method = RequestMethod.POST)
    public ModelAndView createUserAccount(@Valid UserAccount userAccount) {
        ModelAndView result;
        Iterator<Authority> ite = userAccount.getAuthorities().iterator();

        Authority authority = (Authority) ite.next();
        String authorityname = authority.getAuthority();

        if (Authority.ACADEMIA.equalsIgnoreCase(authorityname)) {
            result = this.createUserAcademy(userAccount);
        } else {
            result = this.createUserStudent(userAccount);
        }

        return result;
    }

    @RequestMapping(value = "/createAcademy", method = RequestMethod.POST, params = "save")
    public ModelAndView createAcademy(@Valid Academia academia, BindingResult binding) {
        ModelAndView result;
        Academia academia_aux;

        if (binding.hasErrors()) {
            result = createUserAcademy(academia.getUserAccount(), "actor.commit.academy.comercialname");
            return result;
        }

        academia_aux = this.academiaService.findByNombreComercial(academia.getNombreComercial());
        if (academia_aux != null) {
            result = createUserAcademy(academia.getUserAccount(), "actor.commit.same.academy");
        } else {
            this.academiaService.save(academia);
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    @RequestMapping(value = "/createStudent", method = RequestMethod.POST, params = "save")
    public ModelAndView createStudent(@Valid Alumno alumno, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createUserStudent(alumno.getUserAccount(), "actor.commit.same.academy");
            return result;
        }

        Actor actor = this.actorService.findByCorreo(alumno.getCorreo());
        if (actor != null) {
            result = createUserStudent(alumno.getUserAccount(), "actor.commit.same.academy");
        } else {
            this.alumnoService.save(alumno);
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    protected ModelAndView createUserAcademy(UserAccount userAccount) {
        ModelAndView result;
        Academia academia;

        result = new ModelAndView("actor/createAcademy");

        academia = this.academiaService.create();
        academia.setUserAccount(userAccount);

        result.addObject("academy", academia);

        return result;
    }

    protected ModelAndView createUserAcademy(UserAccount userAccount, String mensaje) {
        ModelAndView result;
        Academia academia;

        result = new ModelAndView("actor/createAcademy");

        academia = this.academiaService.create();
        academia.setUserAccount(userAccount);

        result.addObject("academy", academia);
        result.addObject("mensaje", mensaje);

        return result;
    }

    protected ModelAndView createUserStudent(UserAccount userAccount) {
        ModelAndView result;
        Alumno alumno;

        result = new ModelAndView("actor/createStudent");

        alumno = this.alumnoService.create();
        alumno.setUserAccount(userAccount);

        result.addObject("student", alumno);

        return result;
    }

    protected ModelAndView createUserStudent(UserAccount userAccount, String mensaje) {
        ModelAndView result;
        Alumno alumno;

        result = new ModelAndView("actor/createStudent");

        alumno = this.alumnoService.create();
        alumno.setUserAccount(userAccount);

        result.addObject("student", alumno);
        result.addObject("mensaje", mensaje);
        return result;
    }

}
