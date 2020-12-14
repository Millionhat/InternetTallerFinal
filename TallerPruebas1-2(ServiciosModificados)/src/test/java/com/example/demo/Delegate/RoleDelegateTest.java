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

import taller2.Palma.demo.delegate.RoleDelegate;
import taller2.Palma.demo.model.Rolee;

@SpringBootTest(classes=taller2.Palma.demo.TallerPruebas11Application.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
public class RoleDelegateTest {
	
	@Mock
	private RoleDelegate delegate;
	
	@BeforeEach
	void setup() {
		
	}
	
	@Test
	void testCreateRole() {
		Rolee r1= new Rolee();
		r1.setRoleName("Admin");
		
		when(delegate.getRole(r1.getRoleId())).thenReturn(r1); 
		
		delegate.createRole(r1);
		
		Rolee test= delegate.getRole(r1.getRoleId());
		
		assertTrue(test.getRoleName().equals(r1.getRoleName()));
		
	}
	
	@Test
	void testUpdateRole() {
		Rolee r1= new Rolee();
		r1.setRoleName("Admin");
		
		delegate.createRole(r1);
		
		when(delegate.getRole(r1.getRoleId())).thenReturn(r1); 
		
		Rolee rNew= new Rolee();
		rNew.setRoleName("Estudent");
		rNew.setRoleId(r1.getRoleId());
		
		delegate.updateRole(r1.getRoleId(), rNew);
		
		Rolee test= delegate.getRole(r1.getRoleId());
		
		assertTrue(test.getRoleId()==r1.getRoleId());
		assertTrue(test.equals(r1));
	}
	
	@Test
	void testDeleteRole() {
		Rolee r1= new Rolee();
		r1.setRoleName("Admin");
		
		delegate.createRole(r1);
		
		delegate.deleteRole(r1.getRoleId());
		
		List<Rolee> list=  new ArrayList();
		when(delegate.getGroupRoles()).thenReturn(list);
		
		List<Rolee> answers = (List<Rolee>) delegate.getGroupRoles();
		assertTrue(answers.size()==0);
	}
	
	@Test
	void testGetGroupRoles() {
		Rolee r1= new Rolee();
		r1.setRoleName("Admin");
		
		Rolee r2= new Rolee();
		r1.setRoleName("Estudent");
		
		Rolee r3= new Rolee();
		r1.setRoleName("Profesor");
		
		List<Rolee> list = new ArrayList();
		list.add(r1);
		list.add(r2);
		list.add(r3);
		
		when(delegate.getGroupRoles()).thenReturn(list);
		
		List<Rolee> response= (List<Rolee>) delegate.getGroupRoles();
		
		assertTrue(response.size()==3);
		assertTrue(response.get(0).getRoleId()==r1.getRoleId());
	}

}
