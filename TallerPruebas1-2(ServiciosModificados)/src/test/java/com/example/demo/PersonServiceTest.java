package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.exception.ValueNotFoundException;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.InstitutionService;
import taller2.Palma.demo.service.PersonService;


@SpringBootTest
class PersonServiceTest {
	
	private static Person testSubject= new Person();
	private static Person nullSubject= new Person();
	private static Institution inst= new Institution();
	private static Institution fakeIns= new Institution();
	@Mock
	private PersonService servo;
	@Mock
	private InstitutionService ins;
	@BeforeEach
	void setAll() {
		inst.setInstId(3);
		testSubject.setPersName("manuel");
		testSubject.setInstitution(inst);
		testSubject.setPersLastname("gusman");
		
		nullSubject.setInstitution(fakeIns);
	}
	
	@Test
	void testAddPerson() {
		try {
			when(servo.addPerson(testSubject)).thenReturn(testSubject);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertTrue(servo.addPerson(testSubject)!=null);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testAddPerson2() {
		//Test de Apellido Vacio
		try {
			when(servo.addPerson(testSubject)).thenReturn(testSubject);
		} catch (NonNullValueException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		nullSubject.setPersName("rojo");
		//Se utilizara ValueNotFoundException.class pero no supe como utilizar custom Exceptions
		try {
			when(servo.addPerson(nullSubject)).thenThrow(NullPointerException.class);
		} catch (NonNullValueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			assertTrue(servo.addPerson(testSubject)!=null);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(NullPointerException.class, () -> {servo.addPerson(nullSubject);});
	}
	
	@Test
	void testAddPerson3() {
		//Test de Nombre Vacio
		
		//Se utilizara ValueNotFoundException.class pero no supe como utilizar custom Exceptions
		try {
			when(servo.addPerson(nullSubject)).thenThrow(NullPointerException.class);
			when(servo.addPerson(testSubject)).thenReturn(testSubject);
			nullSubject.setPersLastname("rojo");
		} catch (NonNullValueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			assertTrue(servo.addPerson(testSubject)!=null);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(NullPointerException.class, () -> {servo.addPerson(nullSubject);});
	}
	@Test
	void testAddPerson4() {
		//Asumimos que la institucion fakeInstitution no existe
		when(ins.getInstitution(testSubject.getInstitution().getInstId()).get()).thenReturn(inst);
		when(ins.getInstitution(nullSubject.getInstitution().getInstId()).get()).thenReturn(fakeIns);
		try {
			when(servo.addPerson(testSubject)).thenReturn(testSubject);
		} catch (NonNullValueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		if(ins.getInstitution(testSubject.getInstitution().getInstId())!=null) {
			try {
				assertTrue(servo.addPerson(testSubject)!=null);
			} catch (NonNullValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			fail();
		}
		
		if(ins.getInstitution(testSubject.getInstitution().getInstId())!=null) {
			try {
				assertTrue(servo.addPerson(testSubject)!=null);
			} catch (NonNullValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			fail();
		}
		
		if(ins.getInstitution(nullSubject.getInstitution().getInstId())!=null) {
			assertTrue(true);
		}else {
			fail();
		}
	}
	
	@Test
	void testEditPerson(){
		try {
			when(servo.update(testSubject)).thenReturn(testSubject);
			testSubject.setPersName("ola");
			testSubject.setPersLastname("adios");
			
			Person edited= servo.update(testSubject);
			
			assertTrue(edited.getPersName().equals("ola"));
			assertTrue(edited.getPersLastname().equals("adios"));
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Test
	void testEditPerson2() {
		try {
			when(servo.update(testSubject)).thenThrow(NullPointerException.class);
			testSubject.setPersName("");
			
			assertThrows(NullPointerException.class, () -> {servo.update(testSubject);});
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	void tesEditPerson3() {
		try {
			when(servo.update(testSubject)).thenThrow(NullPointerException.class);
			testSubject.setPersLastname("");
			
			assertThrows(NullPointerException.class, () -> {servo.update(testSubject);});
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

}
