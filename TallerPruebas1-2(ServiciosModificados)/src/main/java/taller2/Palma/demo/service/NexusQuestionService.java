package taller2.Palma.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import taller2.Palma.demo.DAOimp.NexusQuestionDAO;
import taller2.Palma.demo.model.Nexuspoll;
import taller2.Palma.demo.model.Nexusquestion;
import taller2.Palma.demo.serviceInt.NexusQuestionServiceInterface;

@Service
public class NexusQuestionService implements NexusQuestionServiceInterface {
	
	private NexusQuestionDAO repo;
	
	public NexusQuestionService(NexusQuestionDAO repo) {
		this.repo=repo;
	}
	@Override
	public void delete(Nexusquestion q) {
		// TODO Auto-generated method stub
		repo.delete(q);
	}

	@Override
	public Nexusquestion addQuestion(Nexusquestion question) {
		// TODO Auto-generated method stub
		repo.save(question);
		return question;
	}

	@Override
	public Nexusquestion update(Nexusquestion question) {
		// TODO Auto-generated method stub
		Nexusquestion edit=repo.findById(question.getNexquesId());
		edit=question;
		repo.update(edit);
		return edit;
		
	}

	@Override
	public Optional<Nexusquestion> getQuestion(long qId) {
		// TODO Auto-generated method stub
		return Optional.of(repo.findById(qId));
	}

	@Override
	public Iterable<Nexusquestion> getQuestions() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Iterable<Nexusquestion> findQuestionsByPoll(Nexuspoll id){
		return repo.findByPoll(id);
	}
}
