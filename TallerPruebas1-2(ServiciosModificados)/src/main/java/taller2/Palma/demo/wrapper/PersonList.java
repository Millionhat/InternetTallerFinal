package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Person;

public class PersonList {
	
	private List<Person> person;
	
	public PersonList() {
		person= new ArrayList();
	}
	
	public List<Person> getList(){
		return person;
	}
	
	public void setList(List<Person> list) {
		person= list;
	}

}
