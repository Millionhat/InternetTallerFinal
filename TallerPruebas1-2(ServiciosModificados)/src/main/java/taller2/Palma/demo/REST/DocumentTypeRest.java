package taller2.Palma.demo.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.service.DocumentTypeService;

@RestController
@RequestMapping("/docType/RestDocType")
public class DocumentTypeRest {
	
	private DocumentTypeService serv;
	
	public DocumentTypeRest(DocumentTypeService s) {
		serv=s;
	}
	
	@PostMapping(value="/")
	public void createDocType(@RequestBody Documenttype dt, UriComponentsBuilder builder) throws NonNullValueException {
		serv.addDT(dt);
	}
	
	
	@DeleteMapping("/{doctypeId}")
	public void deleteDocType(@PathVariable long doctypeId) {
		Optional<Documenttype> deleted=serv.getDocType(doctypeId);
		if(!deleted.isEmpty()) {
			serv.delete(doctypeId);
		}
	}
	
	@PutMapping("/{doctypeId}")
	public void editDocType(@PathVariable long doctypeId,@RequestBody Documenttype documenttype) throws NonNullValueException {
		serv.update(documenttype);
	}
	
	@GetMapping("/{doctypeId}")
	public Documenttype getDocType(@PathVariable long doctypeId) {
		return serv.getDocType(doctypeId).get();
	}
	
	@GetMapping(value="/")
	public Iterable<Documenttype> getDocTypes(){
		//List<Documenttype> document = serv.getDocTypes();
		List<Documenttype> document = new ArrayList();
		Documenttype dt=new Documenttype();
		dt.setDoctypeName("Excel");
		dt.setDoctypeIsactive("Verdadero");
		
		document.add(dt);
		
		return document;
	}
}
