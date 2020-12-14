package com.example.demo.Delegate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import taller2.Palma.demo.delegate.UserDelegate;
import taller2.Palma.demo.model.Userr;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
public class UserDelegateTest {
	
	@Mock
	private UserDelegate delegate;
	
	@BeforeEach
	void setup() {
		
	}
	@Test
	void testCreateUser() {
		Userr u1 = new Userr();
		u1.setUserName("m");
		u1.setUserPassword("123");
		
		when(delegate.getuser(u1.getUserId())).thenReturn(u1);
		
		delegate.createUser(u1);
		
		Userr test= delegate.getuser(u1.getUserId());
		
		assertTrue(test.getUserName().equals(u1.getUserName()));
	}
	
	@Test
	void testUpdateUse() {
		Userr u1 = new Userr();
		u1.setUserName("m");
		u1.setUserPassword("123");
		
		delegate.createUser(u1);
		
		when(delegate.getuser(u1.getUserId())).thenReturn(u1);
		
		Userr uNew= new Userr();
		uNew.setUserName("n");
		uNew.setUserPassword("456");
		uNew.setUserId(u1.getUserId());
		
		delegate.updateUser(u1.getUserId(), uNew);
		
		Userr response= delegate.getuser(u1.getUserId());
		
		assertTrue(response.getUserId()==u1.getUserId());
		assertTrue(response.equals(u1));
		
	}
	
	@Test
	void testDeleteUser() {
		Userr u1 = new Userr();
		u1.setUserName("m");
		u1.setUserPassword("123");
		
		delegate.createUser(u1);
		
		delegate.deleteUser(u1.getUserId());
		
		List<Userr> list=  new ArrayList();
		when(delegate.getGroupUser()).thenReturn(list);
		
		List<Userr> answers= (List<Userr>) delegate.getGroupUser();
		assertTrue(answers.size()==0);
		
	}
	
	
	@Test
	void testGetGroupUser() {
		Userr u1 = new Userr();
		u1.setUserName("m");
		u1.setUserPassword("123");
		
		Userr u2 = new Userr();
		u2.setUserName("l");
		u2.setUserPassword("456");

		Userr u3 = new Userr();
		u3.setUserName("n");
		u3.setUserPassword("789");
		
		
		List<Userr> list= new ArrayList();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		
		when(delegate.getGroupUser()).thenReturn(list);
		
		List<Userr> answers= (List<Userr>) delegate.getGroupUser();
		assertTrue(answers.size()==3);
		assertTrue(answers.get(0).getUserId()==u1.getUserId());
		
	}
}
