package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IDocumentStateDAO;
import taller2.Palma.demo.model.Documentstate;

@Repository
@Scope("singleton")
public class DocStateDAO implements IDocumentStateDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	public DocStateDAO(EntityManager manager) {
//		entityManager=manager;
//	}
	

	@Override
	public void delete(Documentstate entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public List<Documentstate> findAll() {
		String jpql="Select a from Documentstate a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Documentstate findById(long docstatId) {
		return entityManager.find(Documentstate.class,docstatId);
	}

	@Override
	public void save(Documentstate entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Documentstate entity) {
		entityManager.merge(entity);
		
	}

}
