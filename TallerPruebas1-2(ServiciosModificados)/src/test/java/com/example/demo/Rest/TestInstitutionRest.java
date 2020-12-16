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

import taller2.Palma.demo.REST.InstitutionRest;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.service.InstitutionService;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@WebMvcTest(value=InstitutionRest.class)
public class TestInstitutionRest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private InstitutionService service;
	
	@Test
	public void RetriveInstitution() throws Exception {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		
		List<Institution> instace= new ArrayList();
		instace.add(i1);
		
		when(service.getInstitutions()).thenReturn(instace);
		
		MvcResult result= mvc
				.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expected= "["+"{instId: 0,instAcademicserverurl: null,instAcadextradataurl: null,instAcadloginpassword: null,instAcadloginurl: null,instAcadloginusername: null,"+
		        "instAcadpersoninfodocurl: null,instAcadpersoninfoidurl: null,instAcadphysicalspacesurl: null,instAcadprogrammedcoursesurl: null,instLdapbasedn: null,instLdappassword: null,"+
		        "instLdapurl: null,instLdapusername: null,instLdapusersearchbase: null,instLdapusersearchfilter: null,instName: Icesi,persons: null}"+"]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		    
	}
	
	@Test
	public void CreateInstitution() throws Exception {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		String ins= "["+"{instId: 0,instAcademicserverurl: null,instAcadextradataurl: null,instAcadloginpassword: null,instAcadloginurl: null,instAcadloginusername: null,"+
		        "instAcadpersoninfodocurl: null,instAcadpersoninfoidurl: null,instAcadphysicalspacesurl: null,instAcadprogrammedcoursesurl: null,instLdapbasedn: null,instLdappassword: null,"+
		        "instLdapurl: null,instLdapusername: null,instLdapusersearchbase: null,instLdapusersearchfilter: null,instName: Icesi,persons: null}"+"]";
		
		when(service.addInstituion(any())).thenReturn(i1);
		
		mvc.perform(
				MockMvcRequestBuilders.post("/").content(ins).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateInstitution() throws Exception {
		Institution i1= new Institution();
		i1.setInstName("Icesi");
		String ins= "["+"{instId: 0,instAcademicserverurl: null,instAcadextradataurl: null,instAcadloginpassword: null,instAcadloginurl: null,instAcadloginusername: null,"+
		        "instAcadpersoninfodocurl: null,instAcadpersoninfoidurl: null,instAcadphysicalspacesurl: null,instAcadprogrammedcoursesurl: null,instLdapbasedn: null,instLdappassword: null,"+
		        "instLdapurl: null,instLdapusername: null,instLdapusersearchbase: null,instLdapusersearchfilter: null,instName: Icesi,persons: null}"+"]";
		
		when(service.update(any())).thenReturn(i1);
		
		MvcResult result =mvc.perform(
				MockMvcRequestBuilders.put("/"+ i1.getInstId()).content(ins).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		JSONAssert.assertEquals(ins, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void deleteInstitution() throws Exception {
			Institution i1= new Institution();
			i1.setInstName("Icesi");
			
			doNothing().when(service).delete(any());
			
			mvc.perform( MockMvcRequestBuilders.delete("/"+ i1.getInstId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
