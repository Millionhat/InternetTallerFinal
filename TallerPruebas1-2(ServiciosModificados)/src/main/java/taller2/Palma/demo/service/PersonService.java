package taller2.Palma.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAOimp.PersonDAO;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.DocTypeId;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.repository.PersonRepository;
import taller2.Palma.demo.serviceInt.PersonServiceInterface;


@Service
public class PersonService implements PersonServiceInterface{
	
	private PersonDAO repo;
	
	public PersonService(PersonDAO repo) {
		this.repo=repo;
	}

	@Transactional
	public Person addPerson(Person perso) throws NonNullValueException{
		if(perso.getPersName()=="" || perso.getPersName()==null || perso.getPersLastname()==""||perso.getPersLastname()==null) {
			throw new NonNullValueException();
		}
		repo.save(perso);
		return perso;
	}

	@Transactional(readOnly = true)
	public Optional<Person> getPerson(Long person) throws NoSuchElementException{
		return Optional.of(repo.findById(person));
		
	}

	@Transactional
	public Person update(Person add) throws NonNullValueException {
		// TODO Auto-generated method stub
		if(add.getPersName()!=""||add.getPersName()!=null||add.getPersLastname()!=""||add.getPersLastname()!=null) {
			Person existing= repo.findById(add.getPersId());
			existing=add;
			repo.update(existing);
			return add;
		}else {
			throw new NonNullValueException();
		}
	}
	
	@Transactional
	public void delete(Long person) {
		Person deleted=repo.findById(person);
		repo.delete(deleted);
	}
	
	@Transactional(readOnly = true)
	public List<Person> getPeople(){
		return repo.findAll();
	}
	
//	public DocTypeId[] getTypes() {
//		return DocTypeId.values();
//	}
}
