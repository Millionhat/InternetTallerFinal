package taller2.Palma.demo.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
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
import taller2.Palma.demo.wrapper.PersonList;

@RestController
@RequestMapping("/person/RestPerson")
public class PersonRest {
	
	private PersonService service;
	
	public PersonRest(PersonService p) {
		service=p;
	}
	
	@PostMapping("/person/")
	public void createPerson(Person dt)throws NonNullValueException {
		service.addPerson(dt);
	}
	
	@DeleteMapping("/person/{personId}")
	public void deletePerson(@PathVariable long personId){
		Optional<Person> delet=service.getPerson(personId);
		if (!delet.isEmpty()) {
			service.delete(personId);
		}
	}
	
	@PutMapping("/person/{personId}")
	public void editPerson(@PathVariable long personId,@RequestBody Person person) throws NonNullValueException{
		service.update(person);
	}
	
	@GetMapping("/person/{personId}")
	public Person getPerson(@PathVariable long personId) {
		return service.getPerson(personId).get();
	}
	
	@GetMapping(value= "/")
	public List<Person> getPeople() throws NonNullValueException{
		
		List<Person> people = service.getPeople();
		return people;
	}
}
