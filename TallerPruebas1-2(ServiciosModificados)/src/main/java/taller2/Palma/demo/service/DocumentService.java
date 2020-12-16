package taller2.Palma.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import taller2.Palma.demo.DAOimp.DocumentDAO;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.repository.DocumentRepository;
import taller2.Palma.demo.serviceInt.DocumentServiceInterface;

@Service
public class DocumentService implements DocumentServiceInterface {
	
	private DocumentDAO repo;
	
	public DocumentService(DocumentDAO repo) {
		this.repo=repo;
	}
	public Documentt addDoc(Documentt doc) {
		
		repo.save(doc);
		return doc;
	}
	public Documentt update(Documentt doc) {
		Documentt edited=repo.findById(doc.getDocId());
		edited=doc;
		repo.update(edited);
		return edited;
	}
	public Optional<Documentt> getDoc(long docId) {
		
		return Optional.of(repo.findById(docId));
	}
	public void delete(Documentt test) {
		// TODO Auto-generated method stub
		repo.delete(test);
	}
	public List<Documentt> getDocs(){
		return repo.findAll();
	}
	
	public List<Documentt> findDocs(Person person){
		return repo.findByPerson(person);
	}
	public List<Documentt> findDocsByType(Documenttype type){
		return repo.findByType(type);
	}
}
