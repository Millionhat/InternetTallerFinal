package taller2.Palma.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taller2.Palma.demo.DAOimp.DocStateDAO;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documentstate;
import taller2.Palma.demo.repository.DocumentInstanceStateRepository;
import taller2.Palma.demo.serviceInt.DocStateServiceInterface;

@Service
public class DocumentStateService {
	
	private DocStateDAO repo;
	
	public DocumentStateService(DocStateDAO repo) {
		this.repo=repo;
	}

	@Transactional
	public Documentstate addDSI(Documentstate ds) {
		repo.save(ds);
		return ds;
	}

	@Transactional
	public Documentstate update(Documentstate ds) {
		Documentstate edit= repo.findById(ds.getDocstatId());
		edit=ds;
		repo.update(edit);
		return edit;
	}

}
