package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Userr;

public interface IUserDAO {
	
	public void delete(Userr entity);
	public List<Userr> findAll();
	public Userr findById(long id);
	public void save(Userr entity);
	public void update(Userr entity);

}
