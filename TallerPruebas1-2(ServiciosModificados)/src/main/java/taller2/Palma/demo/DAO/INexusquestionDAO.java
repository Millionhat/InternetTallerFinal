package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Nexusquestion;

public interface INexusquestionDAO {
	
	public void delete(Nexusquestion question);
	
	public List<Nexusquestion> findAll();

	public Nexusquestion findById(long id);
	
	public void save(Nexusquestion question);
	
	public void update(Nexusquestion question);
	
	public List<Nexusquestion> findByPoll(long poll);
}
