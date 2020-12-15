package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.model.Nexusquestion;

public interface NexusQuestionServiceInterface {
	
	public void delete(Nexusquestion q);
	public Nexusquestion addQuestion(Nexusquestion question);
	public Nexusquestion update(Nexusquestion question);
	public Optional<Nexusquestion> getQuestion(long qId);
	public Iterable<Nexusquestion> getQuestions();

}
