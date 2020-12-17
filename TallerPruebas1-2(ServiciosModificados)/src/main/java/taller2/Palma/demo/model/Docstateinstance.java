package taller2.Palma.demo.model;


import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the DOCSTATEINSTANCE database table.
 * 
 */
@Entity
@NamedQuery(name="Docstateinstance.findAll", query="SELECT d FROM Docstateinstance d")
public class Docstateinstance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCSTATEINSTANCE_DOCSTATINSID_GENERATOR", sequenceName="DOCSTATEINSTANCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCSTATEINSTANCE_DOCSTATINSID_GENERATOR")
	@Column(name="DOCSTATINS_ID")
	private long docstatinsId;

	@Temporal(TemporalType.DATE)
	@Column(name="DOCSTATINS_ENDDATE")
	@NotNull(groups= {add.class,update.class})
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date docstatinsEnddate;

	@Temporal(TemporalType.DATE)
	@Column(name="DOCSTATINS_STARTDATE")
	@NotNull(groups= {add.class,update.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date docstatinsStartdate;

	//bi-directional many-to-one association to Documentstate
	@ManyToOne
	@JoinColumn(name="DOCSTAT_DOCSTAT_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Documentstate documentstate;

	//bi-directional many-to-one association to Documentt
	@ManyToOne
	@JoinColumn(name="DOC_DOC_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull(groups={add.class,update.class})
	private Documentt documentt;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERS_PERS_ID")
	@NotNull(groups={add.class,update.class})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Person person;

	public Docstateinstance() {
	}

	public long getDocstatinsId() {
		return this.docstatinsId;
	}

	public void setDocstatinsId(long docstatinsId) {
		this.docstatinsId = docstatinsId;
	}

	public Date getDocstatinsEnddate() {
		return this.docstatinsEnddate;
	}

	public void setDocstatinsEnddate(Date docstatinsEnddate) {
		this.docstatinsEnddate = docstatinsEnddate;
	}

	public Date getDocstatinsStartdate() {
		return this.docstatinsStartdate;
	}

	public void setDocstatinsStartdate(Date docstatinsStartdate) {
		this.docstatinsStartdate = docstatinsStartdate;
	}

	public Documentstate getDocumentstate() {
		return this.documentstate;
	}

	public void setDocumentstate(Documentstate documentstate) {
		this.documentstate = documentstate;
	}

	public Documentt getDocumentt() {
		return this.documentt;
	}

	public void setDocumentt(Documentt documentt) {
		this.documentt = documentt;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}