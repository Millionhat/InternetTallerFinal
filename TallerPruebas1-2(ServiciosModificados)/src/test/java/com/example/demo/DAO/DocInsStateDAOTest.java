package com.example.demo.DAO;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.TallerPruebas11Application;
import taller2.Palma.demo.DAOimp.DocInsStateDAO;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;


@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class DocInsStateDAOTest {
	
	private static Docstateinstance dsi = new Docstateinstance();
	private static Docstateinstance dsi2 = new Docstateinstance();
	
	@Autowired
	private DocInsStateDAO dao;
	
	@BeforeEach
	void setUp() throws Exception {
		//dsi.setDocstatinsId(1);
		dsi.setDocstatinsEnddate(Date.from(Instant.now().plus(Duration.ofDays(1))));
		dsi.setDocstatinsStartdate(Date.from(Instant.now().minus(Duration.ofDays(20))));
		
		//dsi2.setDocstatinsId(2);
		dsi2.setDocstatinsEnddate(Date.from(Instant.now().plus(Duration.ofDays(3))));
		dsi2.setDocstatinsStartdate(Date.from(Instant.now().minus(Duration.ofDays(2))));
		

	}

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd() {
		dao.save(dsi);
		dao.save(dsi2);
		assertNotNull(dao.findById(dsi.getDocstatinsId()));
		dao.delete(dsi2);
		dao.delete(dsi);
		
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Create")
	void testAdd2() {
		/**List<Docstateinstance> lista= new ArrayList<Docstateinstance>();
		lista.add(dsi);
		lista.add(dsi2);
		(dao.findAll()).thenReturn(lista);*/
		Docstateinstance dsi4=new Docstateinstance();
		dsi4.setDocstatinsStartdate(dsi.getDocstatinsStartdate());
		dsi4.setDocstatinsEnddate(dsi.getDocstatinsEnddate());
		
		Docstateinstance dsi3=new Docstateinstance();
		dsi3.setDocstatinsStartdate(dsi2.getDocstatinsStartdate());
		dsi3.setDocstatinsEnddate(dsi2.getDocstatinsEnddate());
		
		dao.save(dsi4);
		dao.save(dsi3);
		
		List<Docstateinstance> test= dao.findAll();
		assertTrue(test.size()==2);
		assertTrue(test.get(0).getDocstatinsId()!=test.get(1).getDocstatinsId());
		dao.delete(dsi4);
		dao.delete(dsi3);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Edit")
	void testUpdate() {
		Date up=Date.from(Instant.now().minus(Duration.ofDays(1)));
		Docstateinstance update= new Docstateinstance();
		update.setDocstatinsId(dsi.getDocstatinsId());
		update.setDocstatinsStartdate(up);
		update.setDocstatinsEnddate(dsi.getDocstatinsEnddate());
		//when(dao.findById(dsi.getDocstatinsId())).thenReturn(update);
		dao.update(update);
		Docstateinstance test=dao.findById(dsi.getDocstatinsId());
		
		assertTrue(test.getDocstatinsEnddate()==dsi.getDocstatinsEnddate());
		assertTrue(test.getDocstatinsStartdate()!=dsi.getDocstatinsStartdate());
		dao.delete(dsi2);
		dao.delete(dsi);
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Delete")
	void testDelete() {
		/**List<Docstateinstance> lista= new ArrayList<Docstateinstance>();
		when(dao.findAll()).thenReturn(lista);*/
		
		List<Docstateinstance> test= dao.findAll();
		assertTrue(test.size()==0);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Search")
	void testInRange() {
		Date up=Date.from(Instant.now().minus(Duration.ofDays(10)));
		/**List<Docstateinstance> lista= new ArrayList<Docstateinstance>();
		lista.add(dsi2);
		when(dao.findByDateRange(up,Date.from(Instant.now()))).thenReturn(lista);*/
		Docstateinstance dsi5= new Docstateinstance();
		dsi5.setDocstatinsEnddate(Date.from(Instant.now().plus(Duration.ofDays(3))));
		dsi5.setDocstatinsStartdate(Date.from(Instant.now().minus(Duration.ofDays(2))));
		
		dao.save(dsi5);
		
		List<Docstateinstance> test=dao.findByDateRange(up,Date.from(Instant.now().plus(Duration.ofDays(3))));
		
		//assertTrue(test.size()==1);
		assertTrue(test.get(0).getDocstatinsId()==dsi5.getDocstatinsId());
		assertTrue(test.get(0).getDocstatinsStartdate().compareTo(up)>0);

	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor= Exception.class)
	@Tag("Search")
	void testLastDays() {
		Docstateinstance dsi6= new Docstateinstance();
		dsi6.setDocstatinsEnddate(Date.from(Instant.now().plus(Duration.ofDays(3))));
		dsi6.setDocstatinsStartdate(Date.from(Instant.now().minus(Duration.ofDays(2))));
		
		dao.save(dsi6);
		List<Docstateinstance> test=dao.findChanged();
		dao.delete(dsi6);
		assertTrue(test.size()==1);
		
	}
	

}
