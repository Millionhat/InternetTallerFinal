package taller2.Palma.demo.REST;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.model.Nexuspoll;
import taller2.Palma.demo.service.NexusPollService;

@RestController
@RequestMapping("/nexusPoll/RestNexusPoll")
public class NexusPollRest {
	
	private NexusPollService nps;
	
	public NexusPollRest(NexusPollService nps) {
		this.nps=nps;
	}
	
	@PostMapping(path="/",consumes= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	},produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public void addPoll(@RequestBody Nexuspoll poll) {
		nps.addPoll(poll);
	}
	
	@DeleteMapping(path="/{nexpollId}")
	public void deletePoll(@PathVariable long nexpollId) {
		Optional<Nexuspoll> deleted= nps.getPoll(nexpollId);
		nps.delete(deleted.get());
	}
	
	@PutMapping("/{nexpollId}")
	public void editPoll(@PathVariable long nexpollId, @RequestBody Nexuspoll poll) {
		nps.update(poll);
	}
	
	@GetMapping(value="/")
	public Iterable<Nexuspoll> getPolls(){
		return nps.getPolls();
	}
	
	@GetMapping(path="/{nexpollId}",produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public Nexuspoll getPoll(@PathVariable long nexpollId) {
		return nps.getPoll(nexpollId).get();
	}
}
