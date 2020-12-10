package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
		String url="/RestDocType";
		List<Documenttype> docTypes= new ArrayList();
		Iterable<Documenttype> callmeResponse= null;

//		ObjectMapper mapper= new ObjectMapper();
//		Map<String, String> headers= new HashMap<>();
//		//headers.put(HttpUtils)
//		String ids="";
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Documenttype>> entity= new HttpEntity(docTypes,headers);
		
		ResponseEntity<DocTypeList> response=template.getForEntity(url, DocTypeList.class);
//		ResponseEntity<DocTypeList> response=template.postForEntity(url, entity, DocTypeList.class);
		
		callmeResponse= response.getBody().getList();
		
		return callmeResponse;
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


