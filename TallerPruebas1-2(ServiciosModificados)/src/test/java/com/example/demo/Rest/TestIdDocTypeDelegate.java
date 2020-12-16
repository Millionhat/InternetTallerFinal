package com.example.demo.Rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import taller2.Palma.demo.REST.IdDocTypeRest;
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.service.IdDocTypeService;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@WebMvcTest(IdDocTypeRest.class)
public class TestIdDocTypeDelegate {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IdDocTypeService service;
	
	@Test
	public void RetriveIdDocType() throws Exception {
		Iddocumenttype idt1= new Iddocumenttype();
		idt1.setIddoctypeName("Pasaporte");
		
		List<Iddocumenttype> instace= new ArrayList();
		instace.add(idt1);
		
		when(service.getIDTS()).thenReturn(instace);
		MvcResult result= mvc
				.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String expected= "["+ "iddoctypeId: 0,iddoctypeName: Pasaporte,instInstId: null,persons: null"+"]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void CreateIdDocType() throws Exception {
		Iddocumenttype idt1= new Iddocumenttype();
		idt1.setIddoctypeName("Pasaporte");
		
		String idDocumenttype= "["+ "iddoctypeId: 0,iddoctypeName: Pasaporte,instInstId: null,persons: null"+"]";
		
		when(service.addIDT(any())).thenReturn(idt1);
		
		mvc.perform(
				MockMvcRequestBuilders.post("/").content(idDocumenttype).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void UpdateIdDocType() throws Exception {
		Iddocumenttype idt1= new Iddocumenttype();
		idt1.setIddoctypeName("Pasaporte");
		String idDocumenttype= "["+ "iddoctypeId: 0,iddoctypeName: Pasaporte,instInstId: null,persons: null"+"]";
		
		doNothing().when(service).updateIDT(any());
		
		MvcResult result =mvc.perform(
				MockMvcRequestBuilders.put("/"+ idt1.getIddoctypeId()).content(idDocumenttype).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		JSONAssert.assertEquals(idDocumenttype, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void DeleteIdDocType() throws Exception {
				Iddocumenttype idt1= new Iddocumenttype();
				idt1.setIddoctypeName("Pasaporte");
				doNothing().when(service).delete(any());
				
				mvc.perform( MockMvcRequestBuilders.delete("/"+ idt1.getIddoctypeId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	

}
