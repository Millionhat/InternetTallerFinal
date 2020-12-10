package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentstate;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.DocumentStateService;
import taller2.Palma.demo.service.PersonService;

@SpringBootTest
class DocInstanceServiceTest {
	
	@Mock
	private DocumentStateService dss;
	@Mock
	private PersonService perServ;
	@Mock
	private DocumentService docServ;
	
	public static Docstateinstance ds= new Docstateinstance();
	public static Person ps=new Person();
	public static Documentt doc=new Documentt();
	
	
	@BeforeEach
	void setUp() {
		Date d=new Date();
		ps.setPersId(1);
		doc.setDocId(1);
		ds.setDocstatinsId(1);
		ds.setDocstatinsStartdate(d);
		ds.setDocstatinsEnddate(d);
		ds.setPerson(ps);
		ds.setDocumentt(doc);
	}
	
	@Test
	void testAddDSI() {
		when(dss.addDSI(ds)).thenReturn(ds);
		when(perServ.getPerson(ds.getPerson().getPersId())).thenReturn(ps);
		when(docServ.getDoc(ds.getDocumentt().getDocId())).thenReturn(doc);
		
		assertTrue(perServ.getPerson(ds.getPerson().getPersId())!=null);
		assertTrue(docServ.getDoc(ds.getDocumentt().getDocId())!=null);
		assertTrue(dss.addDSI(ds)!=null);
	}
	
	@Test
	void testAddDSI2() {
		when(dss.addDSI(ds)).thenReturn(ds);
		when(perServ.getPerson(ds.getPerson().getPersId())).thenReturn(ps);
		when(docServ.getDoc(ds.getDocumentt().getDocId())).thenThrow(NullPointerException.class);
		
		assertTrue(perServ.getPerson(ds.getPerson().getPersId())!=null);
		assertThrows(NullPointerException.class, () -> {docServ.getDoc(ds.getDocumentt().getDocId());});
	}
	
	@Test
	void testAddDSI3() {
		when(dss.addDSI(ds)).thenReturn(ds);
		when(perServ.getPerson(ds.getPerson().getPersId())).thenThrow(NullPointerException.class);
		when(docServ.getDoc(ds.getDocumentt().getDocId())).thenReturn(doc);
		
		assertThrows(NullPointerException.class, () ->{perServ.getPerson(ds.getPerson().getPersId());});
		assertTrue(docServ.getDoc(ds.getDocumentt().getDocId())!=null);
	}
	
	@Test
	void testAdd4() {
		when(dss.addDSI(ds)).thenThrow(NullPointerException.class);
		when(perServ.getPerson(ds.getPerson().getPersId())).thenReturn(ps);
		when(docServ.getDoc(ds.getDocumentt().getDocId())).thenReturn(doc);
		
		ds.setDocstatinsEnddate(null);
		
		assertTrue(perServ.getPerson(ds.getPerson().getPersId())!=null);
		assertTrue(docServ.getDoc(ds.getDocumentt().getDocId())!=null);
		assertThrows(NullPointerException.class, () ->{dss.addDSI(ds);});
	}
	@Test
	void testEdit() {
		when(dss.update(ds)).thenReturn(ds);
		when(perServ.getPerson(ds.getPerson().getPersId())).thenReturn(ps);
		when(docServ.getDoc(ds.getDocumentt().getDocId())).thenReturn(doc);
		
		Date actual= new Date();
		
		ds.setDocstatinsEnddate(actual);
		ds.setDocstatinsStartdate(actual);
		
		
		assertTrue(perServ.getPerson(ds.getPerson().getPersId())!=null);
		assertTrue(docServ.getDoc(ds.getDocumentt().getDocId())!=null);
		assertTrue(dss.update(ds)!=null);
	}
	@Test
	void testEdit2() {
		when(dss.update(ds)).thenReturn(ds);
		when(perServ.getPerson(ds.getPerson().getPersId())).thenThrow(NullPointerException.class);
		when(docServ.getDoc(ds.getDocumentt().getDocId())).thenThrow(NullPointerException.class);
		
		Person ed=new Person();
		ed.setPersId(0);
		Documentt d= new Documentt();
		d.setDocId(-1);
		
		assertThrows(NullPointerException.class, () ->{perServ.getPerson(ds.getPerson().getPersId());});
		assertThrows(NullPointerException.class, () -> {docServ.getDoc(ds.getDocumentt().getDocId());});
	}
}
