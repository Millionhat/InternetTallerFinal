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

import taller2.Palma.demo.REST.DocumentRest;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.service.DocumentService;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@WebMvcTest(value= DocumentRest.class)
public class TestDocumentResst {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DocumentService service;
	
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void RetriveDocuments() throws Exception {
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		
		List<Documentt> instace= new ArrayList();
		instace.add(d1);
		
		when(service.getDocs()).thenReturn(instace);
		
		MvcResult result= mvc
				.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String expected= "["+ "{docId: 0,docIspositive: null,docName: hola,docUrl: null,docstateinstances: null,documenttype: null,person: null}"+"]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void CreateDocument() throws Exception {
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		
		String document= "["+ "{docId: 0,docIspositive: null,docName: hola,docUrl: null,docstateinstances: null,documenttype: null,person: null}"+"]";
		
		when(service.addDoc(any())).thenReturn(d1);
		
		mvc.perform(
				MockMvcRequestBuilders.post("/").content(document).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void udpateDocument() throws Exception {
		Documentt d1=new Documentt();
		d1.setDocName("hola");
		
		String document= "["+ "{docId: 0,docIspositive: null,docName: hola,docUrl: null,docstateinstances: null,documenttype: null,person: null}"+"]";
		
		when(service.update(any())).thenReturn(d1);
		
		MvcResult result =mvc.perform(
				MockMvcRequestBuilders.put("/"+ d1.getDocId()).content(document).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		JSONAssert.assertEquals(document, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void deleteDocument() throws Exception {
			Documentt d1=new Documentt();
			d1.setDocName("hola");
			
			doNothing().when(service).delete(any());
			
			mvc.perform( MockMvcRequestBuilders.delete("/"+ d1.getDocId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
			
	}

}



