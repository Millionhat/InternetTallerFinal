package taller2.Palma.demo.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	@PostMapping(path="/",consumes= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	},produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<Documenttype> createDocType(@RequestBody Documenttype dt) throws NonNullValueException {
		Documenttype returnValue= new Documenttype();
		returnValue.setDoctypeId(dt.getDoctypeId());
		returnValue.setDoctypeName(dt.getDoctypeName());
		
		serv.addDT(returnValue);
		
		return new ResponseEntity<Documenttype>(returnValue,HttpStatus.OK);
	
		
	}
	
	
	@DeleteMapping(path="/{doctypeId}")
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
	
	@GetMapping(path="/{doctypeId}",produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<Documenttype> getDocType(@PathVariable long doctypeId) {
		
		return new ResponseEntity<Documenttype>(serv.getDocType(doctypeId).get(),HttpStatus.OK);
	}
	
	@GetMapping(value="/")
	public Iterable<Documenttype> getDocTypes(){
		
		List<Documenttype> document = serv.getDocTypes();
		
		return document;
	}
}
