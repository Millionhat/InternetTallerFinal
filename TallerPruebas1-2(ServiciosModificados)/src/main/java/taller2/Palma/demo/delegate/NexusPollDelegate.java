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

import taller2.Palma.demo.model.Nexuspoll;

@Component
public class NexusPollDelegate {
	
	@Autowired
	RestTemplate template;
	
	public Iterable<Nexuspoll> getPolls(){
		String url="http://localhost:8081/nexusPoll/RestNexusPoll/";
		List<Nexuspoll> pollsList= new ArrayList();
		Iterable<Nexuspoll> callmeResponse=null;
		
		ResponseEntity<Nexuspoll[]> response=template.getForEntity(url, Nexuspoll[].class);
		
		Nexuspoll[] poll= response.getBody();
		
		for(int i=0;i<poll.length;i++) {
			pollsList.add(poll[i]);
		}
		callmeResponse=pollsList;
		
		return callmeResponse;
	}
	
	public void createPoll(Nexuspoll poll) {
		String url="http://localhost:8081/nexusPoll/RestNexusPoll/";
		
		HttpHeaders header= new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Nexuspoll> entity= new HttpEntity<>(poll,header);
		ResponseEntity<Nexuspoll> add=template.postForEntity(url, entity, Nexuspoll.class);
	}
	
	public Nexuspoll getPoll(long pollId) {
		String url="http://localhost:8081/nexusPoll/RestNexusPoll/"+pollId;
		
		Nexuspoll poll= new Nexuspoll();
		HttpHeaders header= new HttpHeaders();
		HttpEntity<Nexuspoll> entity= new HttpEntity<>(poll,header);
		ResponseEntity<Nexuspoll> response= template.getForEntity(url,Nexuspoll.class);
		
		return response.getBody();
	}
	
	public void deletePoll(long pollId) {
		String url="http://localhost:8081/nexusPoll/RestNexusPoll/"+pollId;
		template.delete(url);
	}
	
	public void updatePoll(long pollId,Nexuspoll poll) {
		String url="http://localhost:8081/nexusPoll/RestNexusPoll/"+pollId;
		
		HttpHeaders header=new HttpHeaders();
		HttpEntity<Nexuspoll> entity= new HttpEntity(poll,header);
		template.put(url, entity);
	}
}
