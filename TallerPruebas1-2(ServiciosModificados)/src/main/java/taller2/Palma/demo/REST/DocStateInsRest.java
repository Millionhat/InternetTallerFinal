package taller2.Palma.demo.REST;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.exception.NonValidDateException;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.service.DocStateInstanceService;
import taller2.Palma.demo.service.DocumentService;

@RestController
@RequestMapping("/RestDocStateIns")
public class DocStateInsRest {
	
	private DocStateInstanceService serv;
	private DocumentService docs;
	
	public DocStateInsRest(DocStateInstanceService s, DocumentService d) {
		serv=s;
		docs=d;
	}
	
	@PostMapping(path="/",consumes= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	},produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<Docstateinstance> createDocInsState(@RequestBody Docstateinstance dts) throws NonValidDateException {
		Docstateinstance returnValue = new Docstateinstance();
		returnValue=dts;
		
		serv.addDTS(dts);
		
		return new ResponseEntity<Docstateinstance>(returnValue,HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{docstatinsId}")
	public void deleteDocsStateIns(@PathVariable long docstatinsId) {
		Optional<Docstateinstance> deleted= serv.getDocInstance(docstatinsId);
		if(!deleted.isEmpty()) {
			serv.delete(deleted.get());
		}
	}
	
	@PutMapping("/{docstatinsId}")
	public void editDocType(@PathVariable long docstatinsId, @RequestBody Docstateinstance dsi) throws NonValidDateException {
		serv.update(dsi);
	}
	
	@GetMapping("/{docstatinsId}")
	public Docstateinstance getDSI(@PathVariable long docstatinsId) {
		return serv.getDocInstance(docstatinsId).get();
	}
	
	@GetMapping(value="/")
	public Iterable<Docstateinstance> getDSIS(){
		return serv.getDocInstances();
	}
	
	@GetMapping("/byDoc/{docId}")
	public Iterable<Docstateinstance> getDSIbyDoc(@PathVariable long docId){
		return serv.getInstancesForDoc(docs.getDoc(docId).get());
	}
}
