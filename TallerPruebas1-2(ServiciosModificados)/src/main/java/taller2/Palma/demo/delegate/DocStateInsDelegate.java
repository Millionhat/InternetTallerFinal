package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.wrapper.DSIList;
import taller2.Palma.demo.wrapper.DocumenttList;

@Component
public class DocStateInsDelegate {
	
	private String url="/RestDocStateIns";
	
	@Autowired
	RestTemplate template;
	
	public void createDSI(Docstateinstance dsi) {
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Docstateinstance> entity= new HttpEntity(dsi,header);
		template.postForEntity(url, entity, Docstateinstance.class);
	}

	public Iterable<Docstateinstance> getDroupDSI(){
		List<Docstateinstance> instances= new ArrayList();
		Iterable<Docstateinstance> callmeResponse=null;
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Docstateinstance>> entity= new HttpEntity(instances, headers);
		
		ResponseEntity<DSIList> response= template.getForEntity(url,DSIList.class);
		
		callmeResponse=response.getBody().getInstances();
		
		return callmeResponse;
	}
	
	public Docstateinstance getDSI(long docstatinsId) {
		Docstateinstance dsi= new Docstateinstance();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Docstateinstance> entity= new HttpEntity(dsi,header);
		
		ResponseEntity<Docstateinstance> response= template.getForEntity(url+"/"+docstatinsId,Docstateinstance.class);
		
		return response.getBody();
	}
	
	public void deleteDSI(long docstatinsId) {
		template.delete(url+"/"+docstatinsId);
	}
	
	public void updateDSI(long docstatinsId, Docstateinstance dsi) {
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Docstateinstance> entity= new HttpEntity(dsi,header);
		
		template.put(url+"/"+docstatinsId,entity);
	}
	
	public Iterable<Documentt> getDSIDoc(Documentt doc) {
		String dir=url+"/byDoc/"+doc.getDocId();
		List<Documentt> documents= new ArrayList();
		Iterable<Documentt> callmeResponse= null;
		
		HttpHeaders headers= new HttpHeaders();
		
		ResponseEntity<DocumenttList> response= template.getForEntity(url, DocumenttList.class);
		callmeResponse= response.getBody().getList();
		
		return callmeResponse;
	}
}
