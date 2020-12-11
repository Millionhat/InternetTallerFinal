package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Rolee;

public class RoleList {
	private List<Rolee> roles;
	
	public RoleList() {
		roles= new ArrayList();
	}
	
	public List<Rolee> getRoles(){
		return roles;
	}
	
	public void setRoles(ArrayList<Rolee> list) {
		roles = list;
	}
}
