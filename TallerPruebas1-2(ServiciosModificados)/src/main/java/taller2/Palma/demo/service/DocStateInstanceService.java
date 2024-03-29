package taller2.Palma.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAOimp.DocInsStateDAO;
import taller2.Palma.demo.exception.NonValidDateException;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.repository.DocumentInstanceStateRepository;
import taller2.Palma.demo.serviceInt.DocStateInsServiceInterface;

@Service
public class DocStateInstanceService implements DocStateInsServiceInterface{
	
	private DocInsStateDAO repo;
	
	public DocStateInstanceService(DocInsStateDAO r) {
		repo=r;
	}

	@Transactional
	public Docstateinstance addDTS(Docstateinstance test) throws NonValidDateException{
		// TODO Auto-generated method stub
		if(test.getDocstatinsStartdate().compareTo(test.getDocstatinsEnddate())<=0) {
			repo.save(test);
			return test;
		}else {
			throw new NonValidDateException();
		}
	}

	@Transactional
	public Docstateinstance update(Docstateinstance dsi) throws NonValidDateException{
		// TODO Auto-generated method stub
		if(dsi.getDocstatinsStartdate().compareTo(dsi.getDocstatinsEnddate())<=0) {
		Docstateinstance edit=repo.findById(dsi.getDocstatinsId());
		edit=dsi;
		repo.update(edit);
		return dsi;
		}else {
			throw new NonValidDateException();
		}
	}

	@Transactional
	public void delete(Docstateinstance dsi) {
		repo.delete(dsi);
	}

	@Transactional(readOnly=true)
	public Optional<Docstateinstance> getDocInstance(Long docstatinsId) {
		
		return Optional.of(repo.findById(docstatinsId));
	}
	
	@Transactional(readOnly=true)
	public Iterable<Docstateinstance> getDocInstances(){
		return repo.findAll();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Docstateinstance> getInstancesForDoc(Documentt doc){
		return repo.findByDocumentt(doc);
	}
}
