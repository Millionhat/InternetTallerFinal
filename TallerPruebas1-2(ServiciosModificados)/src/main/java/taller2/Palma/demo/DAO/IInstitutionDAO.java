package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Institution;

public interface IInstitutionDAO {

	public void delete(Institution entity);
	
	public List<Institution> findAll();
	
	public Institution findById(long id);
	
	public void save(Institution entity);
	
	public void update(Institution entity);
}
