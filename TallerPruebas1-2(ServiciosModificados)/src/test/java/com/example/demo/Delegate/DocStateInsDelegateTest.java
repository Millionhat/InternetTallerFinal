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

import taller2.Palma.demo.delegate.DocStateInsDelegate;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Person;


@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class DocStateInsDelegateTest {
	
	@Mock
	DocStateInsDelegate del;


	@Test
	void testAddInstance() {
		Docstateinstance d1= new Docstateinstance();
		del.createDSI(d1);
		
		when(del.getDSI(d1.getDocstatinsId())).thenReturn(d1);
		
		Docstateinstance answer=del.getDSI(d1.getDocstatinsId());
		
		assertNull(answer.getDocstatinsEnddate());
	}
	
	@Test
	void testDeleteInstance() {
		Docstateinstance d1= new Docstateinstance();
		del.createDSI(d1);
		
		Docstateinstance d2= new Docstateinstance();
		d2.setDocstatinsId(1111);
		del.createDSI(d2);
		
		List<Docstateinstance> list=new ArrayList();
		list.add(d2);
		
		when(del.getDroupDSI()).thenReturn(list);
		
		del.deleteDSI(d1.getDocstatinsId());
		List<Docstateinstance> ans= (List<Docstateinstance>) del.getDroupDSI();
		assertTrue(ans.size()==1);
		assertTrue(ans.get(0).getDocstatinsId()!=d1.getDocstatinsId());
	}
	
	@Test
	void testUpdate() {
		Docstateinstance d1= new Docstateinstance();
		del.createDSI(d1);
		
		Docstateinstance d2= new Docstateinstance();
		d2.setDocstatinsId(d1.getDocstatinsId());
		d2.setPerson(new Person());
		
		del.updateDSI(d1.getDocstatinsId(), d2);
		
		when(del.getDSI(d1.getDocstatinsId())).thenReturn(d2);
		
		Docstateinstance ans= del.getDSI(d1.getDocstatinsId());
		
		assertEquals(ans.getDocstatinsId(),d1.getDocstatinsId());
		assertNotEquals(ans,d1);
	}

}
