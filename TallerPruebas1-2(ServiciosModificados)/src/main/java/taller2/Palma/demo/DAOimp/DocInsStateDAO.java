package taller2.Palma.demo.DAOimp;

import java.time.Instant;
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

import taller2.Palma.demo.DAO.IDocumentInsStateDAO;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;

@Repository
@Scope("singleton")
public class DocInsStateDAO implements IDocumentInsStateDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	public DocInsStateDAO(EntityManager manager) {
//		entityManager=manager;
//	}
	
	@Transactional
	@Override
	public void delete(Docstateinstance entity) {
		entityManager.remove(entity);
		
	}

	@Transactional(readOnly=true)
	@Override
	public List<Docstateinstance> findAll() {
		String jpql= "Select a from Docstateinstance a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional(readOnly=true)
	@Override
	public Docstateinstance findById(long docstatinsId) {
		return entityManager.find(Docstateinstance.class, docstatinsId);
	}
	
	@Transactional
	@Override
	public void save(Docstateinstance entity) {
		entityManager.persist(entity);
		
	}

	@Transactional
	@Override
	public void update(Docstateinstance entity) {
		entityManager.merge(entity);
		
	}
	
	@Transactional(readOnly=true)
	public List<Docstateinstance> findByDateRange(Date first, Date end){		
		String jpql="SELECT a FROM Docstateinstance a WHERE (a.docstatinsStartdate BETWEEN ?1 AND ?2)"
				+" AND (a.docstatinsEnddate BETWEEN ?1 AND ?2)";
		
		Query q= entityManager.createQuery(jpql);
		q.setParameter(1,first);
		q.setParameter(2,end);
		return q.getResultList();
	}

	@Transactional(readOnly=true)
	public List<Docstateinstance> findByDocumentt(Documentt doc) {
		String jpql="SELECT a FROM Docstateinstance a WHERE a.docId = :doc";
		Query q=entityManager.createQuery(jpql);
		q.setParameter("doc",doc);		
		return q.getResultList();
	}
	
	@Transactional(readOnly=true)
	public List<Docstateinstance> findChanged(){
		ZoneId zone=ZoneId.systemDefault();
		LocalDate now= LocalDate.now().minusDays(14);
		Date limit=Date.from(now.atStartOfDay(zone).toInstant());
		String jpql="SELECT a FROM Docstateinstance a WHERE (a.docstatinsStartdate BETWEEN ?1 AND ?2)";
		
		Query q= entityManager.createQuery(jpql);
		q.setParameter(1,limit);
		q.setParameter(2,Date.from(Instant.now()));
		return q.getResultList();
	}
	
}
