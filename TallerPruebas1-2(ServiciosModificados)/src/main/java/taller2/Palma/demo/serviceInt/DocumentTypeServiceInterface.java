package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documenttype;

public interface DocumentTypeServiceInterface {
	public Documenttype addDT(Documenttype dt) throws NonNullValueException;
	public Documenttype update(Documenttype dt) throws NonNullValueException;
	public Optional<Documenttype> getDocType(Long doctypeId);
	public void delete(Long doctypeId);
}
