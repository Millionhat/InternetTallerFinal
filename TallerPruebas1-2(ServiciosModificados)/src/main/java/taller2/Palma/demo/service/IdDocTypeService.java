package taller2.Palma.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import taller2.Palma.demo.DAOimp.IdDocTypeDAO;
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.repository.IdDocTypeRepository;

@Service
public class IdDocTypeService {
	
	private IdDocTypeDAO repo;
	
	public IdDocTypeService(IdDocTypeDAO idtr) {
		repo=idtr;
	}
	
	public Iddocumenttype addIDT(Iddocumenttype idt) {
		repo.save(idt);
		return idt;
	}
	
	public Optional<Iddocumenttype> getIDT(long IDTId){
		return Optional.of(repo.findByI(IDTId));
	}
	
	public void delete(Iddocumenttype IDT) {
		repo.delete(IDT);
	}
	
	public List<Iddocumenttype> getIDTS(){
		return repo.findAll();
	}
	
	public void updateIDT(Iddocumenttype idt) {
		Iddocumenttype edited= repo.findByI(idt.getIddoctypeId());
		edited=idt;
		repo.update(edited);
	}

}
