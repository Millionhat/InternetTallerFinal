package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IInstitutionDAO;
import taller2.Palma.demo.model.Institution;

@Repository
@Scope("singleton")
public class InstitutionDAO implements IInstitutionDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
//	public InstitutionDAO(EntityManager manager) {
//		entityManager=manager;
//	}

	@Transactional
	@Override
	public void delete(Institution entity) {
		entityManager.remove(entity);
		
	}

	@Transactional(readOnly=true)
	@Override
	public List<Institution> findAll() {
		String jpql="Select a from Institution a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Institution findById(long instId) {
		return entityManager.find(Institution.class, instId);
	}

	@Transactional
	@Override
	public void save(Institution entity) {
		entityManager.persist(entity);
		
	}

	@Transactional
	@Override
	public void update(Institution entity) {
		entityManager.merge(entity);
		
	}
	
}
