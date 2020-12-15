package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.wrapper.PersonList;

@Component
public class PersonDelegate {
	
	@Autowired
	RestTemplate template;

	public void createPerson(Person person) {
		String url="http://localhost:8081/person/RestPerson/";
		
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		
		HttpEntity<Person> entity= new HttpEntity(person,headers);
		
		ResponseEntity<Person> response= template.postForEntity(url, entity,Person.class);
	}
	
	public Iterable<Person> getGroupPersonData(){		
		String url="http://localhost:8081/person/RestPerson/";
		List<Person> person= new ArrayList();
		
//		HttpHeaders header= new HttpHeaders();
//		HttpEntity<List<PersonList>> entity = new HttpEntity(person,header);
		
		ResponseEntity<Person[]> reponse= template.getForEntity(url, Person[].class);
		
		Person[] people= reponse.getBody();
		
		List<Person> cosas= new ArrayList();
		for(int i=0;i<people.length;i++) {
			cosas.add(people[i]);
		}
		
		
		Iterable<Person> callmeResponse = cosas;
		
		return callmeResponse;
	}
	
	public Person getPerson(long personId) {
		String url= "http://localhost:8081/person/RestPerson/" + personId;
		
		Person p= new Person();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity = new HttpEntity(p,header);
		
		ResponseEntity<Person> reponse = template.getForEntity(url, Person.class);
		
		return reponse.getBody();
		
	}
	
	public void updatePerson(long personId,Person p) {
		String url = "http://localhost:8081/person/RestPerson/" + personId;
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity = new HttpEntity(p,header);
		
		template.put(url, entity);
	}
	
	public void deletePerson(long personId) {
		String url = "http://localhost:8081/person/RestPerson/" + personId;
		
		template.delete(url);
	}
}
