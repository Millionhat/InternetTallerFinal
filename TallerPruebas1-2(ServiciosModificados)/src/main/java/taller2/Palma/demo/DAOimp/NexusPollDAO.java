package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.INexusPollDAO;
import taller2.Palma.demo.model.Nexuspoll;

@Repository
@Scope("singleton")
public class NexusPollDAO implements INexusPollDAO{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public void delete(Nexuspoll poll) {
		// TODO Auto-generated method stub
		manager.remove(poll);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Nexuspoll> findAll() {
		// TODO Auto-generated method stub
		String jpql= "SELECT p FROM Nexuspoll p";
		return manager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Nexuspoll findById(long nexpollId) {
		// TODO Auto-generated method stub
		return manager.find(Nexuspoll.class, nexpollId);
	}

	@Transactional
	@Override
	public void save(Nexuspoll poll) {
		// TODO Auto-generated method stub
		manager.persist(poll);
	}

	@Override
	public void update(Nexuspoll poll) {
		// TODO Auto-generated method stub
		manager.merge(poll);
	}

	
}
