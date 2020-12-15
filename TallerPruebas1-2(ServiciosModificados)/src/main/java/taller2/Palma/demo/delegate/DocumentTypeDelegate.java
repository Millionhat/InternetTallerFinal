package taller2.Palma.demo.delegate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.wrapper.DocTypeList;

@Component
public class DocumentTypeDelegate {
	
	@Autowired
	RestTemplate template;
	
	public Iterable<Documenttype> getGroupDocTypeData(){
		String url="http://localhost:8081/docType/RestDocType/";
		List<Documenttype> docTypes= new ArrayList();
		Iterable<Documenttype> callmeResponse= null;
		
		//HttpHeaders headers= new HttpHeaders();
		//HttpEntity<List<Documenttype>> entity= new HttpEntity(docTypes,headers);
		
		ResponseEntity<Documenttype[]> response=template.getForEntity(url, Documenttype[].class);
		
		Documenttype[] documenttype= response.getBody();
		
		for(int i=0;i<documenttype.length;i++) {
			docTypes.add(documenttype[i]);
		}
		callmeResponse= docTypes;
		
		return callmeResponse;
		//return null;
	}
	
	public void createDocType(Documenttype dt) {
		String url= "http://localhost:8081/docType/RestDocType";

//		URI uri= new URI(url);
//		HttpHeaders header= new HttpHeaders();
//		header.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Documenttype> entity= new HttpEntity<>(dt,header);
//		template.postForEntity(uri,entity,Documenttype.class);
		
		HttpHeaders header= new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Documenttype> entity= new HttpEntity<>(dt,header);
		Documenttype test = template.postForObject(url, entity, Documenttype.class);
		
		
		
	}
	
	
	public Documenttype getDocType(long doctypeId) {
		String url= "/RestDocType/"+doctypeId;
		
		Documenttype dt=new Documenttype();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Documenttype> entity= new HttpEntity(dt,header);
		
		ResponseEntity<Documenttype> response= template.getForEntity(url,Documenttype.class);
//		ResponseEntity<Documenttype> response= template.postForEntity(url, entity, Documenttype.class);
		
		return response.getBody();
	}
	
	public void deleteDocType(long doctypeId) {
		String url="/RestDocType/"+doctypeId;
		
		template.delete(url);
	}
	
	public void updateDocType(long doctypeId, Documenttype updated) {
		
		String url= "/RestDocType/"+doctypeId;
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Documenttype> entity= new HttpEntity(updated,header);
		
		template.put(url, entity);
	}
}


