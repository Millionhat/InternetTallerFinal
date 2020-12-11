package taller2.Palma.demo.REST;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.PersonService;

@RestController
@RequestMapping("/RestPerson")
public class PersonRest {

	private PersonService service;
	
	public PersonRest(PersonService p) {
		service=p;
	}
	
	@PostMapping("/")
	public void CreatePerson(Person dt)throws NonNullValueException {
		service.addPerson(dt);
	}
	
	@DeleteMapping("{/personId}")
	public void deletePerson(@PathVariable long personId){
		Optional<Person> delet=service.getPerson(personId);
		if (!delet.isEmpty()) {
			service.delete(personId);
		}
	}
	
	@PutMapping("{/personId}")
	public void editPerson(@PathVariable long personId,@RequestBody Person person) throws NonNullValueException{
		service.update(person);
	}
	
	@GetMapping("{/personId}")
	public Person getPerson(@PathVariable long personId) {
		return service.getPerson(personId).get();
	}
	
	@GetMapping("/")
	public Iterable<Person> getPeople(){
		return service.getPeople();
	}
}
