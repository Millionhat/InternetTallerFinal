package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Institution;

public class InstitutionList {
	
	private List<Institution> institution;
	
	
	public InstitutionList() {
		institution= new ArrayList();
		
	}
	
	public List<Institution> getList(){
		return institution;
	}
	
	public void setList(ArrayList<Institution> list) {
		institution= list;
	}

}
