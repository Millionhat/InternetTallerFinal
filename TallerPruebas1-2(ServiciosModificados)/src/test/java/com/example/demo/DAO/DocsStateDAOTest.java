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
import taller2.Palma.demo.DAOimp.DocStateDAO;
import taller2.Palma.demo.model.Documentstate;

@SpringBootTest
@ContextConfiguration(classes=TallerPruebas11Application.class)
class DocsStateDAOTest {

	@Autowired
	private DocStateDAO dao;

	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd() {
		/**List<Documentstate> lista=new ArrayList<Documentstate>();
		lista.add(ds);
		lista.add(ds2);
		
		when(docstate.findAll()).thenReturn(lista);*/
		//ds.setDocstatId(1);
		Documentstate ds= new Documentstate();
		Documentstate ds2=new Documentstate();
		ds.setDocstatName("nuevo");
		
		//ds2.setDocstatId(2);
		ds2.setDocstatName("viejo");
		dao.save(ds);
		dao.save(ds2);
		List<Documentstate> test= dao.findAll();
		assertTrue(test.size()==2);
		assertTrue(test.get(0).getDocstatId()!=test.get(1).getDocstatId());
		dao.delete(ds);
		dao.delete(ds2);

	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Edit")
	void testUpdate() {
		Documentstate ds=new Documentstate();
		ds.setDocstatName("nuevo");
		dao.save(ds);
		Documentstate edit=new Documentstate();
		edit.setDocstatId(ds.getDocstatId());
		edit.setDocstatName("no");
		
		//when(docstate.findById(ds.getDocstatId())).thenReturn(edit);
		
		dao.update(edit);
		Documentstate test=dao.findById(edit.getDocstatId());
		assertTrue(test.getDocstatName()!=ds.getDocstatName());

	}

	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Delete")
	void testDelete() {
		/**List<Documentstate> lista= new ArrayList<Documentstate>();
		when(docstate.findAll()).thenReturn(lista);*/

		Documentstate ds= new Documentstate();
		Documentstate ds2=new Documentstate();
		ds.setDocstatName("nuevo");
		
		//ds2.setDocstatId(2);
		ds2.setDocstatName("viejo");
		dao.save(ds);
		dao.save(ds2);
		
		dao.delete(ds);
		dao.delete(ds2);
		
		List<Documentstate> test= dao.findAll();
		
		assertTrue(test.size()==0);
	}
}
