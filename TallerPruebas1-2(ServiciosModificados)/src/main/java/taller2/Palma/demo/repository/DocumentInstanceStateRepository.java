package taller2.Palma.demo.repository;

import org.springframework.data.repository.CrudRepository;

import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;

public interface DocumentInstanceStateRepository extends CrudRepository<Docstateinstance,Long>{
	Iterable<Docstateinstance> findByDocumentt(Documentt doc);
}
