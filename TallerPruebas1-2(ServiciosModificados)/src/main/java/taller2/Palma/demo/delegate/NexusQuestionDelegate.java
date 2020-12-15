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

import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Nexusquestion;

@Component
public class NexusQuestionDelegate {

	@Autowired
	RestTemplate template;

	public Iterable<Nexusquestion> getQuestions(){
		String url="http://localhost:8081/nexusQuestion/RestNexusQuestion/";
		List<Nexusquestion> questions= new ArrayList();
		Iterable<Nexusquestion> callmeResponse=null;

		ResponseEntity<Nexusquestion[]> response= template.getForEntity(url,Nexusquestion[].class);
		Nexusquestion[] result=response.getBody();

		for(int i=0; i<result.length;i++) {
			questions.add(result[i]);
		}
		callmeResponse= questions;

		return callmeResponse;
	}

	public void createQuestion(Nexusquestion question) {
		String url="http://localhost:8081/nexusQuestion/RestNexusQuestion/";

		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Nexusquestion> entity= new HttpEntity<>(question,header);
		ResponseEntity<Nexusquestion> result = template.postForEntity(url, entity, Nexusquestion.class);

	}
	public Nexusquestion getQuestion(long id) {
		String url="http://localhost:8081/nexusQuestion/RestNexusQuestion/"+id;

		Nexusquestion q= new Nexusquestion();

		HttpHeaders header=new HttpHeaders();
		HttpEntity<Nexusquestion> entity= new HttpEntity<>(q,header);
		ResponseEntity<Nexusquestion> response=template.getForEntity(url,Nexusquestion.class);

		return response.getBody();
	}

	public void delete(long id) {
		String url="http://localhost:8081/nexusQuestion/RestNexusQuestion/"+id;

		template.delete(url);
	}

	public void updateQuestion(long id, Nexusquestion question) {
		String url="http://localhost:8081/nexusQuestion/RestNexusQuestion/"+id;

		HttpHeaders header= new HttpHeaders();
		HttpEntity<Nexusquestion> entity= new HttpEntity<>(question,header);

		template.put(url,entity);
	}

	public Iterable<Nexusquestion> getQuestionsPoll(long poll) {
		String url="http://localhost:8081/nexusQuestion/RestNexusQuestion/poll/"+poll;
		List<Nexusquestion> questions= new ArrayList();
		Iterable<Nexusquestion> callmeResponse=null;

		ResponseEntity<Nexusquestion[]> response= template.getForEntity(url,Nexusquestion[].class);
		Nexusquestion[] result=response.getBody();

		for(int i=0; i<result.length;i++) {
			questions.add(result[i]);
		}
		callmeResponse= questions;

		return callmeResponse;
	}
}