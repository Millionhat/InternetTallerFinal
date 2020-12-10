package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.service.DocumentTypeService;

@SpringBootTest
class DocTypeServiceTest {
	@Mock
	private DocumentTypeService dts;
	
	public static Documenttype test= new Documenttype();
	@BeforeEach
	void setUp() {
		test.setDoctypeId(1);
		test.setDoctypeName("Prueba");
	}

	@Test
	void testAddDT() {
		try {
			when(dts.addDT(test)).thenReturn(test);
			assertTrue(dts.addDT(test)!=null);
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Test
	void testAddDT2() {
		try {
			when(dts.addDT(test)).thenThrow(NullPointerException.class);
			test.setDoctypeName("");
			
			assertThrows(NullPointerException.class, () -> {dts.addDT(test);});
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	void testEditDT() {
		try {
			when(dts.update(test)).thenThrow(NullPointerException.class);
			test.setDoctypeName("");
			assertThrows(NullPointerException.class, () -> {dts.update(test);});
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	void testEditDT2() {
		try {
			when(dts.update(test)).thenReturn(test);
			test.setDoctypeName("hey");
			assertTrue(dts.update(test).getDoctypeName().equals("hey"));
		} catch (NonNullValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
