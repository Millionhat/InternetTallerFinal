package taller2.Palma.demo.DAO;

import java.util.List;

import taller2.Palma.demo.model.Person;

public interface IPersonDAO {
	
	public void delete(Person entity);
	
	public List<Person> findAll();
	
	public Person findById(long id);
	
	public void save(Person entity);
	
	public void update(Person entity);
}
