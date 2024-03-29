package taller2.Palma.demo.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PHYSICALSPACETYPE database table.
 * 
 */
@Entity
@NamedQuery(name="Physicalspacetype.findAll", query="SELECT p FROM Physicalspacetype p")
public class Physicalspacetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PHYSICALSPACETYPE_PHYSPCTYPEID_GENERATOR", sequenceName="PHYSICALSPACETYPE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHYSICALSPACETYPE_PHYSPCTYPEID_GENERATOR")
	@Column(name="PHYSPCTYPE_ID")
	private long physpctypeId;

	@Column(name="PHYSPCTYPE_EXTID")
	private String physpctypeExtid;

	@Column(name="PHYSPCTYPE_IMPLIESCOMM")
	private String physpctypeImpliescomm;

	@Column(name="PHYSPCTYPE_NAME")
	private String physpctypeName;

	//bi-directional many-to-one association to Physicalspace
	@OneToMany(mappedBy="physicalspacetype")
	private List<Physicalspace> physicalspaces;

	//bi-directional many-to-one association to Institution
	@ManyToOne
	@JoinColumn(name="INST_INST_ID")
	private Institution institution;

	public Physicalspacetype() {
	}

	public long getPhyspctypeId() {
		return this.physpctypeId;
	}

	public void setPhyspctypeId(long physpctypeId) {
		this.physpctypeId = physpctypeId;
	}

	public String getPhyspctypeExtid() {
		return this.physpctypeExtid;
	}

	public void setPhyspctypeExtid(String physpctypeExtid) {
		this.physpctypeExtid = physpctypeExtid;
	}

	public String getPhyspctypeImpliescomm() {
		return this.physpctypeImpliescomm;
	}

	public void setPhyspctypeImpliescomm(String physpctypeImpliescomm) {
		this.physpctypeImpliescomm = physpctypeImpliescomm;
	}

	public String getPhyspctypeName() {
		return this.physpctypeName;
	}

	public void setPhyspctypeName(String physpctypeName) {
		this.physpctypeName = physpctypeName;
	}

	public List<Physicalspace> getPhysicalspaces() {
		return this.physicalspaces;
	}

	public void setPhysicalspaces(List<Physicalspace> physicalspaces) {
		this.physicalspaces = physicalspaces;
	}

	public Physicalspace addPhysicalspace(Physicalspace physicalspace) {
		getPhysicalspaces().add(physicalspace);
		physicalspace.setPhysicalspacetype(this);

		return physicalspace;
	}

	public Physicalspace removePhysicalspace(Physicalspace physicalspace) {
		getPhysicalspaces().remove(physicalspace);
		physicalspace.setPhysicalspacetype(null);

		return physicalspace;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}