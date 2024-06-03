package controllers.comun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/privacy")
public class PrivacidadController {

    @RequestMapping(value = "polite", method = RequestMethod.GET)
    public ModelAndView polite() {
        ModelAndView result;

        result = new ModelAndView("privacy/polite");

        return result;
    }
}