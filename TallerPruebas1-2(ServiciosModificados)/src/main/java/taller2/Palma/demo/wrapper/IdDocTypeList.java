package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Iddocumenttype;

public class IdDocTypeList {
	
	private List<Iddocumenttype> idTypes;
	
	public IdDocTypeList() {
		idTypes=new ArrayList();
	}
	
	public List<Iddocumenttype> getList(){
		return idTypes;
	}
	
	public void setList(ArrayList types) {
		idTypes=types;
	}
}
