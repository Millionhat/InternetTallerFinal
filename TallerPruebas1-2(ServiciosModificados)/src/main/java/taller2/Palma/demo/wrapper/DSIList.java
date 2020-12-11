package taller2.Palma.demo.wrapper;

import java.util.ArrayList;
import java.util.List;

import taller2.Palma.demo.model.Docstateinstance;

public class DSIList {

		private List<Docstateinstance> instances;
		
		public DSIList() {
			instances= new ArrayList();
		}
		
		public List<Docstateinstance> getInstances(){
			return instances;
		}
		
		public void setInstances(ArrayList<Docstateinstance> list) {
			instances=list;
		}
}
