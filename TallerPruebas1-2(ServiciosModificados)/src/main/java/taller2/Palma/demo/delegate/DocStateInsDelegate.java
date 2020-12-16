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

import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.wrapper.DSIList;

@Component
public class DocStateInsDelegate {
	
	private String url="http://localhost:8081/RestDocStateIns/";
	
	@Autowired
	RestTemplate template;
	
	public void createDSI(Docstateinstance dsi) {
//		HttpHeaders header= new HttpHeaders();
//		HttpEntity<Docstateinstance> entity= new HttpEntity(dsi,header);
		
		HttpHeaders header= new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Docstateinstance> entity= new HttpEntity<>(dsi,header);
		
		ResponseEntity<Docstateinstance> response = template.postForEntity(url, entity, Docstateinstance.class);
	}

	public Iterable<Docstateinstance> getDroupDSI(){
		List<Docstateinstance> instances= new ArrayList();
		Iterable<Docstateinstance> callmeResponse=null;
		
//		HttpHeaders headers= new HttpHeaders();
//		HttpEntity<List<Docstateinstance>> entity= new HttpEntity(instances, headers);
		
		
		ResponseEntity<Docstateinstance[]> response= template.getForEntity(url,Docstateinstance[].class);
		
		Docstateinstance[] docstatinstance= response.getBody();
		
		for(int i=0;i<docstatinstance.length;i++) {
			instances.add(docstatinstance[i]);
		}
		
		callmeResponse=instances;
		
		return callmeResponse;
	}
	
	public Docstateinstance getDSI(long docstatinsId) {
		Docstateinstance dsi= new Docstateinstance();
		
//		HttpHeaders header= new HttpHeaders();
//		HttpEntity<Docstateinstance> entity= new HttpEntity(dsi,header);
		
		
		ResponseEntity<Docstateinstance> response= template.getForEntity(url+"/"+docstatinsId,Docstateinstance.class);
		
		return response.getBody();
	}
	
	public void deleteDSI(long docstatinsId) {
		template.delete(url+docstatinsId);
	}
	
	public void updateDSI(long docstatinsId, Docstateinstance dsi) {
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Docstateinstance> entity= new HttpEntity(dsi,header);
		
		template.put(url+docstatinsId,entity);
	}
	
	public Iterable<Documentt> getDSIDoc(Documentt doc) {
		String dir=url+"/byDoc/"+doc.getDocId();
		List<Documentt> documents= new ArrayList();
		Iterable<Documentt> callmeResponse= null;
		
		HttpHeaders headers= new HttpHeaders();
		
		ResponseEntity<Documentt[]> response= template.getForEntity(url, Documentt[].class);
		
		Documentt[] docs=response.getBody();
		for(int i=0;i<docs.length;i++) {
			documents.add(docs[i]);
		}
		
		callmeResponse= documents;
		
		return callmeResponse;
	}
}
