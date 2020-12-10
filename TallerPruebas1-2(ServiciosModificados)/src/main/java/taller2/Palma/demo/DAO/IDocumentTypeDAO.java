package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Documenttype;

public interface IDocumentTypeDAO {
	
	public void delete(Documenttype entity);
	
	public List<Documenttype> findAll();
	
	public Documenttype findById(long id);
	
	public void save(Documenttype entity);
	
	public void update(Documenttype entity);

}
