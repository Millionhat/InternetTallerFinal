package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Rolee;

public interface IRoleDAO {
	
	public void delete(Rolee entity);
	
	public List<Rolee> findAll();
	
	public Rolee findById(long id);
	
	public void save(Rolee entity);
	
	public void update(Rolee entity);
}
