package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Documenttype;

public class DocTypeList {
	
	private List<Documenttype> types;
	
	public void DocTypeList() {
		types= new ArrayList();
	}
	
	public List<Documenttype> getList(){
		return types;
	}
	
	public void setList(ArrayList<Documenttype> list) {
		types=list;
	}
}
