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

import taller2.Palma.demo.delegate.DocumentTypeDelegate;
import taller2.Palma.demo.model.Documenttype;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
class DocumentTypeDelegateTest {
	
	
	@Mock
	private DocumentTypeDelegate dtd;
	

	@Test
	void testCreateDocType() {
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		dtd.createDocType(dt);
		
		
		List<Documenttype> types= new ArrayList();
		types.add(dt);
		when(dtd.getGroupDocTypeData()).thenReturn(types);
		
		List<Documenttype> saved=(List<Documenttype>) dtd.getGroupDocTypeData();
		
		assertTrue(saved.size()==1);
		assertTrue(saved.get(0).equals(dt));
		
	}
	
	@Test
	void testGetDocType() {
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		dtd.createDocType(dt);
		
		
		Documenttype t1= new Documenttype();
		t1.setDoctypeName("Word");
		t1.setDoctypeIsactive("True");
		dtd.createDocType(t1);
		
		Documenttype t2= new Documenttype();
		t2.setDoctypeName("PDF");
		t2.setDoctypeIsactive("False");
		
		List<Documenttype> types= new ArrayList();
		types.add(dt);
		types.add(t1);
		types.add(t2);
		
		when(dtd.getDocType(t2.getDoctypeId())).thenReturn(t2);
		when(dtd.getGroupDocTypeData()).thenReturn(types);
		
		Documenttype response=dtd.getDocType(t2.getDoctypeId());
		
		List<Documenttype> allT= (List<Documenttype>) dtd.getGroupDocTypeData();
		assertNotNull(response);
		assertTrue(response.getDoctypeName()=="PDF");
		
		assertTrue(allT.size()==3);
		assertTrue(allT.get(1)!=allT.get(2));
	}
    
	@Test
	void testUpdateDT() {
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		dtd.createDocType(dt);
		Documenttype dt1=dt;
		dt1.setDoctypeName("No es Excel");
		dtd.updateDocType(dt.getDoctypeId(), dt1);
		when(dtd.getDocType(dt.getDoctypeId())).thenReturn(dt1);
		
		Documenttype answer= dtd.getDocType(dt.getDoctypeId());
		
		assertTrue(answer.getDoctypeId()== dt.getDoctypeId());
		assertTrue(answer.equals(dt));
	}
	
	@Test
	void testDelete() {
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		dtd.createDocType(dt);
		
		
		Documenttype t1= new Documenttype();
		t1.setDoctypeName("Word");
		t1.setDoctypeIsactive("True");
		dtd.createDocType(t1);
		
		dtd.deleteDocType(dt.getDoctypeId());
		List<Documenttype> types= new ArrayList();
		types.add(t1);
		when(dtd.getGroupDocTypeData()).thenReturn(types);
		
		List<Documenttype> answers= (List<Documenttype>) dtd.getGroupDocTypeData();
		
		assertTrue(answers.size()==1);
		
	}
}
