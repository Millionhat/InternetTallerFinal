package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IDocumentTypeDAO;
import taller2.Palma.demo.model.Documenttype;

@Repository
@Scope("singleton")
public class DocTypeDAO implements IDocumentTypeDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

//	public DocTypeDAO(EntityManager manager) {
//		entityManager = manager;
//	}
	@Transactional
	@Override
	public void delete(Documenttype entity) {
		entityManager.remove(entity);
		
	}

	@Transactional(readOnly=true)
	@Override
	public List<Documenttype> findAll() {
		String jpql = "Select a from Documenttype a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Documenttype findById(long doctypeId) {
		return entityManager.find(Documenttype.class, doctypeId);
	}

	@Transactional
	@Override
	public void save(Documenttype entity) {
		entityManager.persist(entity);
		
	}

	@Transactional
	@Override
	public void update(Documenttype entity) {
		entityManager.merge(entity);
		
	}

	
}
