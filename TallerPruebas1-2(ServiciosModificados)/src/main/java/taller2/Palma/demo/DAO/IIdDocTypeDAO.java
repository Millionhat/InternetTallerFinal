package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Iddocumenttype;

public interface IIdDocTypeDAO {

	public void delete(Iddocumenttype entity);
	
	public List<Iddocumenttype> findAll();
	
	public Iddocumenttype findByI(long id);
	
	public void save(Iddocumenttype entity);
	
	public void update(Iddocumenttype entity);
}
