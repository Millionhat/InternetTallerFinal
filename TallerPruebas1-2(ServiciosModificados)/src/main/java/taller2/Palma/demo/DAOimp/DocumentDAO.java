package taller2.Palma.demo.DAOimp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IDocumentDAO;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;

@Repository
@Scope("singleton")
public class DocumentDAO implements IDocumentDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
//	public DocumentDAO(EntityManager manager) {
//		entityManager=manager;
//	}

	@Override
	public void delete(Documentt entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public List<Documentt> findAll() {
		String jpql= "Select a from Documentt a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Documentt findById(long docId) {
		return entityManager.find(Documentt.class, docId);
	}
	

	@Override
	public void save(Documentt entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Documentt entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public List<Documentt> findByDocName(String docName) {
		String jpql= "SELECT d FROM Documentt d WHERE d.docName = :name";
		Query q= entityManager.createQuery(jpql);
		q.setParameter("name", docName);
		return q.getResultList();
	}

	public List<Documentt> findByType(Documenttype id){
		String jpql= "SELECT d FROM Documentt d WHERE d.documenttype = :id";
		Query q= entityManager.createQuery(jpql);
		q.setParameter("id", id);
		return q.getResultList();
	}
	
	public List<Documentt> findByDateRangeDoc(Date first, Date end){
		String jpql="SELECT a FROM DocstateinstanceWHERE (a.docstatinsStartdate BETWEEN ?1 AND ?2)"
				+" AND (a.docstatinsEnddate BETWEEN ?1 AND ?2)";
		
		Query q= entityManager.createQuery(jpql);
		q.setParameter(1,first);
		q.setParameter(2,end);
		return q.getResultList();
	}

	public List<Documentt> findByPerson(Person person){
		String jpql="SELECT d FROM Documentt d WHERE d.person = :pers";
		Query q= entityManager.createQuery(jpql);
		q.setParameter("pers", person);
		return q.getResultList();
	}
	

}
