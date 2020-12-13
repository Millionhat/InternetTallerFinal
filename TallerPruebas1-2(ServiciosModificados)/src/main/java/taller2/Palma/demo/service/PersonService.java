package taller2.Palma.demo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
	public Person addPerson(Person perso) throws NonNullValueException{
		if(perso.getPersName()=="" || perso.getPersName()==null || perso.getPersLastname()==""||perso.getPersLastname()==null) {
			throw new NonNullValueException();
		}
		repo.save(perso);
		return perso;
	}

	public Optional<Person> getPerson(Long person) throws NoSuchElementException{
		return Optional.of(repo.findById(person));
		
	}

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
	
	public void delete(Long person) {
		Person deleted=repo.findById(person);
		repo.delete(deleted);
	}
	
	public Iterable<Person> getPeople(){
		return repo.findAll();
	}
	
//	public DocTypeId[] getTypes() {
//		return DocTypeId.values();
//	}
}
