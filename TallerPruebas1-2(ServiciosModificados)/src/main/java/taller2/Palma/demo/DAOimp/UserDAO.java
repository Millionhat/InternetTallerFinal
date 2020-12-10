package taller2.Palma.demo.DAOimp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IUserDAO;
import taller2.Palma.demo.model.Userr;

@Repository
@Scope("singleton")
public class UserDAO implements IUserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void delete(Userr entity) {
		entityManager.remove(entity);

	}

	@Transactional(readOnly=true)
	@Override
	public List<Userr> findAll() {
		String jpql= "Select a from Userr a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Userr findById(long userId) {
		return entityManager.find(Userr.class, userId);
	}

	@Transactional
	@Override
	public void save(Userr entity) {
		entityManager.persist(entity);

	}

	@Transactional
	@Override
	public void update(Userr entity) {
		entityManager.merge(entity);

	}

}
