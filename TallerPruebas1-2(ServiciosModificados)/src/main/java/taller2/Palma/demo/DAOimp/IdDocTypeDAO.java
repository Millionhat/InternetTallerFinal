package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IIdDocTypeDAO;
import taller2.Palma.demo.model.Iddocumenttype;

@Repository
@Scope("singleton")
public class IdDocTypeDAO implements IIdDocTypeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	public IdDocTypeDAO(EntityManager manager) {
//		entityManager=manager;
//	}
	
	@Override
	public void delete(Iddocumenttype entity) {
		entityManager.remove(entity);

	}

	@Override
	public List<Iddocumenttype> findAll() {
		String jpql = "Select a from Iddocumenttype a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Iddocumenttype findByI(long iddoctypeId) {
		return entityManager.find(Iddocumenttype.class, iddoctypeId);
	}

	@Override
	public void save(Iddocumenttype entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(Iddocumenttype entity) {
		entityManager.merge(entity);
	}

}
