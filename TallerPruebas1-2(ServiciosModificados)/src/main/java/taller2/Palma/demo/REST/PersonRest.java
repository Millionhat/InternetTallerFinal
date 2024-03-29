package taller2.Palma.demo.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping(path="/",consumes= {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	
	public ResponseEntity<Person> createPerson(@RequestBody Person p)throws NonNullValueException {
		

		Person p1= new Person();
		p1.setPersName(p.getPersName());
		p1.setPersLastname(p.getPersLastname());
		p1.setPersEmail(p.getPersEmail());
		p1.setPersIddocument(p.getPersIddocument());

		
		service.addPerson(p1);
		
		return new ResponseEntity<Person>(p,HttpStatus.OK);
	}
	
	@DeleteMapping("/{personId}")
	public void deletePerson(@PathVariable long personId){
		Optional<Person> delet=service.getPerson(personId);
		if (!delet.isEmpty()) {
			service.delete(personId);
		}
	}
	
	@PutMapping("/{personId}")
	public void editPerson(@PathVariable long personId,@RequestBody Person person) throws NonNullValueException{
		service.update(person);
	}
	
	@GetMapping("/{personId}")
	public Person getPerson(@PathVariable long personId) {
		return service.getPerson(personId).get();
	}
	
	@GetMapping(value= "/")
	public List<Person> getPeople() throws NonNullValueException{
		
		List<Person> people = service.getPeople();
		return people;
	}
}
