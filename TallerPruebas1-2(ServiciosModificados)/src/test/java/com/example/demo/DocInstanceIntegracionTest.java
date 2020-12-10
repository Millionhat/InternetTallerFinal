package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.TallerPruebas11Application;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.exception.NonValidDateException;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.DocStateInstanceService;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.DocumentTypeService;
import taller2.Palma.demo.service.PersonService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TallerPruebas11Application.class)
class DocInstanceIntegracionTest {
	
	private DocStateInstanceService serv;
	
	private PersonService ps;
	private DocumentService dts;
	
	public static Docstateinstance test= new Docstateinstance();
	public static Person p= new Person();
	public static Person fp=new Person();
	public static Documentt dt=new Documentt();
	public static Documentt ft=new Documentt();
	
	@Autowired
	public DocInstanceIntegracionTest(DocStateInstanceService ser,PersonService ps, DocumentService dts) {
		this.ps=ps;
		this.dts=dts;
		serv=ser;
	}
	@BeforeEach
	void setUp() throws Exception {
		test.setDocstatinsId(1);
		test.setDocstatinsStartdate(new Date());
		test.setDocstatinsEnddate(new Date());
		p.setPersId(1);
		p.setPersName("aloe");
		p.setPersLastname("vera");
		dt.setDocId(1);
		dt.setDocName("trabajo");
		test.setPerson(p);
		test.setDocumentt(dt);
		fp.setPersId(-1);
		ft.setDocId(-1);
		try {
			ps.addPerson(p);
			dts.addDoc(dt);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testAdd() {
		try {
			ps.getPerson(test.getPerson().getPersId());
			dts.getDoc(test.getDocumentt().getDocId());
			Docstateinstance added=serv.addDTS(test);
			
			assertTrue(added.getDocstatinsId()==1);
			
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void testAdd2() {
		try {
			Date a=new Date(System.currentTimeMillis() - 3600 * 1000);
			test.setDocstatinsEnddate(a);
			ps.getPerson(test.getPerson().getPersId());
			dts.getDoc(test.getDocumentt().getDocId());
			assertThrows(NonValidDateException.class,() ->{serv.addDTS(test);});
			
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void testAdd3() {
		try {
			test.setDocumentt(ft);
			ps.getPerson(test.getPerson().getPersId());
			dts.getDoc(test.getDocumentt().getDocId());
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testAdd4() {
		try {
			test.setPerson(fp);
			ps.getPerson(test.getPerson().getPersId());
			dts.getDoc(test.getDocumentt().getDocId());
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testEdit() {
		Date end= new Date(System.currentTimeMillis() + 3600 * 1000);
		test.setDocstatinsEnddate(end);
		Docstateinstance edited;
		try {
			edited = serv.update(test);
			assertTrue(edited.getDocstatinsEnddate().equals(end));
		} catch (NonValidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	void testEdit2() {
		Date end=new Date(System.currentTimeMillis() - 3600 * 1000);
		test.setDocstatinsEnddate(end);
		assertThrows(NonValidDateException.class, ()->{serv.update(test);});
	}
	
	@Test
	void testEdit3() {
		test.setDocumentt(ft);
		assertThrows(NoSuchElementException.class,()->{dts.getDoc(test.getDocumentt().getDocId());});
		
		test.setPerson(fp);
		assertThrows(NoSuchElementException.class,()->{ps.getPerson(test.getPerson().getPersId());});
	}
	
	@Test
	void testDelete() {
		serv.delete(test);
		assertThrows(NoSuchElementException.class,()->{serv.getDocInstance(test.getDocstatinsId());});
	}
}
