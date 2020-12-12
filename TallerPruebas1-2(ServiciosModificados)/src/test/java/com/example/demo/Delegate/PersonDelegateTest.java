package com.example.demo.Delegate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.REST.PersonRest;
import taller2.Palma.demo.delegate.PersonDelegate;
import taller2.Palma.demo.exception.NonNullValueException;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
public class PersonDelegateTest {
	
	
	@Mock
	private PersonDelegate delegate;
	
	@Mock
	private PersonRest rest;
	
	
	@BeforeEach
	void setup() {
		
	}
	
	@Test
	void TestCreatePerson() {
		
		assertTrue(true);
	}
	
}
