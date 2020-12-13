package com.example.demo.Delegate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.delegate.InstitutionDelegate;
import taller2.Palma.demo.model.Institution;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
public class InstitutionDelegateTest {
	
	@Mock
	private InstitutionDelegate delegate;
	
	
	@Test
	void testCreateInstitution() {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		
		when(delegate.getInstitution(i1.getInstId())).thenReturn(i1);
		
		delegate.createInstitution(i1);
		
		Institution test = delegate.getInstitution(i1.getInstId());
		
		assertTrue(test.getInstName().equals(i1.getInstName()));
		
	}
	
	@Test
	void testUpdateInstitution() {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		
		delegate.createInstitution(i1);
		
		Institution iNew= new Institution();
		i1.setInstName("Icesi2");
		iNew.setInstId((int) i1.getInstId());
		
		delegate.updateInstitution(i1.getInstId(), iNew);
		when(delegate.getInstitution(i1.getInstId())).thenReturn(i1);
		
		Institution test = delegate.getInstitution(i1.getInstId());
		
		assertTrue(test.getInstId()==i1.getInstId());
		assertTrue(test.equals(i1));
		
	}
	
	@Test
	void testDeleteInstitution() {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		
		delegate.createInstitution(i1);
		
		delegate.deletedInstitution(i1.getInstId());
		
		List<Institution> list= new ArrayList();
		when(delegate.getGroupInstitution()).thenReturn(list);
		List<Institution> answers= (List<Institution>) delegate.getGroupInstitution();
		
		assertTrue(answers.size()==0);
		
	}
	
	@Test
	void testGetPeople() {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		
		Institution i2= new Institution();
		i1.setInstName("Javeriana");
		
		Institution i3= new Institution();
		i1.setInstName("Valle");
		
		Institution i4= new Institution();
		i1.setInstName("UAO");
		
		List<Institution> list = new ArrayList();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);
		
		when(delegate.getGroupInstitution()).thenReturn(list);
		List<Institution> response= (List<Institution>) delegate.getGroupInstitution();
		
		assertTrue(response.size()==4);
		assertTrue(response.get(0).getInstId()==i1.getInstId()); 
	}
}
