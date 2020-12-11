package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Documentt;

public class DocumenttList {
	
	private List<Documentt> docs;
	
	public DocumenttList() {
		docs= new ArrayList();
	}
	
	public List<Documentt> getList(){
		return docs;
	}
	
	public void setList(ArrayList<Documentt> list) {
		docs=list;
	}
}
