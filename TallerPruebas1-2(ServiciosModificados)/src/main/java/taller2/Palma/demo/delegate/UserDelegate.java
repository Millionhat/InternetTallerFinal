package taller2.Palma.demo.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import taller2.Palma.demo.model.Userr;
import taller2.Palma.demo.wrapper.UserList;

@Component
public class UserDelegate {
	
	private String url="/RestUser";
	@Autowired
	RestTemplate template;

	public Iterable<Userr> getGroupUSer(){
		List<Userr> users= new ArrayList();
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Userr>> entity = new HttpEntity(users,headers);
		
		ResponseEntity<UserList> response = template.getForEntity(url, UserList.class);
		
		Iterable<Userr> callmeResponse= response.getBody().getUsers();
		
		return callmeResponse;
	}
	
	public Userr getuser(long userId) {
		Userr user= new Userr();
		
		String urlUser= url + "/" + userId;
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Userr>> entity = new HttpEntity(user,headers);
		
		ResponseEntity<Userr> response = template.getForEntity(urlUser, Userr.class);
		
		return response.getBody();
	}
	
	public void deleteUser(long userId) {
		String urlUser= url + "/" + userId;
		template.delete(urlUser);
	}
	
	public void updateUser(long userId, Userr user) {
		String urlUser= url + "/" + userId;
		
		HttpHeaders headers= new HttpHeaders();
		HttpEntity<List<Userr>> entity = new HttpEntity(user,headers);
		
		template.put(urlUser, entity);
	}
}
