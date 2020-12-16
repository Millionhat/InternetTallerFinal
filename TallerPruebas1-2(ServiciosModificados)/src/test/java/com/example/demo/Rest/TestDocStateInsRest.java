package com.example.demo.Rest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
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
import org.mockito.Mockito;
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

import taller2.Palma.demo.REST.DocStateInsRest;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.service.DocStateInstanceService;
import taller2.Palma.demo.service.DocumentService;



@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@WebMvcTest(value= DocStateInsRest.class)
public class TestDocStateInsRest {


	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DocStateInstanceService service;
	
	@Test
	public void RetriveDocStateInstance() throws Exception {
		Docstateinstance d1= new Docstateinstance();
		d1.setDocstatinsStartdate(new Date(111));
		d1.setDocstatinsEnddate(new Date(222));
		
		
		List<Docstateinstance> instace= new ArrayList();
		instace.add(d1);
		
		
		when(service.getDocInstances()).thenReturn(instace);
		MvcResult result= mvc
				.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expected= "["+"{ docstatinsId: 0,docstatinsEnddate: 1970-01-01T00:00:00.222+00:00,docstatinsStartdate: 1970-01-01T00:00:00.111+00:00,documentstate: null,documentt:null,person: null}"+"]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createDocStateInstance() throws Exception {
		Docstateinstance d1= new Docstateinstance();
		d1.setDocstatinsStartdate(new Date(111));
		d1.setDocstatinsEnddate(new Date(222));
		
		String docstateinstance = "{ docstatinsId; 0,docstatinsEnddate: 1970-01-01T00:00:00.222+00:00,docstatinsStartdate: 1970-01-01T00:00:00.111+00:00,documentstate: null,documentt:null,person: null}";
		
		when(service.addDTS(any())).thenReturn(d1);
		
		mvc.perform(
				MockMvcRequestBuilders.post("/").content(docstateinstance).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateDocStateInstance() throws Exception {
		Docstateinstance d1= new Docstateinstance();
		d1.setDocstatinsStartdate(new Date(111));
		d1.setDocstatinsEnddate(new Date(222));
		
		String docstateinstance = "{ docstatinsId; 0,docstatinsEnddate: 1970-01-01T00:00:00.222+00:00,docstatinsStartdate: 1970-01-01T00:00:00.111+00:00,documentstate: null,documentt:null,person: null}";
		
		when(service.update(any())).thenReturn(d1);
		
		MvcResult result =mvc.perform(
				MockMvcRequestBuilders.put("/"+ d1.getDocstatinsId()).content(docstateinstance).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		JSONAssert.assertEquals(docstateinstance, result.getResponse().getContentAsString(), false);
		
	}
	@Test
	public void deleteDocStateInstance() throws Exception {
		Docstateinstance d1= new Docstateinstance();
		d1.setDocstatinsStartdate(new Date(111));
		d1.setDocstatinsEnddate(new Date(222));
		
		doNothing().when(service).delete(any());
		
		mvc.perform( MockMvcRequestBuilders.delete("/"+ d1.getDocstatinsId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}
