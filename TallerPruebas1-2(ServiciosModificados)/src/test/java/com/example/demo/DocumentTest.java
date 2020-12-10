package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.DocumentService;

@SpringBootTest
class DocumentTest {
	
	@Mock
	private DocumentService ds;
	
	private static Documentt doc=new Documentt();
	private static Person p=new Person();
	private static Documenttype dt=new Documenttype();
	
	
	
	@BeforeEach
	void setUp() {
		doc.setDocId(1);
		doc.setDocName("Tarjeta");
		doc.setPerson(p);		
		doc.setDocumenttype(dt);
	}
	@Test
	void testAddDoc() {
		when(ds.addDoc(doc)).thenReturn(doc);
		
		assertTrue(ds.addDoc(doc)!=null);
	}
	
	@Test
	void testAddDoc2() {
		when(ds.addDoc(doc)).thenThrow(NullPointerException.class);
		
		assertThrows(NullPointerException.class, () -> {ds.addDoc(doc);});
	}
	
	@Test
	void testEditDoc() {
		when(ds.update(doc)).thenReturn(doc);
		doc.setDocName("preuba");
		assertTrue(ds.update(doc).getDocName()=="preuba");
	}
	
	@Test
	void testEditDoc2() {
		when(ds.update(doc)).thenThrow(NullPointerException.class);
		doc.setDocName("");
		
		assertThrows(NullPointerException.class, ()-> {ds.update(doc);});
		
	}
	@Test
	void testEditDoc3() {
		when(ds.update(doc)).thenThrow(NullPointerException.class);
		doc.setPerson(null);
		
		assertThrows(NullPointerException.class, ()-> {ds.update(doc);});
	}
	@Test
	void testEditDoc4() {
		when(ds.update(doc)).thenThrow(NullPointerException.class);
		doc.setDocumenttype(null);
		
		assertThrows(NullPointerException.class, ()-> {ds.update(doc);});
	}
}
