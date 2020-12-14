package taller2.Palma.demo.delegate;

import java.awt.PageAttributes.MediaType;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.wrapper.PersonList;

@Component
public class PersonDelegate {
	
	@Autowired
	RestTemplate template;

	public void createPerson(Person person) {
		String url="http://localhost:8081/person/RestPerson";
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity= new HttpEntity(person,header);
		template.postForEntity(url, entity,Person.class);
	}
	
	public Iterable<Person> getGroupPersonData(){		
		String url="http://localhost:8081/person/RestPerson/";
		List<Person> person= new ArrayList();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<List<PersonList>> entity = new HttpEntity(person,header);
		
		ResponseEntity<PersonList> reponse= template.getForEntity(url, PersonList.class);
		
		//Iterable<Person> callmeResponse = reponse.getBody().getList();
		
		//return callmeResponse;
		return null;
	}
	
	public Person getPerson(long personId) {
		String url= "/person/RestPerson/" + personId;
		
		Person p= new Person();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity = new HttpEntity(p,header);
		
		ResponseEntity<Person> reponse = template.getForEntity(url, Person.class);
		
		return reponse.getBody();
		
	}
	
	public void updatePerson(long personId,Person p) {
		String url = "/person/RestPerson/" + personId;
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Person> entity = new HttpEntity(p,header);
		
		template.put(url, entity);
	}
	
	public void deletePerson(long personId) {
		String url = "/person/RestPerson/" + personId;
		
		template.delete(url);
	}
}
