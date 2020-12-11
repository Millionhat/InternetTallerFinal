package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Userr;

public class UserList {
	private List<Userr> users;

	public UserList() {
		users= new ArrayList();
	}
	
	public List<Userr> getUsers(){
		return users;
	}
	
	public void setUsers(ArrayList<Userr> list) {
		users=list;
	}
}
