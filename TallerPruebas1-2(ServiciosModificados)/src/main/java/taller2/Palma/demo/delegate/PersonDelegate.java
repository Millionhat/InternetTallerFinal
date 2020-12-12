package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.wrapper.PersonList;

public class PersonDelegate {
	
	@Autowired
	RestTemplate template;
	
	public void createPerson(Person person) {
		String url="/RestPerson";
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity= new HttpEntity(person,header);
		template.postForEntity(url, entity,Person.class);
	}
	
	public Iterable<Person> getGroupPersonData(){		
		//falta la URL 
		String url="/RestPerson";
		List<Person> person= new ArrayList();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<List<PersonList>> entity = new HttpEntity(person,header);
		
		ResponseEntity<PersonList> reponse= template.getForEntity(url, PersonList.class);
		
		Iterable<Person> callmeResponse = reponse.getBody().getList();
		
		return callmeResponse;
		
	}
	
	public Person getPerson(long personId) {
		String url= "/RestPerson/" + personId;
		
		Person p= new Person();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity = new HttpEntity(p,header);
		
		ResponseEntity<Person> reponse = template.getForEntity(url, Person.class);
		
		return reponse.getBody();
		
	}
	
	public void updatePerson(long personId,Person p) {
		String url = "/RestPerson/" + personId;
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity = new HttpEntity(p,header);
		
		template.put(url, entity);
	}
	
	public void deletePerson(long personId) {
		String url = "/RestPerson/" + personId;
		
		template.delete(url);
	}
}
