package taller2.Palma.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly=true)
	public Optional<Institution> getInstitution(Long institution) throws NoSuchElementException {
		
		return Optional.of(insRepo.findById(institution));
	}
	
	@Transactional
	public Institution addInstituion(Institution ins) {
		insRepo.save(ins);
		return ins;
	}
	
	@Transactional(readOnly=true)
	public List<Institution> getInstitutions(){
		return insRepo.findAll();
	}

	@Transactional
	@Override
	public Institution update(Institution ins) {
		// TODO Auto-generated method stub
		Institution editIsn= insRepo.findById(ins.getInstId());
		editIsn=ins;
		insRepo.update(editIsn);
		return editIsn;
	}

	@Transactional
	@Override
	public void delete(Institution ins) {
		// TODO Auto-generated method stub
		insRepo.delete(ins);
		
	}

}
