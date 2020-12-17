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
import taller2.Palma.demo.model.Person;
@Component
public class DocumentDelegate {
	
	@Autowired
	RestTemplate template;
	
	private String url="http://localhost:8081/docs/RestDocument/";
	
	public void createDocument(Documentt documentt) {
		HttpHeaders header= new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Documentt> entity= new HttpEntity<>(documentt,header);
		ResponseEntity<Documentt> test = template.postForEntity(url, entity, Documentt.class);
	}
	
	public Iterable<Documentt> getGroupDocuments(){
		List<Documentt> docs= new ArrayList();
		Iterable<Documentt> callmeResponse= null;
		
		//HttpHeaders headers= new HttpHeaders();
		//HttpEntity<List<Documentt>> entity= new HttpEntity(docTypes,headers);
		
		ResponseEntity<Documentt[]> response=template.getForEntity(url, Documentt[].class);
		
		Documentt[] documentt= response.getBody();
		
		for(int i=0;i<documentt.length;i++) {
			docs.add(documentt[i]);
		}
		callmeResponse= docs;
		
		return callmeResponse;
	}
	
	
	public Iterable<Documentt> getGroupDocbyType(Documenttype search){
		List<Documentt> docs= new ArrayList();
		Iterable<Documentt> callmeResponse= null;
		
		//HttpHeaders headers= new HttpHeaders();
		//HttpEntity<List<Documentt>> entity= new HttpEntity(docTypes,headers);
		
		ResponseEntity<Documentt[]> response=template.getForEntity(url+"docType/"+search.getDoctypeId(), Documentt[].class);
		
		Documentt[] documentt= response.getBody();
		
		for(int i=0;i<documentt.length;i++) {
			docs.add(documentt[i]);
		}
		callmeResponse= docs;
		
		return callmeResponse;
		
	}
	
	public Documentt getDocument(long docId) {
		
		Documentt doc= new Documentt();
		HttpHeaders headers= new HttpHeaders();
		ResponseEntity<Documentt> response= template.getForEntity(url+docId, Documentt.class);
		
		return response.getBody();
	}
	
	public void deleteDoc(long docId) {

		template.delete(url+docId);
	}
	
	public void updateDoc(long docId,Documentt updated) {
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Documentt> entity= new HttpEntity(updated,header);
		template.put(url+docId, entity);
	}
	
	public Iterable<Documentt> getGroupDocByPerson(Person person){
		List<Documentt> docs= new ArrayList();
		Iterable<Documentt> callmeResponse= null;
		
		//HttpHeaders headers= new HttpHeaders();
		//HttpEntity<List<Documentt>> entity= new HttpEntity(docTypes,headers);
		
		ResponseEntity<Documentt[]> response=template.getForEntity(url+"person/"+person.getPersId(), Documentt[].class);
		
		Documentt[] documentt= response.getBody();
		
		for(int i=0;i<documentt.length;i++) {
			docs.add(documentt[i]);
		}
		callmeResponse= docs;
		
		return callmeResponse;
	}
}
