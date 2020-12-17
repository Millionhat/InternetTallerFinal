package com.example.demo.Delegate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import taller2.Palma.demo.REST.PersonRest;
import taller2.Palma.demo.delegate.PersonDelegate;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Person;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
public class PersonDelegateTest {
	
	
	@Mock
	private PersonDelegate delegate;
	
	@Mock
	private PersonRest rest;
	
	
	@BeforeEach
	void setup() {
		
	}
	
	@Test
	void testCreatePerson() {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		when(delegate.getPerson(p1.getPersId())).thenReturn(p1);
		
		delegate.createPerson(p1);
		
		Person test= delegate.getPerson(p1.getPersId());
		
		assertTrue(test.getPersName().equals(p1.getPersName()));
	}
	
	@Test
	void testUpdatePerson() {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		delegate.createPerson(p1);
		
		when(delegate.getPerson(p1.getPersId())).thenReturn(p1);
		
		Person pNew= new Person();
		pNew.setPersName("Juan");
		pNew.setPersLastname("Juan");
		pNew.setPersEmail("new@gmail.com");
		pNew.setPersId(p1.getPersId());
		
		delegate.updatePerson(p1.getPersId(), pNew);
		
		Person test= delegate.getPerson(p1.getPersId());
		
		assertTrue(test.getPersId()== p1.getPersId());
		assertTrue(test.equals(p1));
		
		
	}
	
	@Test
	void testDeletePerson() {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		delegate.createPerson(p1);
		
		delegate.deletePerson(p1.getPersId());
		
		List<Person> list= new ArrayList();
		when(delegate.getGroupPersonData()).thenReturn(list);
		List<Person> answers= (List<Person>) delegate.getGroupPersonData();
		
		assertTrue(answers.size()==0);
	}
	
	@Test
	void testGetPeople() {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		Person p2 = new Person();
		p2.setPersName("juan");
		p2.setPersLastname("juan");
		p2.setPersEmail("j@gmail.com");
		p2.setPersIddocument("2222222");
		
		Person p3 = new Person();
		p3.setPersName("juan");
		p3.setPersLastname("juan");
		p3.setPersEmail("j@gmail.com");
		p3.setPersIddocument("3333333");
		
		List<Person> list= new ArrayList();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		when(delegate.getGroupPersonData()).thenReturn(list);
		
		List<Person> response= (List<Person>) delegate.getGroupPersonData();
		
		assertTrue(response.size()==3);
		assertTrue(response.get(0).getPersId()==p1.getPersId());
	}
	
}
