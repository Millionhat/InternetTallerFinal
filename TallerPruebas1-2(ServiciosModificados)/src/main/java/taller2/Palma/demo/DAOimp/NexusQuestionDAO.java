package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.INexusquestionDAO;
import taller2.Palma.demo.model.Nexusquestion;

public class NexusQuestionDAO implements INexusquestionDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public void delete(Nexusquestion question) {
		// TODO Auto-generated method stub
		manager.remove(question);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Nexusquestion> findAll() {
		// TODO Auto-generated method stub
		String jpql= "SELECT p FROM Nexuspoll p";
		return manager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Nexusquestion findById(long nexquesId) {
		// TODO Auto-generated method stub
		return manager.find(Nexusquestion.class, nexquesId);
	}

	@Transactional
	@Override
	public void save(Nexusquestion question) {
		// TODO Auto-generated method stub
		manager.persist(question);
	}

	@Transactional
	@Override
	public void update(Nexusquestion question) {
		// TODO Auto-generated method stub
		manager.merge(question);
	}

	@Transactional
	@Override
	public List<Nexusquestion> findByPoll(long poll) {
		// TODO Auto-generated method stub
		String jpql="SELECT q FROM Nexusquestion q WHERE p.nexuspoll= :id";
		Query query= manager.createQuery(jpql);
		query.setParameter("id", poll);
		return query.getResultList();
	}

}
