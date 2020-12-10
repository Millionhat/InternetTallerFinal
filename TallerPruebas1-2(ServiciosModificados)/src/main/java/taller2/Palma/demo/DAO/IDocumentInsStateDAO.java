package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Docstateinstance;

public interface IDocumentInsStateDAO {

	public void delete(Docstateinstance entity);
	
	public List<Docstateinstance> findAll();
	
	public  Docstateinstance findById(long id);
	
	public void save(Docstateinstance entity);

	public void update(Docstateinstance entity);
}
