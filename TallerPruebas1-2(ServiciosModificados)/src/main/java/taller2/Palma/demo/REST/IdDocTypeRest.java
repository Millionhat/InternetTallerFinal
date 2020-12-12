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
	
	@PostMapping("/")
	public void createDocument(@RequestBody Iddocumenttype idt) {
		serv.addIDT(idt);
	}
	
	@DeleteMapping("/{iddoctypeId}")
	public void deleteDoc(@PathVariable long iddoctypeId) {
		Optional<Iddocumenttype> deleted= serv.getIDT(iddoctypeId);
		if(!deleted.isEmpty()) {
			serv.delete(deleted.get());
		}
	}
	
//	@PutMapping("/{iddoctypeId}")
//	public void editIdDocType(@PathVariable long iddocumentId,@RequestBody Iddocumenttype idt) {
//		serv.
//	}
	
	@GetMapping("/{iddocumenttypeId}")
	public Iddocumenttype getType(@PathVariable long iddocumenttypeId) {
		return serv.getIDT(iddocumenttypeId).get();
	}
	
	@GetMapping("/")
	public Iterable<Iddocumenttype> getTypes(){
		return serv.getIDTS();
	}
}
