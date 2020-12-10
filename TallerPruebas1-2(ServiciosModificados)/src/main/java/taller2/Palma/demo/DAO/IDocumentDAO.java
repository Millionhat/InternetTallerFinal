package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Documentt;

public interface IDocumentDAO {
	
	public void delete(Documentt entity);
	
	public List<Documentt> findAll();
	
	public Documentt findById(long id);
	
	public void save(Documentt entity);
	
	public void update(Documentt entity);
	
	public List<Documentt> findByDocName(String docName);
}
