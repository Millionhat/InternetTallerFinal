package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Nexuspoll;



public interface INexusPollDAO {
	
	
	public void delete(Nexuspoll poll);
	
	public List<Nexuspoll> findAll();

	public Nexuspoll findById(long id);
	
	public void save(Nexuspoll poll);
	
	public void update(Nexuspoll poll);
	
}
