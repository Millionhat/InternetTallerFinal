package taller2.Palma.demo.REST;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.service.InstitutionService;

@RestController
@RequestMapping("/RestInstitution")
public class InstitutionRest {
	private InstitutionService service;
	
	public InstitutionRest(InstitutionService s) {
		service=s;
	}
	
	@PostMapping("/")
	public void createInstitution(@RequestBody Institution ins) {
		service.addInstituion(ins);
	}
	
	@DeleteMapping("/{insId}")
	public void deleteInstitution(@PathVariable long insId) {
		Optional<Institution> deleted= service.getInstitution(insId);
		if(!deleted.isEmpty()) {
			service.delete(deleted.get());
		}
		
	}
	@GetMapping("/{insId}")
	public Institution getInstitution(@PathVariable long insId) {
		return service.getInstitution(insId).get();
	}
	
	@GetMapping("/")
	public Iterable<Institution> getInstitutions(){
		return service.getInstitutions();
	}
}

