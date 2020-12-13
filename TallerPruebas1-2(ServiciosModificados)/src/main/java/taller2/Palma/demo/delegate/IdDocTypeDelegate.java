package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.wrapper.IdDocTypeList;

@Component
public class IdDocTypeDelegate {
	
	@Autowired
	RestTemplate template;
	
	private String url="/RestIDdoc";
	
	public void creatIdDocType(Iddocumenttype idt) {
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<Iddocumenttype> entity= new HttpEntity(idt,headers);
		
		template.postForEntity(url, entity, Iddocumenttype.class);
	}
	
	public Iterable<Iddocumenttype> getGroupTypes(){
		List<Iddocumenttype> idTypes= new ArrayList();
		Iterable<Iddocumenttype> callmeResponse=null;
		
		HttpHeaders headers=new HttpHeaders();
		HttpEntity<List<Iddocumenttype>> entity= new HttpEntity(idTypes,headers);
		
		ResponseEntity<IdDocTypeList> response= template.getForEntity(url, IdDocTypeList.class);
		
		callmeResponse=response.getBody().getList();
		
		return callmeResponse;
	}
	
	public Iddocumenttype getIdDocType(long iddoctypeId) {
		
		Iddocumenttype idt= new Iddocumenttype();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Iddocumenttype> entity=new HttpEntity(idt,header);
		
		ResponseEntity<Iddocumenttype> response= template.getForEntity(url, Iddocumenttype.class);
		return response.getBody();
	}
	
	public void deleteIDT(long iddoctypeId) {
		String dir=url+"/"+iddoctypeId;
		template.delete(dir);
	}
	
	public void updateIDT(long iddoctypeId, Iddocumenttype edited) {
		String dir= url+"/"+iddoctypeId;
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<Iddocumenttype> entity= new HttpEntity(edited,headers);
		
		template.put(dir, entity);
	}
}
