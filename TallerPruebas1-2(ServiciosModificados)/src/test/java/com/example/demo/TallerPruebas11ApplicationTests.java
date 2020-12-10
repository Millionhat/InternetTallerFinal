package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.repository.DocumentInstanceStateRepository;
import taller2.Palma.demo.repository.DocumentRepository;
import taller2.Palma.demo.repository.DocumentStateRepository;
import taller2.Palma.demo.repository.DocumentTypeRepository;
import taller2.Palma.demo.repository.InstitutionRepository;
import taller2.Palma.demo.repository.PersonRepository;
import taller2.Palma.demo.service.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TallerPruebas11ApplicationTests {
	
	@Mock
	private PersonRepository prepo;
	@Mock
	private DocumentRepository drepo;
	@Mock
	private DocumentInstanceStateRepository disrepo;
	@Mock
	private DocumentStateRepository dsrepo;
	@Mock
	private InstitutionRepository irepo;
	@Mock
	private DocumentTypeRepository dtrepo;
	@InjectMocks
	private PersonService ps;
	@InjectMocks
	private DocumentStateService dss;
	private DocumentService ds;
	private DocStateInstanceService dsis;
	private InstitutionService is;
	private DocumentTypeService dts;
	@BeforeEach
	void setMockUp() {
		when(prepo.save(null)).thenReturn(new Person());
		when(prepo.findById(null).get()).thenReturn(new Person());
	}


}
