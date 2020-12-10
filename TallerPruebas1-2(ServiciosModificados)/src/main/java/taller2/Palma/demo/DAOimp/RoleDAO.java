package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IRoleDAO;
import taller2.Palma.demo.model.Rolee;

@Repository
@Scope("singleton")
public class RoleDAO implements IRoleDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void delete(Rolee entity) {
		entityManager.remove(entity);

	}

	@Transactional(readOnly=true)
	@Override
	public List<Rolee> findAll() {
		String jpql="Select a from Rolee a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Rolee findById(long roleId) {
		return entityManager.find(Rolee.class, roleId);
	}

	@Transactional
	@Override
	public void save(Rolee entity) {
		entityManager.persist(entity);

	}

	@Transactional
	@Override
	public void update(Rolee entity) {
		entityManager.merge(entity);
	}

}
