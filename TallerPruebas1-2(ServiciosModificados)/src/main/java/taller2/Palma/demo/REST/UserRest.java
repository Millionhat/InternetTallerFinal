package taller2.Palma.demo.REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.model.Userr;
import taller2.Palma.demo.service.UserService;

@RestController
@RequestMapping("/RestUser")
public class UserRest {
	
	private UserService service;
	
	public UserRest(UserService s) {
		service= s;
	}
	
	@PostMapping("/")
	public void createUser(@RequestBody Userr user) {
		service.save(user);
	}
	
	
	//no se si hacer los metodos faltantes en el service
	
	@GetMapping("{/userId}")
	public Userr getUser(@PathVariable long userId){
		return service.getUser(userId).get();
	}
	
}
