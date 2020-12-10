package com.example.demo.DAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.TallerPruebas11Application;
import taller2.Palma.demo.DAOimp.DocTypeDAO;
import taller2.Palma.demo.model.Documenttype;

@SpringBootTest
@ContextConfiguration(classes=TallerPruebas11Application.class)
class DocTypeDAOTest {
	
	private static Documenttype dt= new Documenttype();
	private static Documenttype dt2= new Documenttype();
	
	@Autowired
	private DocTypeDAO dao;

	/**@BeforeEach
	void setUp() throws Exception {
		//dt.setDoctypeId(1);
		dt.setDoctypeName("excel");
		dt.setDoctypeIsactive("si");
		
		//dt2.setDoctypeId(2);
		dt2.setDoctypeName("pdf");
		dt2.setDoctypeIsactive("si");
	}*/

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd() {
		//when(dao.findById(dt.getDoctypeId())).thenReturn(dt);
		dt.setDoctypeName("excel");
		dt.setDoctypeIsactive("si");
		dt2.setDoctypeName("pdf");
		dt2.setDoctypeIsactive("si");
		dao.save(dt);
		
		assertTrue(dao.findById(dt.getDoctypeId()).getDoctypeName().equals("excel"));
		assertTrue(dao.findById(dt.getDoctypeId()).getDoctypeIsactive().equals("si"));
		dao.delete(dt);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd2() {
		/**List<Documenttype> lista=new ArrayList<Documenttype>();
		lista.add(dt);
		lista.add(dt2);
		when(dao.findAll()).thenReturn(lista);*/
		dt= new Documenttype();
		dt.setDoctypeName("excel");
		dt.setDoctypeIsactive("si");
		dao.save(dt);
		dao.save(dt2);
		
		List<Documenttype> test= dao.findAll();
		assertTrue(test.size()==2);
		assertTrue(test.get(0).getDoctypeName()!=test.get(1).getDoctypeName());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Edit")
	void testUpdate() {
		Documenttype updated=new Documenttype();
		updated.setDoctypeId(dt2.getDoctypeId());
		updated.setDoctypeName("word");
		updated.setDoctypeIsactive("no");
		//when(dao.findById(dt.getDoctypeId())).thenReturn(updated);
		
		dao.update(updated);
		Documenttype update=dao.findById(updated.getDoctypeId());
		assertTrue(update.getDoctypeId()==dt2.getDoctypeId());
		assertTrue(update.getDoctypeName()!=dt2.getDoctypeName());
		assertTrue(update.getDoctypeIsactive()!=dt2.getDoctypeIsactive());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Delete")
	void testDelete() {
		//when(dao.findById(dt.getDoctypeId())).thenReturn(null);
		dt.setDoctypeName("excel");
		dt.setDoctypeIsactive("si");
		dao.delete(dt);
		assertNull(dao.findAll().size()==1);
	}
	
}
