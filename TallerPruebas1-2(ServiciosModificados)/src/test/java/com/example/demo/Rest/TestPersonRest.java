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

import taller2.Palma.demo.REST.PersonRest;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.PersonService;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@WebMvcTest(value= PersonRest.class)
public class TestPersonRest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PersonService service;
	
	@Test
	public void RetrivePerson() throws Exception{
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		List<Person> instace= new ArrayList();
		instace.add(p1);
		
		when(service.getPeople()).thenReturn(instace);
		
		MvcResult result= mvc
				.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String expected= "["+ "{persId: 0,persAddress: null,persContactnumber: null,persEmail: j@gmail.com,persExtid: null,persIddocument: 111111,persIsactive: null,persLastname: juan,persLatitude: null,"+
	        "persLocaldata: null,persLongitude: null,persName: juan,persOnsetdate: null,persPoliticsaccepted: null,persPoliticsaccepteddate: null,accessdenialevents1: null,accessdenialevents2: null,attendances: null,"+
	        "contactfences: null,docstateinstances: null,documentts: null,followups1: null,followups2: null,iddocumenttype: null,institution: null,personautotrans: null,personrelationships1: null,personrelationships2: null,"+
	        "personFences: null,personRoles: null,personVulners: null,physicalcheckups: null,posessions: null,userrs: null,ustPersonNexuses: null,ustPersonStatuses: null,ustPersonSymptoms: null,ustSocialclosecontacts: null,visits: null}"+"]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void CreatedPerson() throws Exception {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		String person= "["+ "{persId: 0,persAddress: null,persContactnumber: null,persEmail: j@gmail.com,persExtid: null,persIddocument: 111111,persIsactive: null,persLastname: juan,persLatitude: null,"+
		        "persLocaldata: null,persLongitude: null,persName: juan,persOnsetdate: null,persPoliticsaccepted: null,persPoliticsaccepteddate: null,accessdenialevents1: null,accessdenialevents2: null,attendances: null,"+
		        "contactfences: null,docstateinstances: null,documentts: null,followups1: null,followups2: null,iddocumenttype: null,institution: null,personautotrans: null,personrelationships1: null,personrelationships2: null,"+
		        "personFences: null,personRoles: null,personVulners: null,physicalcheckups: null,posessions: null,userrs: null,ustPersonNexuses: null,ustPersonStatuses: null,ustPersonSymptoms: null,ustSocialclosecontacts: null,visits: null}"+"]";
		when(service.addPerson(any())).thenReturn(p1);
		
		mvc.perform(
				MockMvcRequestBuilders.post("/").content(person).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());	
		
	}
	
	@Test
	public void updatePerson() throws Exception {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		String person= "["+ "{persId: 0,persAddress: null,persContactnumber: null,persEmail: j@gmail.com,persExtid: null,persIddocument: 111111,persIsactive: null,persLastname: juan,persLatitude: null,"+
		        "persLocaldata: null,persLongitude: null,persName: juan,persOnsetdate: null,persPoliticsaccepted: null,persPoliticsaccepteddate: null,accessdenialevents1: null,accessdenialevents2: null,attendances: null,"+
		        "contactfences: null,docstateinstances: null,documentts: null,followups1: null,followups2: null,iddocumenttype: null,institution: null,personautotrans: null,personrelationships1: null,personrelationships2: null,"+
		        "personFences: null,personRoles: null,personVulners: null,physicalcheckups: null,posessions: null,userrs: null,ustPersonNexuses: null,ustPersonStatuses: null,ustPersonSymptoms: null,ustSocialclosecontacts: null,visits: null}"+"]";
		
		when(service.update(any())).thenReturn(p1);
		
		MvcResult result =mvc.perform(
				MockMvcRequestBuilders.put("/"+ p1.getPersId()).content(person).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		JSONAssert.assertEquals(person, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void deletePerson() throws Exception {
		Person p1= new Person();
		p1.setPersName("juan");
		p1.setPersLastname("juan");
		p1.setPersEmail("j@gmail.com");
		p1.setPersIddocument("111111");
		
		doNothing().when(service).delete(any());
		
		mvc.perform( MockMvcRequestBuilders.delete("/"+ p1.getPersId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}
	
