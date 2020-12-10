package taller2.Palma.demo.serviceInt;

import taller2.Palma.demo.model.Institution;

public interface InstitutionServiceInterface {
	public Institution getInstitution(Long institution);
	public Institution addInstituion(Institution ins);
}
