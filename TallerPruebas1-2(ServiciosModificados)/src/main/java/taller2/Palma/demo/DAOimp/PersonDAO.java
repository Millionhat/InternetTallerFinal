package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IPersonDAO;
import taller2.Palma.demo.model.Person;

@Repository
@Scope("singleton")
public class PersonDAO implements IPersonDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
//	public PersonDAO(EntityManager manager) {
//		entityManager = manager;
//	}
	

	@Override
	public void delete(Person entity) {
		entityManager.remove(entity);
		
	}


	@Override
	public List<Person> findAll() {
		String jpql ="SELECT p FROM Person p";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Person findById(long persId) {
		return entityManager.find(Person.class, persId);
	}
	
	@Override
	public void save(Person entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Person entity) {
		entityManager.merge(entity);
		
	}
	
	public List<Person> findByName(String name){
		String jpql= "SELECT p FROM Person p WHERE p.persName= :name";
		Query q= entityManager.createQuery(jpql);
		q.setParameter("name", name);
		return q.getResultList();
	}
	

	public List<Person> findByLastName(String lname){
		String jpql="SELECT p FROM Person p WHERE p.persLastname= :lname";
		Query q= entityManager.createQuery(jpql);
		q.setParameter("lname", lname);
		return q.getResultList();
	}
	

	public List<Person> findByEmail(String mail) {
		String jpql="SELECT p FROM Person p WHERE p.persEmail= :mail";
		Query q= entityManager.createQuery(jpql);
		q.setParameter("mail", mail);
		return q.getResultList();
	}

}
