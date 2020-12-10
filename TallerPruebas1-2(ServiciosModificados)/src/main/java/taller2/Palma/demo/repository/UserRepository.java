package taller2.Palma.demo.repository;

import org.springframework.data.repository.CrudRepository;

import taller2.Palma.demo.model.Userr;

public interface UserRepository extends CrudRepository<Userr,Long>{
	Userr findByUserName(String username);
}
