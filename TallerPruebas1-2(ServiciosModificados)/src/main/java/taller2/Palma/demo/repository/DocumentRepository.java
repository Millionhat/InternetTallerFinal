package taller2.Palma.demo.repository;

import org.springframework.data.repository.CrudRepository;

import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;

public interface DocumentRepository extends CrudRepository<Documentt,Long>{
	
	Iterable<Documentt> findByPerson(Person person);
	
	Iterable<Documentt> findByDocumenttype(Documenttype type);
}
