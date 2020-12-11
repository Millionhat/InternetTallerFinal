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
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.wrapper.DSIList;

@Component
public class DocStateInsDelegate {
	
	private String url="/RestDocStateIns";
	@Autowired
	RestTemplate template;

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
}
