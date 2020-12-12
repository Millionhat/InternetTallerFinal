package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Rolee;
import taller2.Palma.demo.model.Userr;
import taller2.Palma.demo.wrapper.RoleList;

@Component
public class RoleDelegate {
	
	private String url="/RestRole";
	@Autowired
	RestTemplate template;
	
	public void createRole(Rolee role) {
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Rolee>> entity = new HttpEntity(role,headers);
		
		template.postForEntity(url, entity, Rolee.class);
		
	}
	
	public Iterable<Rolee> getGroupRoles(){
		List<Rolee> roles= new ArrayList();
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Rolee>> entity = new HttpEntity(roles,headers);
		
		ResponseEntity<RoleList> response = template.getForEntity(url, RoleList.class);
		
		Iterable<Rolee> callmeResponse= response.getBody().getRoles();
		
		return callmeResponse;
	}
	
	public Rolee getRole(long roleId) {
		Rolee role= new Rolee();
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Rolee>> entity = new HttpEntity(role,headers);
		
		ResponseEntity<Rolee> response = template.getForEntity(url, Rolee.class);
		
		return response.getBody();
	}
	
	public void deleteRole(long roleId) {
		String urlRole= url + "/" + roleId;
		template.delete(urlRole);
	}
	public void updateRole(long roleId, Rolee r) {
		String urlRole= url + "/" + roleId;
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Rolee>> entity = new HttpEntity(r,headers);
		
		template.put(url, entity);
	}
}
