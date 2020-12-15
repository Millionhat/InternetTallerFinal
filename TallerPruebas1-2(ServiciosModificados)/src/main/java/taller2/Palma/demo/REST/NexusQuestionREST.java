package taller2.Palma.demo.REST;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.model.Nexuspoll;
import taller2.Palma.demo.model.Nexusquestion;
import taller2.Palma.demo.service.NexusPollService;
import taller2.Palma.demo.service.NexusQuestionService;

@RestController
@RequestMapping("/RestNexusQuestion")
public class NexusQuestionREST {
	private NexusQuestionService nqs;
	private NexusPollService nps;
	
	public NexusQuestionREST(NexusQuestionService nqs,NexusPollService nps) {
		this.nqs=nqs;
		this.nps=nps;
	}
	
	@PostMapping("/")
	public void createNexuQuestion(@RequestBody Nexusquestion question) {
		nqs.addQuestion(question);
	}
	
	@DeleteMapping("/{nexquesId}")
	public void deleteQuestion(@PathVariable long nexquesId) {
		Optional<Nexusquestion> deleted= nqs.getQuestion(nexquesId);
		if(!deleted.isEmpty()) {
			nqs.delete(deleted.get());
		}
	}
	
	@PutMapping("/{nexquesId}")
	public void editQuestion(@PathVariable long nexquesId, @RequestBody Nexusquestion question) {
		nqs.update(question);
	}
	
	@GetMapping("/{nexquesId}")
	public Nexusquestion getQuestion(@PathVariable long nexquesId) {
		return nqs.getQuestion(nexquesId).get();
	}
	
	@GetMapping("/")
	public Iterable<Nexusquestion> getQuestions(){
		return nqs.getQuestions();
	}
	
	@GetMapping("/poll/{nexpollId}")
	public Iterable<Nexusquestion> getQuestionsbyPoll(@PathVariable long nexpollId){
		Nexuspoll search=nps.getPoll(nexpollId).get();
		return nqs.findQuestionsByPoll(search);
	}
}
