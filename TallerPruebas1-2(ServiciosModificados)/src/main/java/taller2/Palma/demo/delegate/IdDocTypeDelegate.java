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
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.wrapper.IdDocTypeList;

@Component
public class IdDocTypeDelegate {
	
	@Autowired
	RestTemplate template;
	
	private String url="http://localhost:8081/RestIDdoc/";
	
	public void creatIdDocType(Iddocumenttype idt) {
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		
		HttpEntity<Iddocumenttype> entity= new HttpEntity(idt,headers);
		
		ResponseEntity<Iddocumenttype> ans=template.postForEntity(url, entity, Iddocumenttype.class);
	}
	
	public Iterable<Iddocumenttype> getGroupTypes(){
		List<Iddocumenttype> docTypes= new ArrayList();
		Iterable<Iddocumenttype> callmeResponse= null;
		
		//HttpHeaders headers= new HttpHeaders();
		//HttpEntity<List<IdIddocumenttype>> entity= new HttpEntity(docTypes,headers);
		
		ResponseEntity<Iddocumenttype[]> response=template.getForEntity(url, Iddocumenttype[].class);
		
		Iddocumenttype[] IdIddocumenttype= response.getBody();
		
		for(int i=0;i<IdIddocumenttype.length;i++) {
			docTypes.add(IdIddocumenttype[i]);
		}
		callmeResponse= docTypes;
		
		return callmeResponse;
	}
	
	public Iddocumenttype getIdDocType(long iddoctypeId) {
		
		Iddocumenttype dt=new Iddocumenttype();
		
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Iddocumenttype> entity= new HttpEntity(dt,header);
		
		ResponseEntity<Iddocumenttype> response= template.getForEntity((url+iddoctypeId),Iddocumenttype.class);
//		ResponseEntity<Iddocumenttype> response= template.postForEntity(url, entity, Iddocumenttype.class);
		
		return response.getBody();
	}
	
	public void deleteIDT(long iddoctypeId) {
		String dir=url+iddoctypeId;
		template.delete(dir);
	}
	
	public void updateIDT(long iddoctypeId, Iddocumenttype edited) {
		String dir= url+"/"+iddoctypeId;
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<Iddocumenttype> entity= new HttpEntity(edited,headers);
		
		template.put(dir, entity);
	}
}
