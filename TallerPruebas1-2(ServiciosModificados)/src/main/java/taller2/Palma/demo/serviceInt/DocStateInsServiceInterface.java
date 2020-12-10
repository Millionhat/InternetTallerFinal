package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.exception.NonValidDateException;
import taller2.Palma.demo.model.Docstateinstance;

public interface DocStateInsServiceInterface {
	public Docstateinstance addDTS(Docstateinstance test) throws NonValidDateException;
	public Docstateinstance update(Docstateinstance dsi) throws NonValidDateException;
	public void delete(Docstateinstance dsi);
	public Optional<Docstateinstance> getDocInstance(Long docstatinsId);
}
