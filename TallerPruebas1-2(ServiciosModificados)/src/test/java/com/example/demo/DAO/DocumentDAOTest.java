package com.example.demo.DAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.TallerPruebas11Application;
import taller2.Palma.demo.DAOimp.DocInsStateDAO;
import taller2.Palma.demo.DAOimp.DocTypeDAO;
import taller2.Palma.demo.DAOimp.DocumentDAO;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class DocumentDAOTest {
	
	private static Documentt doc1= new Documentt();
	private static Documentt doc2= new Documentt();
	
	@Autowired
	private DocumentDAO docs;
	
	@Autowired
	private DocTypeDAO tipo;
	
	@Autowired
	private DocInsStateDAO ins;

	

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd() {
		//doc1.setDocId(1);
		doc1.setDocName("hola");
		doc1.setDocIspositive("true");
		
		//doc2.setDocId(2);
		doc2.setDocName("ADIOS");
		doc2.setDocIspositive("false");
		//when(docs.findById(doc1.getDocId())).thenReturn(doc1);
		docs.save(doc1);
		List<Documentt> test= docs.findAll();
		assertNotNull(test);
		assertTrue(test.get(0).getDocIspositive()=="true");
		assertTrue(test.get(0).getDocName()=="hola");

	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd2() {
		/**List<Documentt> lista= new ArrayList<Documentt>();
		lista.add(doc1);
		lista.add(doc2);
		when(docs.findAll()).thenReturn(lista);*/
		docs.save(doc2);
		List<Documentt> test=docs.findAll();
		
		assertTrue(test.size()==2);
		assertTrue(test.get(0).getDocName()!=test.get(1).getDocName());

	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Edit")
	void testUpdate() {
		Documentt update= new Documentt();
		update.setDocId(doc1.getDocId());
		update.setDocName("salu2");
		update.setDocIspositive(doc1.getDocIspositive());
		
		docs.update(update);
		
		Documentt test= docs.findById(doc1.getDocId());
		
		assertTrue(test.getDocName()!=doc1.getDocName());
		assertTrue(test.getDocIspositive()==doc1.getDocIspositive());

	}

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Delete")
	void testDelete() {
		/**List<Documentt> lista= new ArrayList<Documentt>();
		lista.add(doc1);
		when(docs.findAll()).thenReturn(lista);*/
		Documentt del= new Documentt();
		del.setDocId(doc2.getDocId());
		del.setDocName(doc2.getDocName());
		del.setDocIspositive(doc2.getDocIspositive());
		docs.delete(del);
		
		List<Documentt> test=docs.findAll();
		System.out.println(test.size());
		assertTrue(test.size()==1);

	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Search")
	void testFindName() {
		/**List<Documentt> lista= new ArrayList<Documentt>();
		lista.add(doc1);
		when(docs.findByDocName("hola")).thenReturn(lista);*/
		List<Documentt> test=docs.findByDocName("hola");
		
		assertTrue(test.size()==1);
		assertTrue(test.get(0).getDocId()==doc1.getDocId());

	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Query")
	void testFindRange() {
		Date past=Date.from(Instant.now().minus(Duration.ofDays(2)));
		Date fut=Date.from(Instant.now().plus(Duration.ofDays(5)));
		Docstateinstance dsi=new Docstateinstance();
		dsi.setDocstatinsEnddate(fut);
		dsi.setDocstatinsStartdate(past);
		dsi.setDocumentt(doc1);
		ins.save(dsi);
		List<Documentt> test= docs.findByDateRangeDoc(past, fut);
		assertTrue(test.size()==1);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Query")
	void testType() {
		Documenttype dtype=new Documenttype();
		dtype.setDoctypeName("nuevo");;
		dtype.setDoctypeIsactive("si");
		tipo.save(dtype);
		
		
		Documentt typo= new Documentt();
		typo.setDocumenttype(dtype);
		docs.save(typo);
		List<Documentt> test=docs.findByType(dtype);
		assertTrue(test.size()==1);
		
	}

	
}
