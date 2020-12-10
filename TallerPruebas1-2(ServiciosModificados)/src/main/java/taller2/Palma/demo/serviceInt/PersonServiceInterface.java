package taller2.Palma.demo.serviceInt;

import java.util.NoSuchElementException;
import java.util.Optional;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Person;

public interface PersonServiceInterface {

	public Person addPerson(Person perso) throws NonNullValueException;
	public Optional<Person> getPerson(Long person) throws NoSuchElementException;
	public Person update(Person add) throws NonNullValueException;
	public void delete(Long person);
}
