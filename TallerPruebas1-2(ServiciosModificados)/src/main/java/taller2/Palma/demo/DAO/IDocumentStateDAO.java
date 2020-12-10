package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Documentstate;

public interface IDocumentStateDAO {

	public void delete(Documentstate entity);
	
	public List<Documentstate> findAll();
	
	public Documentstate findById(long id);
	
	public void save(Documentstate entity);
	
	public void update(Documentstate entity);
}
