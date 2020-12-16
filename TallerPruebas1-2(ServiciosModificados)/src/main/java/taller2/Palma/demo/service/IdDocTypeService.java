package taller2.Palma.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAOimp.IdDocTypeDAO;
import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.repository.IdDocTypeRepository;

@Service
public class IdDocTypeService {
	
	private IdDocTypeDAO repo;
	
	public IdDocTypeService(IdDocTypeDAO idtr) {
		repo=idtr;
	}
	
	@Transactional
	public Iddocumenttype addIDT(Iddocumenttype idt) {
		repo.save(idt);
		return idt;
	}
	
	@Transactional(readOnly=true)
	public Optional<Iddocumenttype> getIDT(long IDTId){
		return Optional.of(repo.findByI(IDTId));
	}
	
	@Transactional
	public void delete(Iddocumenttype IDT) {
		repo.delete(IDT);
	}
	
	@Transactional(readOnly=true)
	public List<Iddocumenttype> getIDTS(){
		return repo.findAll();
	}
	
	@Transactional
	public void updateIDT(Iddocumenttype idt) {
		Iddocumenttype edited= repo.findByI(idt.getIddoctypeId());
		edited=idt;
		repo.update(edited);
	}

}
