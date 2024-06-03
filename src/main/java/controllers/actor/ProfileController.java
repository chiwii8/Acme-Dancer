
package controllers.actor;

import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.actores.Academia;
import domain.actores.Alumno;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.AcademiaService;
import services.AlumnoService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	AcademiaService	academiaService;
	AlumnoService	alumnoService;
	LoginService	loginService;


	@Autowired
	public ProfileController(final AcademiaService academiaService, final AlumnoService alumnoService, final LoginService loginService) {
		this.academiaService = academiaService;
		this.alumnoService = alumnoService;
	}

	@RequestMapping(value = "/editdata", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		try {
			final UserAccount user = LoginService.getPrincipal();

			final Iterator<Authority> Ite = user.getAuthorities().iterator();
			final Authority authority = Ite.next();

			if (authority.getAuthority().equalsIgnoreCase("ALUMNO")) {
				result = new ModelAndView("profile/editdatastudent");
				final Alumno alumno = this.alumnoService.findByUserAccount(user);
				result.addObject("student", alumno);
			} else {
				result = new ModelAndView("profile/editdataacademy");
				final Academia academia = this.academiaService.findByUserAccount(user);
				result.addObject("academy", academia);
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}

		return result;

	}

	@RequestMapping(value = "editdatastudent", method = RequestMethod.POST, params = "save")
	public ModelAndView saveStudent(@Valid final Alumno alumno, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("profile/editdatastudent");
			result.addObject("student", alumno);
			result.addObject("mensaje", "profile.commit.error");
			return result;
		}
		try {
			this.alumnoService.save(alumno);
			result = new ModelAndView("redirect:/");
		} catch (final Exception e) {
			result = new ModelAndView("profile/editdatastudent");
			result.addObject("student", alumno);
			result.addObject("mensaje", "profile.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/editacademy", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAcademy(@Valid final Academia academia, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("profile/editdataacademy");
			result.addObject("academy", academia);
			result.addObject("mensaje", "profile.commit.error");
			return result;
		}
		try {
			this.academiaService.save(academia);
			result = new ModelAndView("redirect:/");
		} catch (final Exception e) {
			result = new ModelAndView("profile/editdataacademy");
			result.addObject("academy", academia);
			result.addObject("mensaje", "profile.commit.error");
		}

		return result;
	}
}
