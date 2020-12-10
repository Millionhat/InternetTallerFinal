package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.TallerPruebas11Application;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.DocumentTypeService;
import taller2.Palma.demo.service.PersonService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TallerPruebas11Application.class)
class DocIntegracion {
	
	private DocumentService ds;
	private PersonService ps;
	private DocumentTypeService dts;
	
	public static Documentt test= new Documentt();
	public static Person p= new Person();
	public static Person fp=new Person();
	public static Documenttype dt=new Documenttype();
	public static Documenttype ft=new Documenttype();
	
	@Autowired
	public DocIntegracion(DocumentService ser,PersonService ps, DocumentTypeService dts) {
		this.ps=ps;
		this.dts=dts;
		ds=ser;
	}
	
	@BeforeEach
	void setUp(){
		test.setDocId(1);
		test.setDocName("carta");
		test.setPerson(p);
		p.setPersId(1);
		p.setPersName("aloe");
		p.setPersLastname("vera");
		dt.setDoctypeId(1);
		dt.setDoctypeName("trabajo");
		test.setPerson(p);
		test.setDocumenttype(dt);
		fp.setPersId(-1);
		ft.setDoctypeId(-1);
		try {
			ps.addPerson(p);
			dts.addDT(dt);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	void testAdd() {
		try{
			ps.getPerson(test.getPerson().getPersId());
			dts.getDocType(test.getDocumenttype().getDoctypeId());
			if(ps.getPerson(test.getPerson().getPersId())!=null && dts.getDocType(test.getDocumenttype().getDoctypeId())!=null) {
				Documentt added=ds.addDoc(test);
				assertTrue(added.getDocId()==1);
				assertTrue(added.getDocName()=="carta");
			}else {
				fail();
			}
		}catch (Exception e){
			fail();
		}
	}
	
	@Test
	void testAdd2() {
		try{
			test.setDocName("");
			ps.getPerson(test.getPerson().getPersId());
			dts.getDocType(test.getDocumenttype().getDoctypeId());
			if(ps.getPerson(test.getPerson().getPersId())!=null && dts.getDocType(test.getDocumenttype().getDoctypeId())!=null) {
				assertThrows(NonNullValueException.class, ()->{ds.addDoc(test);});
				
				test.setDocName(null);
				assertThrows(NonNullValueException.class, ()->{ds.addDoc(test);});
			}else {
				fail();
			}
		}catch (Exception e){
			fail();
		}
	}
	
	@Test
	void testAdd3() {
		try {
			test.setPerson(fp);
			ps.getPerson(test.getPerson().getPersId());
			dts.getDocType(test.getDocumenttype().getDoctypeId());
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testAdd4() {
		try {
			test.setDocumenttype(ft);
			ps.getPerson(test.getPerson().getPersId());
			dts.getDocType(test.getDocumenttype().getDoctypeId());
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testEdit() {
		test.setDocName("prueba");
		Documentt edit=ds.update(test);
		assertTrue(edit.getDocName()=="prueba");
		assertTrue(edit.getDocId()==1);
	}
	
	@Test
	void testEdit2() {
		test.setDocName("");
		assertThrows(NonNullValueException.class, ()->{ds.update(test);});
		
		test.setDocName(null);
		assertThrows(NonNullValueException.class, ()->{ds.update(test);});
	}
	
	@Test
	void testDelete() {
		ds.delete(test);
		assertThrows(NoSuchElementException.class, ()->{ds.getDoc(test.getDocId());});
	}
	@Test
	void testDelete2() {
		assertThrows(NoSuchElementException.class, ()->{ds.getDoc(test.getDocId());});
	}
		
}
