package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.model.Documentt;

public interface DocumentServiceInterface {
	public Documentt addDoc(Documentt doc);
	public Documentt update(Documentt doc);
	public Optional<Documentt> getDoc(long docId);
	public void delete(Documentt test);
}
