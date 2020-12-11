package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.wrapper.DocumenttList;

@Component
public class DocumentDelegate {
	
	@Autowired
	RestTemplate template;
	
	public Iterable<Documentt> getGroupDocuments(){
		String url="/RestDocument";
		List<Documentt> documents= new ArrayList();
		Iterable<Documentt> callmeResponse= null;
		
		HttpHeaders headers= new HttpHeaders();
		
		ResponseEntity<DocumenttList> response= template.getForEntity(url, DocumenttList.class);
		callmeResponse= response.getBody().getList();
		
		return callmeResponse;
	}
	
	public Documentt getDocument(long docId) {
		String url="/RestDocument/"+docId;
		
		Documentt doc= new Documentt();
		HttpHeaders headers= new HttpHeaders();
		ResponseEntity<Documentt> response= template.getForEntity(url, Documentt.class);
		
		return response.getBody();
	}
	
	public void deleteDoc(long docId) {
		String url= "/RestDocument/"+docId;
		template.delete(url);
	}
	
	public void updateDoc(long docId,Documentt updated) {
		String url= "/RestDocument/"+docId;
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Documentt> entity= new HttpEntity(updated,header);
		template.put(url, entity);
	}
}
