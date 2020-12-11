package taller2.Palma.demo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.Palma.demo.DAOimp.InstitutionDAO;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.repository.InstitutionRepository;
import taller2.Palma.demo.serviceInt.InstitutionServiceInterface;

@Service
public class InstitutionService implements InstitutionServiceInterface{
	
	private InstitutionDAO insRepo;
	
	public InstitutionService(InstitutionDAO repo) {
		insRepo=repo;
	}

	public Optional<Institution> getInstitution(Long institution) throws NoSuchElementException {
		
		return Optional.of(insRepo.findById(institution));
	}
	
	public Institution addInstituion(Institution ins) {
		insRepo.save(ins);
		return ins;
	}
	
	public Iterable<Institution> getInstitutions(){
		return insRepo.findAll();
	}

	@Override
	public Institution update(Institution ins) {
		// TODO Auto-generated method stub
		Institution editIsn= insRepo.findById(ins.getInstId());
		editIsn=ins;
		insRepo.update(editIsn);
		return editIsn;
	}

	@Override
	public void delete(Institution ins) {
		// TODO Auto-generated method stub
		insRepo.delete(ins);
		
	}

}
