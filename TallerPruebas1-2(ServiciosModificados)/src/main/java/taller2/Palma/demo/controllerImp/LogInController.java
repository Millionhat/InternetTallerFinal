package taller2.Palma.demo.controllerImp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import taller2.Palma.demo.model.Userr;

@Controller
public class LogInController {
	@RequestMapping("/login")
	public String login(Model model) {
		return "/login";
	}
	
}
