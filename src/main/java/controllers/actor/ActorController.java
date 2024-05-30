
package controllers.actor;

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

	ActorService		actorService;

	UserAccountService	userAccountService;

	AcademiaService		academiaService;

	AlumnoService		alumnoService;


	@Autowired
	public ActorController(final ActorService actorService, final UserAccountService userAccountService, final AcademiaService academiaService, final AlumnoService alumnoService) {
		this.actorService = actorService;
		this.userAccountService = userAccountService;
		this.academiaService = academiaService;
		this.alumnoService = alumnoService;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		UserAccount userAccount;
		final Collection<Authority> authorities;

		userAccount = this.userAccountService.create();

		authorities = Authority.listAuthorities();

		result = new ModelAndView("actor/create");
		result.addObject("authoritiesElement", authorities);
		result.addObject("userAccount", userAccount);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "next")
	public ModelAndView createUserAccount(@Valid final UserAccount userAccount) {
		ModelAndView result;
		final Iterator<Authority> ite = userAccount.getAuthorities().iterator();

		///TODO: Hacer verificación y devolver error en caso de ser necesario
		//final UserAccount user = this.userAccountService.findByuserName(userAccount.getUsername());

		final Authority authority = ite.next();
		final String authorityname = authority.getAuthority();

		if (Authority.ACADEMIA.equalsIgnoreCase(authorityname))
			result = this.createUserAcademy(userAccount);
		else
			result = this.createUserStudent(userAccount);

		return result;
	}

	@RequestMapping(value = "/createAcademy", method = RequestMethod.POST, params = "save")
	public ModelAndView createAcademy(@Valid final Academia academia, final BindingResult binding) {
		ModelAndView result;
		Academia academia_aux;

		if (binding.hasErrors()) {
			result = this.createUserAcademy(academia.getUserAccount(), "actor.commit.academy.comercialname");
			return result;
		}

		academia_aux = this.academiaService.findByNombreComercial(academia.getNombreComercial());
		if (academia_aux != null)
			result = this.createUserAcademy(academia.getUserAccount(), "actor.commit.same.academy");
		else {
			this.academiaService.save(academia);
			result = new ModelAndView("redirect:/");
		}

		return result;
	}

	@RequestMapping(value = "/createStudent", method = RequestMethod.POST, params = "save")
	public ModelAndView createStudent(@Valid final Alumno alumno, final BindingResult binding) {
		ModelAndView result;
		final UserAccount user = alumno.getUserAccount();
		System.out.println("Este es el usuario" + user.getUsername());
		if (binding.hasErrors()) {
			System.out.println("Tengo un error");
			binding.getAllErrors().forEach(error -> System.out.println(error.toString()));
			result = this.createUserStudent(alumno.getUserAccount(), "actor.commit.same.academy");
			return result;
		}

		final Actor actor = this.actorService.findByCorreo(alumno.getCorreo());
		if (actor != null) {
			System.out.println("Tengo un error Aqui");
			result = this.createUserStudent(alumno.getUserAccount(), "actor.commit.same.academy");

		} else {

			this.alumnoService.save(alumno);
			result = new ModelAndView("redirect:");
		}

		return result;
	}

	protected ModelAndView createUserAcademy(final UserAccount userAccount) {
		ModelAndView result;
		Academia academia;

		result = new ModelAndView("actor/createAcademy");

		academia = this.academiaService.create();
		academia.setUserAccount(userAccount);

		result.addObject("academy", academia);

		return result;
	}

	protected ModelAndView createUserAcademy(final UserAccount userAccount, final String mensaje) {
		ModelAndView result;
		Academia academia;

		result = new ModelAndView("actor/createAcademy");

		academia = this.academiaService.create();
		academia.setUserAccount(userAccount);

		result.addObject("academy", academia);
		result.addObject("mensaje", mensaje);

		return result;
	}

	protected ModelAndView createUserStudent(final UserAccount userAccount) {
		ModelAndView result;
		Alumno alumno;

		result = new ModelAndView("actor/createStudent");

		alumno = this.alumnoService.create();
		alumno.setUserAccount(userAccount);

		result.addObject("student", alumno);

		return result;
	}

	protected ModelAndView createUserStudent(final UserAccount userAccount, final String mensaje) {
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
