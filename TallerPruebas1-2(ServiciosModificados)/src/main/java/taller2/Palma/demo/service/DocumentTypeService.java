package taller2.Palma.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAOimp.DocTypeDAO;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.repository.DocumentTypeRepository;
import taller2.Palma.demo.serviceInt.DocumentTypeServiceInterface;

@Service
public class DocumentTypeService implements DocumentTypeServiceInterface{
	
	private DocTypeDAO dtRepo;
	
	public DocumentTypeService(DocTypeDAO repo) {
		dtRepo=repo;
	}

	@Transactional
	public Documenttype addDT(Documenttype dt) throws NonNullValueException{
		if(dt.getDoctypeName()!=null||dt.getDoctypeName()!="") {
			dtRepo.save(dt);
		return dt;
		}else {
			throw new NonNullValueException();
		}
	}

	@Transactional
	public Documenttype update(Documenttype dt) throws NonNullValueException {
		if(dt.getDoctypeName()!=null || dt.getDoctypeName()!="") {
			Documenttype edit=dtRepo.findById(dt.getDoctypeId());
			dt.setDoctypeId(edit.getDoctypeId());
			dtRepo.update(dt);
			return dt;
		}else {
			throw new NonNullValueException();
		}
	}

	@Transactional(readOnly=true)
	public Optional<Documenttype> getDocType(Long doctypeId) {
		return Optional.of(dtRepo.findById(doctypeId));
	}

	@Transactional
	public void delete(Long doctypeId) {
		// TODO Auto-generated method stub
		Documenttype tobeDeleted= dtRepo.findById(doctypeId);
		dtRepo.delete(tobeDeleted);
	}
	
	@Transactional(readOnly=true)
	public List<Documenttype> getDocTypes(){
		return dtRepo.findAll();
	}
}
