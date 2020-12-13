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

import taller2.Palma.demo.delegate.IdDocTypeDelegate;
import taller2.Palma.demo.model.Iddocumenttype;


@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class IdDocTypeDelegateTest {
	
	@Mock
	IdDocTypeDelegate delegate;
	
	@Test
	void testAddIdDocType() {
		
		Iddocumenttype idt1= new Iddocumenttype();
		idt1.setIddoctypeName("Pasaporte");
		
		delegate.creatIdDocType(idt1);
		
		when(delegate.getIdDocType(idt1.getIddoctypeId())).thenReturn(idt1);
		
		Iddocumenttype ans= delegate.getIdDocType(idt1.getIddoctypeId());
		
		assertTrue(ans.getIddoctypeName()=="Pasaporte");
	}
	
	@Test
	void testDeleteIdDocType() {
		
		Iddocumenttype idt=new Iddocumenttype();
		idt.setIddoctypeName("Cedula");
		
		delegate.creatIdDocType(idt);
		
		List<Iddocumenttype> list= new ArrayList();
		
		when(delegate.getGroupTypes()).thenReturn(list);
		
		delegate.deleteIDT(idt.getIddoctypeId());
		
		List<Iddocumenttype> ans= (List<Iddocumenttype>) delegate.getGroupTypes();
		
		assertTrue(ans.size()==0);
	}
	
	@Test
	void testUpdateIDT() {
		
		Iddocumenttype idt=new Iddocumenttype();
		idt.setIddoctypeName("Cedula");
		
		delegate.creatIdDocType(idt);
		
		Iddocumenttype idt2= new Iddocumenttype();
		idt2.setIddoctypeId(idt.getIddoctypeId());
		idt2.setIddoctypeName("Tarjeta de Identidad");
		
		delegate.updateIDT(idt.getIddoctypeId(),idt2);
		
		when(delegate.getIdDocType(idt.getIddoctypeId())).thenReturn(idt2);
		
		Iddocumenttype edited= delegate.getIdDocType(idt.getIddoctypeId());
		
		assertTrue(edited.getIddoctypeId()==idt.getIddoctypeId());
		assertFalse(edited.getIddoctypeName().equals(idt.getIddoctypeName()));
	}
}
