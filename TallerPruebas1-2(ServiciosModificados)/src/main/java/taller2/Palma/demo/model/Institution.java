package taller2.Palma.demo.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the INSTITUTION database table.
 * 
 */
@Entity
@NamedQuery(name = "Institution.findAll", query = "SELECT i FROM Institution i")
public class Institution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "INSTITUTION_INSTID_GENERATOR", sequenceName = "INSTITUTION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTITUTION_INSTID_GENERATOR")
	@Column(name = "INST_ID")
	private long instId;

	@Column(name = "INST_ACADEMICSERVERURL")
	private String instAcademicserverurl;

	@Column(name = "INST_ACADEXTRADATAURL")
	private String instAcadextradataurl;

	@Column(name = "INST_ACADLOGINPASSWORD")
	private String instAcadloginpassword;

	@Column(name = "INST_ACADLOGINURL")
	private String instAcadloginurl;

	@Column(name = "INST_ACADLOGINUSERNAME")
	private String instAcadloginusername;

	@Column(name = "INST_ACADPERSONINFODOCURL")
	private String instAcadpersoninfodocurl;

	@Column(name = "INST_ACADPERSONINFOIDURL")
	private String instAcadpersoninfoidurl;

	@Column(name = "INST_ACADPHYSICALSPACESURL")
	private String instAcadphysicalspacesurl;

	@Column(name = "INST_ACADPROGRAMMEDCOURSESURL")
	private String instAcadprogrammedcoursesurl;

	@Column(name = "INST_LDAPBASEDN")
	private String instLdapbasedn;

	@Column(name = "INST_LDAPPASSWORD")
	private String instLdappassword;

	@Column(name = "INST_LDAPURL")
	private String instLdapurl;

	@Column(name = "INST_LDAPUSERNAME")
	private String instLdapusername;

	@Column(name = "INST_LDAPUSERSEARCHBASE")
	private String instLdapusersearchbase;

	@Column(name = "INST_LDAPUSERSEARCHFILTER")
	private String instLdapusersearchfilter;

	@Column(name = "INST_NAME")
	private String instName;

	

	// bi-directional many-to-one association to Person
	@OneToMany(mappedBy = "institution")
	private List<Person> persons;


	public Institution() {
	}

	public long getInstId() {
		return this.instId;
	}

	public void setInstId(int instId) {
		this.instId = instId;
	}

	public String getInstAcademicserverurl() {
		return this.instAcademicserverurl;
	}

	public void setInstAcademicserverurl(String instAcademicserverurl) {
		this.instAcademicserverurl = instAcademicserverurl;
	}

	public String getInstAcadextradataurl() {
		return this.instAcadextradataurl;
	}

	public void setInstAcadextradataurl(String instAcadextradataurl) {
		this.instAcadextradataurl = instAcadextradataurl;
	}

	public String getInstAcadloginpassword() {
		return this.instAcadloginpassword;
	}

	public void setInstAcadloginpassword(String instAcadloginpassword) {
		this.instAcadloginpassword = instAcadloginpassword;
	}

	public String getInstAcadloginurl() {
		return this.instAcadloginurl;
	}

	public void setInstAcadloginurl(String instAcadloginurl) {
		this.instAcadloginurl = instAcadloginurl;
	}

	public String getInstAcadloginusername() {
		return this.instAcadloginusername;
	}

	public void setInstAcadloginusername(String instAcadloginusername) {
		this.instAcadloginusername = instAcadloginusername;
	}

	public String getInstAcadpersoninfodocurl() {
		return this.instAcadpersoninfodocurl;
	}

	public void setInstAcadpersoninfodocurl(String instAcadpersoninfodocurl) {
		this.instAcadpersoninfodocurl = instAcadpersoninfodocurl;
	}

	public String getInstAcadpersoninfoidurl() {
		return this.instAcadpersoninfoidurl;
	}

	public void setInstAcadpersoninfoidurl(String instAcadpersoninfoidurl) {
		this.instAcadpersoninfoidurl = instAcadpersoninfoidurl;
	}

	public String getInstAcadphysicalspacesurl() {
		return this.instAcadphysicalspacesurl;
	}

	public void setInstAcadphysicalspacesurl(String instAcadphysicalspacesurl) {
		this.instAcadphysicalspacesurl = instAcadphysicalspacesurl;
	}

	public String getInstAcadprogrammedcoursesurl() {
		return this.instAcadprogrammedcoursesurl;
	}

	public void setInstAcadprogrammedcoursesurl(String instAcadprogrammedcoursesurl) {
		this.instAcadprogrammedcoursesurl = instAcadprogrammedcoursesurl;
	}

	public String getInstLdapbasedn() {
		return this.instLdapbasedn;
	}

	public void setInstLdapbasedn(String instLdapbasedn) {
		this.instLdapbasedn = instLdapbasedn;
	}

	public String getInstLdappassword() {
		return this.instLdappassword;
	}

	public void setInstLdappassword(String instLdappassword) {
		this.instLdappassword = instLdappassword;
	}

	public String getInstLdapurl() {
		return this.instLdapurl;
	}

	public void setInstLdapurl(String instLdapurl) {
		this.instLdapurl = instLdapurl;
	}

	public String getInstLdapusername() {
		return this.instLdapusername;
	}

	public void setInstLdapusername(String instLdapusername) {
		this.instLdapusername = instLdapusername;
	}

	public String getInstLdapusersearchbase() {
		return this.instLdapusersearchbase;
	}

	public void setInstLdapusersearchbase(String instLdapusersearchbase) {
		this.instLdapusersearchbase = instLdapusersearchbase;
	}

	public String getInstLdapusersearchfilter() {
		return this.instLdapusersearchfilter;
	}

	public void setInstLdapusersearchfilter(String instLdapusersearchfilter) {
		this.instLdapusersearchfilter = instLdapusersearchfilter;
	}

	public String getInstName() {
		return this.instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}


	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setInstitution(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setInstitution(null);

		return person;
	}

}