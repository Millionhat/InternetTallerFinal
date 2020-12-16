package taller2.Palma.demo.REST;

import java.util.ArrayList;
import java.util.List;
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

import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.service.IdDocTypeService;

@RestController
@RequestMapping("/RestIDdoc")
public class IdDocTypeRest {
	
	private IdDocTypeService serv;
	
	public IdDocTypeRest(IdDocTypeService s) {
		serv=s;
	}
	
	@PostMapping(path="/",consumes= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	},produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<Iddocumenttype> createIdDocType(@RequestBody Iddocumenttype idt) {
		serv.addIDT(idt);
		return new ResponseEntity<Iddocumenttype>(idt,HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{iddoctypeId}")
	public void deleteDoc(@PathVariable long iddoctypeId) {
		Optional<Iddocumenttype> deleted= serv.getIDT(iddoctypeId);
		if(!deleted.isEmpty()) {
			serv.delete(deleted.get());
		}
	}
	
	@PutMapping("/{iddoctypeId}")
	public void editIdDocType(@PathVariable long iddocumentId,@RequestBody Iddocumenttype idt) {
		serv.updateIDT(idt);
	}
	
	@GetMapping(path="/{iddocumenttypeId}",produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<Iddocumenttype> getType(@PathVariable long iddocumenttypeId) {
		return new ResponseEntity<Iddocumenttype>(serv.getIDT(iddocumenttypeId).get(),HttpStatus.OK);
	}
	
	@GetMapping(value="/")
	public List<Iddocumenttype> getTypes(){

		return serv.getIDTS();
	}
	
//	@PutMapping("/{iddoctypeId}")
//	public void updateIDT(@PathVariable long iddoctypeId,@RequestBody Iddocumenttype idt) {
//		serv.updateIDT(idt);
//	}
}
