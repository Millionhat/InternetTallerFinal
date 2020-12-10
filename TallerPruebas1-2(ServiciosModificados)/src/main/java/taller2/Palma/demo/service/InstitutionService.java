package taller2.Palma.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.Palma.demo.DAOimp.InstitutionDAO;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.repository.InstitutionRepository;
import taller2.Palma.demo.serviceInt.InstitutionServiceInterface;

@Service
public class InstitutionService implements InstitutionServiceInterface{
	
	private InstitutionDAO insRepo;
	
	public InstitutionService(InstitutionDAO repo) {
		insRepo=repo;
	}

	public Institution getInstitution(Long institution) {
		
		return insRepo.findById(institution);
	}
	
	public Institution addInstituion(Institution ins) {
		insRepo.save(ins);
		return ins;
	}
	
	public Iterable<Institution> getInstitutions(){
		return insRepo.findAll();
	}

}
