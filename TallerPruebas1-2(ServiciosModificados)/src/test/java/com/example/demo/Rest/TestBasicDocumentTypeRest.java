package com.example.demo.Rest;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import taller2.Palma.demo.delegate.DocumentTypeDelegate;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DocumentTypeDelegate.class)
public class TestBasicDocumentTypeRest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void welcome() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("Hello World")));
	}

}
