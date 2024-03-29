package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.wrapper.InstitutionList;

@Component
public class InstitutionDelegate {
	
	@Autowired
	RestTemplate template;
	
	public void createInstitution(Institution institution) {
		String url= "http://localhost:8081/RestInstitution";
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<Institution> entity = new HttpEntity(institution,headers);
		
		template.postForEntity(url, entity, Institution.class);
		
		
	}
	
	public Iterable<Institution> getGroupInstitution(){
		String url= "http://localhost:8081/RestInstitution/";
		List<Institution> institutions= new ArrayList();
		
//		HttpHeaders headers= new HttpHeaders();
//		HttpEntity<List<Institution>> entity = new HttpEntity(institutions,headers);
		
		ResponseEntity<Institution[]> response = template.getForEntity(url, Institution[].class);
		
		Institution[] inst= response.getBody();
		for(int i=0;i<inst.length;i++) {
			institutions.add(inst[i]);
		}
		Iterable<Institution> callmeResponse= institutions;
		
		return callmeResponse;
	}
	
	public Institution getInstitution(long insId) {
		String url= "/RestInstitution/" + insId;
		
		HttpHeaders header= new HttpHeaders();
		Institution ins= new Institution();
		HttpEntity<Institution> entity = new HttpEntity(ins,header);
		
		ResponseEntity<Institution> response= template.getForEntity(url, Institution.class);
		
		return response.getBody();
	}
	
	public void deletedInstitution(long insId) {
		String url= "/RestInstitution/" + insId;
		template.delete(url);
	}
	
	public void updateInstitution(long insId,Institution insUpdate) {
		String url= "/RestInstitution/" + insId;
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Institution> entity = new HttpEntity(insUpdate,header);
		template.put(url, entity);
	}

}
