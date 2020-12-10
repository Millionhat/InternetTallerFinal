package com.example.demo.DAO;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAO.IInstitutionDAO;
import taller2.Palma.demo.DAOimp.IdDocTypeDAO;
import taller2.Palma.demo.DAOimp.InstitutionDAO;
import taller2.Palma.demo.DAOimp.PersonDAO;
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.model.Person;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class PersonDAOTest {
	

	private static Person per2 = new Person();
	private static Iddocumenttype cc= new Iddocumenttype();
	private static Institution inst= new Institution();
	
	@Autowired
	private PersonDAO people;
	
	@Autowired
	private InstitutionDAO ins;
	
	@Autowired
	private IdDocTypeDAO dt;



	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAddPerson() {

		//when(people.findById(per1.getPersId())).thenReturn(per1);
		Person per1 =new Person();
		per1.setPersName("hola");
		per1.setPersLastname("bye");
		per1.setPersEmail("j@gmail.com");
		per1.setPersIddocument("111111");
		people.save(per1);
		Person test=people.findById(per1.getPersId());
		assertTrue(test.getPersName()=="hola");
		
		people.delete(per1);
		
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAddPerson2() {
		//when(people.findById(per2.getPersId())).thenReturn(per2);
		
		per2.setPersName("adios");
		per2.setPersLastname("hi");
		per2.setPersEmail("a@gmail.com");
		per2.setPersIddocument("222222");
		people.save(per2);
		Person test=people.findById(per2.getPersId());
		assertTrue(test.getPersIddocument()=="222222");
		people.delete(per2);
		
	}
	

	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Edit")
	void testUpdate() {
		Person per1 =new Person();
		per1.setPersName("hola");
		per1.setPersLastname("bye");
		per1.setPersEmail("j@gmail.com");
		per1.setPersIddocument("111111");
		people.save(per1);
		Person update=new Person();
		update.setPersId(per1.getPersId());
		update.setPersName(per1.getPersName());
		update.setPersEmail("a@gmail.com");
		//when(people.findById(per1.getPersId())).thenReturn(update);
		people.update(update);
		Person test=people.findById(update.getPersId());
		
		assertNotNull(test.getPersEmail());
		assertFalse(test.getPersEmail().equals(per1.getPersEmail()));
		people.delete(per1);
		people.delete(update);
	}

	

	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Delete")
	void testDelete() {
		Person per1 =new Person();
		per1.setPersName("hola");
		per1.setPersLastname("bye");
		per1.setPersEmail("j@gmail.com");
		per1.setPersIddocument("111111");
		people.save(per1);
		Person per3 =new Person();
		per3.setPersName("adios");
		per3.setPersLastname("hi");
		per3.setPersEmail("a@gmail.com");
		per3.setPersIddocument("222222");
		people.save(per3);
		/**List<Person> lista= new ArrayList<Person>();
		when(people.findAll()).thenReturn(lista);*/
		people.delete(per1);
		people.delete(per3);
		
		List<Person> test= people.findAll();
		
		assertTrue(test.size()==0);
		
	}
	
	@Test
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Query")
	void testFindName() {
		Person per1 =new Person();
		per1.setPersName("hola");
		per1.setPersLastname("bye");
		per1.setPersEmail("j@gmail.com");
		per1.setPersIddocument("111111");
		people.save(per1);
		Person per3 =new Person();
		per3.setPersName("adios");
		per3.setPersLastname("hi");
		per3.setPersEmail("a@gmail.com");
		per3.setPersIddocument("222222");
		people.save(per3);
		/**List<Person> lista= new ArrayList<Person>();
		lista.add(per2);
		when(people.findByName("adios")).thenReturn(lista);*/
		
		List<Person> test= people.findByName("adios");
		
		assertTrue(test.size()==1);
		assertTrue(test.get(0).getPersName().equals("adios"));
		
		people.delete(per1);
		people.delete(per3);

	}

	
	@Test
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Query")
	void testFindLastName() {
		Person per1 =new Person();
		per1.setPersName("hola");
		per1.setPersLastname("bye");
		per1.setPersEmail("j@gmail.com");
		per1.setPersIddocument("111111");
		people.save(per1);
		Person per3 =new Person();
		per3.setPersName("adios");
		per3.setPersLastname("hi");
		per3.setPersEmail("a@gmail.com");
		per3.setPersIddocument("222222");
		people.save(per3);
		/**List<Person> lista= new ArrayList<Person>();
		lista.add(per2);
		when(people.findByLastName("hi")).thenReturn(lista);*/
		
		List<Person> test= people.findByLastName("hi");
		

		assertTrue(test.size()==1);
		assertTrue(test.get(0).getPersLastname().equals("hi"));
		
		people.delete(per1);
		people.delete(per3);

	}
	
	
	@Test
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Query")
	void findByEmail() {
		Person per1 =new Person();
		per1.setPersName("hola");
		per1.setPersLastname("bye");
		per1.setPersEmail("k@yahoo.com");
		per1.setPersIddocument("111111");
		people.save(per1);
		//when(people.findByEmail(email)).thenReturn(per1);
		
		List<Person> test= people.findByEmail("k@yahoo.com");
		
		assertTrue(test.size()==1);
		
		people.delete(per1);
	}
	

}
