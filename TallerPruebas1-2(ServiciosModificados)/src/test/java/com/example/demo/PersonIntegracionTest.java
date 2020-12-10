package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.java.Log;
import taller2.Palma.demo.TallerPruebas11Application;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.repository.InstitutionRepository;
import taller2.Palma.demo.repository.PersonRepository;
import taller2.Palma.demo.service.InstitutionService;
import taller2.Palma.demo.service.PersonService;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TallerPruebas11Application.class)
class PersonIntegracionTest {
	
	private static Person testSubject= new Person();
	private static Institution inst= new Institution();
	private static Institution fakeIns= new Institution();
	
	private PersonService serv;
	private static InstitutionService ser;
	
	@Autowired
	public PersonIntegracionTest(PersonService repo,InstitutionService rep) {
		serv=repo;
		ser=rep;
	}
	@BeforeEach
	void setup() {
		inst.setInstId(1);
		fakeIns.setInstId(3);
		ser.addInstituion(inst);
		testSubject.setPersId(1);
		testSubject.setPersName("AAA");
		testSubject.setPersLastname("bbb");
		testSubject.setInstitution(inst);
	}
	@Nested
	class a{
	@Test
	void testAdd() {
		if(ser.getInstitution(testSubject.getInstitution().getInstId()).getInstId()==inst.getInstId()) {
		Person added;
		try {
			added = serv.addPerson(testSubject);
			assertTrue(added.getPersId()==1);
			assertTrue(added.getPersName()=="AAA");
			assertTrue(added.getPersLastname()=="bbb");
			serv.delete(added.getPersId());
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
;
		}else {
			fail();
		}
	}
	
	@Test
	void testAdd2() {
		testSubject.setInstitution(fakeIns);
		assertThrows(NoSuchElementException.class, () -> {ser.getInstitution(testSubject.getInstitution().getInstId());});
		try {
			ser.getInstitution(testSubject.getInstitution().getInstId());
			serv.addPerson(testSubject);
			fail();
		}catch (Exception e) {
			assertTrue(true);
			
		}
		
	}
	@Test
	void testAdd3() {
		testSubject.setPersName("");
		if(ser.getInstitution(testSubject.getInstitution().getInstId()).getInstId()==inst.getInstId()) {
			assertThrows(NonNullValueException.class, () ->{serv.addPerson(testSubject);});
			testSubject.setPersName(null);
			assertThrows(NonNullValueException.class, () ->{serv.addPerson(testSubject);});
		}
	}
	
	@Test
	void testAdd4() {
		testSubject.setPersLastname("");
		if(ser.getInstitution(testSubject.getInstitution().getInstId()).getInstId()==inst.getInstId()) {
			assertThrows(NonNullValueException.class, () ->{serv.addPerson(testSubject);});
			testSubject.setPersLastname(null);
			assertThrows(NonNullValueException.class, () ->{serv.addPerson(testSubject);});
		}
	}
	}
	@Nested
	class b{
	@Test
	void testEdit() {
		testSubject.setPersName("Gmail");
		testSubject.setPersLastname("212312");
		Person edit;
		try {
			edit = serv.update(testSubject);
			assertTrue(edit.equals(testSubject));
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testEdit2() {
		testSubject.setPersName("");
		assertThrows(NonNullValueException.class, () ->{serv.update(testSubject);});
		testSubject.setPersName(null);
		assertThrows(NonNullValueException.class, () ->{serv.update(testSubject);});
		
	}
	
	@Test
	void testEdit3() {
		testSubject.setPersLastname("");
		assertThrows(NonNullValueException.class, () ->{serv.update(testSubject);});
		testSubject.setPersLastname(null);
		assertThrows(NonNullValueException.class, () ->{serv.update(testSubject);});
	}
	
	@Test
	void testEdit4() {
		testSubject.setInstitution(fakeIns);
		
		testSubject.setInstitution(fakeIns);
		assertThrows(NoSuchElementException.class, () -> {ser.getInstitution(testSubject.getInstitution().getInstId());});
		try {
			ser.getInstitution(testSubject.getInstitution().getInstId());
			serv.update(testSubject);
			fail();
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	}
	@Nested
	class c{
	@Test
	void testDelete() {
		serv.delete(testSubject.getPersId());
		assertThrows(NoSuchElementException.class, ()->{serv.getPerson(testSubject.getPersId());});
	}
	@Test
	void testDelete2() {
		testSubject.setPersId(5);
		assertThrows(NoSuchElementException.class, ()->{serv.delete(testSubject.getPersId());});
	}
	}
}
