package taller2.Palma.demo.DAOimp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.INexusquestionDAO;
import taller2.Palma.demo.DAO.IVulnerabilityquestionDAO;
import taller2.Palma.demo.model.Nexuspoll;
import taller2.Palma.demo.model.Nexusquestion;

@Repository
@Scope("singleton")
public class NexusQuestionDAO implements INexusquestionDAO {


	@PersistenceContext
	private EntityManager manager;

	@Override
	public void delete(Nexusquestion question) {
		// TODO Auto-generated method stub
		manager.remove(question);
	}


	@Override
	public List<Nexusquestion> findAll() {
		// TODO Auto-generated method stub
		String jpql= "SELECT p FROM Nexuspoll p";
		return manager.createQuery(jpql).getResultList();
	}

	@Override
	public Nexusquestion findById(long nexquesId) {
		// TODO Auto-generated method stub
		return manager.find(Nexusquestion.class, nexquesId);
	}

	@Override
	public void save(Nexusquestion question) {
		// TODO Auto-generated method stub
		manager.persist(question);
	}

	@Override
	public void update(Nexusquestion question) {
		// TODO Auto-generated method stub
		manager.merge(question);
	}

	@Override
	public List<Nexusquestion> findByPoll(Nexuspoll poll) {
		// TODO Auto-generated method stub
		String jpql="SELECT q FROM Nexusquestion q WHERE p.nexuspoll= :id";
		Query query= manager.createQuery(jpql);
		query.setParameter("id", poll);
		return query.getResultList();
	}
}