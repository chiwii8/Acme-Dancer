
package controllers.administrador;

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
import domain.Estilo;
import services.EstiloService;

@Controller
@RequestMapping("/administrator/style")
public class EstiloAdministradorController extends AbstractController {

	/// Cargamos los servicios que emplea la vista
	EstiloService estiloService;


	@Autowired
	public EstiloAdministradorController(final EstiloService estiloService) {
		this.estiloService = estiloService;

	}

	/// Listado
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Estilo> estilos;

		estilos = this.estiloService.findAll();
		result = new ModelAndView("style/list");
		result.addObject("styles", estilos);

		return result;
	}

	/// Creado
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Estilo estilo;

		estilo = this.estiloService.create();
		result = this.createEditModelAndView(estilo);

		return result;

	}

	/// Editar
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int estiloId) {
		ModelAndView result;
		Estilo estilo;

		estilo = this.estiloService.findById(estiloId);
		Assert.notNull(estilo);
		result = this.createEditModelAndView(estilo);

		return result;
	}

	/// Guardar
	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = "save")
	public ModelAndView save(@Valid final Estilo estilo, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(estilo);
		else
			try {
				this.estiloService.save(estilo);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable e) {

				result = this.createEditModelAndView(estilo, "estilo.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET, params = "delete")
	public ModelAndView delete(final Estilo estilo, final BindingResult binding) {
		ModelAndView result;

		try {
			this.estiloService.delete(estilo);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable e) {
			result = this.createEditModelAndView(estilo, "style.commit.error");
		}

		return result;
	}

	//// Métodos que controlan las imágenes
	@RequestMapping(value = "/listImages", method = RequestMethod.GET)
	public ModelAndView listImages(@RequestParam int id) {
		ModelAndView result;
		Estilo estilo;

		Collection<String> images;

		estilo = this.estiloService.findById(id);
		images = estilo.getImagenes();

		result = new ModelAndView("style/listImages");

		result.addObject("images", images);

		return result;

	}

	//// Métodos que controlan las imágenes
	@RequestMapping(value = "/listVideos", method = RequestMethod.GET)
	public ModelAndView listVideos(@RequestParam int id) {
		ModelAndView result;
		Estilo estilo;

		Collection<String> videos;

		estilo = this.estiloService.findById(id);
		videos = estilo.getVideos();

		result = new ModelAndView("style/listVideos");

		result.addObject("videos", videos);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Estilo estilo) {
		ModelAndView result;

		result = new ModelAndView("style/edit");

		result.addObject("style", estilo);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Estilo estilo, final String mensaje) {
		ModelAndView result;

		result = new ModelAndView("style/edit");

		result.addObject("style", estilo);
		result.addObject("mensaje", mensaje);

		return result;
	}

}
