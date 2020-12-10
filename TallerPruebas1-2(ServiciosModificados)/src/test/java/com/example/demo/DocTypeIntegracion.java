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
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.service.DocumentTypeService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TallerPruebas11Application.class)
class DocTypeIntegracion {
	
	private DocumentTypeService serv;
	
	public static Documenttype doc =new Documenttype();
	@Autowired
	public DocTypeIntegracion(DocumentTypeService dts) {
		serv=dts;
	}
	@BeforeEach
	void setUp() {
		doc.setDoctypeId(1);
		doc.setDoctypeName("caja");
	}
	@Test
	void testAdd(){
		Documenttype added;
		try {
			added = serv.addDT(doc);
			assertTrue(added.getDoctypeId()==1);
			assertTrue(added.getDoctypeName()=="caja");
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	void testAdd2() {
		doc.setDoctypeName("");
		assertThrows(NonNullValueException.class, ()->{serv.update(doc);});
		doc.setDoctypeName(null);
		assertThrows(NonNullValueException.class, ()->{serv.update(doc);});
	}
	
	@Test
	void testEdit() {
		doc.setDoctypeName("carro");
		Documenttype edit;
		try {
			edit = serv.update(doc);
			assertTrue(edit.getDoctypeName()!="caja");
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testEdit2() {
		doc.setDoctypeName("");
		assertThrows(NonNullValueException.class, ()->{serv.update(doc);});
		doc.setDoctypeName(null);
		assertThrows(NonNullValueException.class, ()->{serv.update(doc);});
	}
	
	@Test
	void testDelete() {
		serv.delete(doc.getDoctypeId());
		assertThrows(NoSuchElementException.class, ()->{serv.getDocType(doc.getDoctypeId());});
	}
	
	@Test
	void testDelete2() {
		doc.setDoctypeId(10);
		assertThrows(NoSuchElementException.class, ()->{serv.delete(doc.getDoctypeId());});
	}
}
