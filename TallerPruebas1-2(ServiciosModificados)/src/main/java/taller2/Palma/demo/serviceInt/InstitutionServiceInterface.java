package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Institution;

public interface InstitutionServiceInterface {
	public Optional<Institution> getInstitution(Long institution);
	public Institution addInstituion(Institution ins);
	public Institution update (Institution ins);
	public void delete(Institution ins);
}
