package com.example.demo.Delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.delegate.DocumentDelegate;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class DocumentDelegateTest {
	
	@Mock
	DocumentDelegate del;


	@Test
	void testAddDoc() {
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		del.createDocument(d1);
		
		when(del.getDocument(d1.getDocId())).thenReturn(d1);
		
		Documentt ans= del.getDocument(d1.getDocId());
		
		assertNotNull(ans);
		assertTrue(ans.getDocName()=="hola");
	}
	
	@Test
	void testDelete() {
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		del.createDocument(d1);
		
		List<Documentt> list=new ArrayList();
		
		when(del.getGroupDocuments()).thenReturn(list);
		del.deleteDoc(d1.getDocId());
		List<Documentt> answers=(List<Documentt>) del.getGroupDocuments();
		
		assertTrue(answers.size()==0);
	}
	
	@Test
	void testUpdate() {
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		del.createDocument(d1);
		
		Documentt d2= new Documentt();
		d2.setDocId(d1.getDocId());
		d2.setDocName("adios");
		d2.setDocIspositive("Si");
		when(del.getDocument(d1.getDocId())).thenReturn(d2);
		del.updateDoc(d1.getDocId(), d2);
		
		Documentt answer=del.getDocument(d1.getDocId());
		assertTrue(answer.getDocId()==d1.getDocId());
		assertTrue(!answer.equals(d1));
	}
	
	@Test
	void testGetDocPers() {
		Person p1= new Person();
		p1.setPersId(22);
		
		Person p2= new Person();
		p2.setPersId(111);
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		d1.setPerson(p1);
		
		Documentt d2=new Documentt();
		d2.setDocName("adios");
		d2.setPerson(p2);
		
		
		Documentt d3=new Documentt();
		d3.setDocName("hi");
		d3.setPerson(p1);
		
		Documentt d4=new Documentt();
		d4.setDocName("bye");
		d4.setPerson(p2);
		
		List<Documentt> l1= new ArrayList();
		l1.add(d1);
		l1.add(d3);
		when(del.getGroupDocByPerson(p1)).thenReturn(l1);
		
		List<Documentt> response= (List<Documentt>) del.getGroupDocByPerson(p1);
		
		assertTrue(response.size()==2);
		assertTrue(response.get(0).getPerson().equals(p1));
		assertTrue(response.get(1).getPerson().equals(p1));
	}
	
	@Test
	void testGetDocType() {
		
		Documenttype t1=new Documenttype();
		t1.setDoctypeId(111);
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		d1.setDocumenttype(t1);
		
		Documentt d2=new Documentt();
		d2.setDocName("adios");
		d2.setDocumenttype(t1);
		
		
		Documentt d3=new Documentt();
		d3.setDocName("hi");
		
		List<Documentt> l1= new ArrayList();
		l1.add(d1);
		l1.add(d2);
		
		when(del.getGroupDocbyType(t1)).thenReturn(l1);
		List<Documentt> answer=(List<Documentt>) del.getGroupDocbyType(t1);
		
		assertTrue(answer.size()==2);
		assertTrue(answer.get(0).getDocumenttype().equals(t1));
		assertTrue(answer.get(1).getDocumenttype().equals(t1));
	}
}














