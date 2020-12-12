package taller2.Palma.demo.REST;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.DocumentTypeService;
import taller2.Palma.demo.service.PersonService;

@RestController
@RequestMapping("/RestDocument")
public class DocumentRest {
	private DocumentService serv;
	private DocumentTypeService dts;
	private PersonService ps;
	
	public DocumentRest(DocumentService s, DocumentTypeService d, PersonService pers) {
		serv=s;
		dts=d;
	}
	
	@PostMapping("/")
	public void createDocument(@RequestBody Documentt doc) {
		serv.addDoc(doc);
	}
	
	@DeleteMapping("/{docId}")
	public void deleteDoc(@PathVariable long docId) {
		Optional<Documentt> deleted= serv.getDoc(docId);
		if(!deleted.isEmpty()) {
			serv.delete(deleted.get());
		}
	}
	
	@PutMapping("/{docId}")
	public void editDoc(@PathVariable long docId,@RequestBody Documentt documentt) {
		serv.update(documentt);
	}
	
	@GetMapping("/{docId}")
	public Documentt getDoc(@PathVariable long docId) {
		return serv.getDoc(docId).get();
	}
	
	@GetMapping("/")
	public Iterable<Documentt> getDocs(){
		return serv.getDocs();
	}
	
	@GetMapping("/docType/{doctypeId}")
	public Iterable<Documentt> getDocsByType(@PathVariable long doctypeId){	
		return serv.findDocsByType(dts.getDocType(doctypeId).get());
	}
	
	@GetMapping("/person/{persId}")
	public Iterable<Documentt> getDocsByPerson(@PathVariable long persId){
		return serv.findDocs(ps.getPerson(persId).get());
	}
}
