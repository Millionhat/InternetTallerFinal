package taller2.Palma.demo.repository;

import org.springframework.data.repository.CrudRepository;

import taller2.Palma.demo.model.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

}
