package com.example.demo.Rest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import taller2.Palma.demo.REST.DocumentTypeRest;
import taller2.Palma.demo.delegate.DocumentTypeDelegate;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.service.DocumentTypeService;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@WebMvcTest(value= DocumentTypeRest.class)
public class TestDocumentTypeRest {
	

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DocumentTypeService service;
	
	
	@Test
	public void asas() {
		assertTrue(true);
	}
	@Test
	public void RetriveDocumentTypes() throws Exception {
		String url="http://localhost:8081/docType/RestDocType/";
		
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		
		
		List<Documenttype> types= new ArrayList();
		types.add(dt);
		
		
		
		when(service.getDocTypes()).thenReturn(types);
		MvcResult result= mvc
				.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expected = "[" + "{doctypeId:1,doctypeIsactive:Verdadero,doctypeName:Excel,instInstId:null,documentstates:null,documentts:null}" + "]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void createDocumentTypes() throws Exception {
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		
		String documenttype=  "{\"doctypeId\":\"1\",\"doctypeIsactive\":\"Verdadero\",\"doctypeName\":\"Excel\",\"instInstId\":\"null\",\"documentstates\":\"null\",\"documentts\":\"null\"}";
		
		when(service.addDT(dt)).thenReturn(dt);
		mvc.perform(
				MockMvcRequestBuilders.post("/").content(documenttype).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
}
