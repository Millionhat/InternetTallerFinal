package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;


/**
 * The persistent class for the USERR database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Userr.findAll", query="SELECT u FROM Userr u")
public class Userr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERR_USERID_GENERATOR", sequenceName="USERR_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERR_USERID_GENERATOR")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="INST_INST_ID")
	private Long instInstId;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERS_PERS_ID")
	private Person person;
	
	private UserrType type;

	public Userr() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(Long instInstId) {
		this.instInstId = instInstId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public UserrType getType() {
		return type;
	}
	
	public void setType(UserrType type) {
		this.type=type;
	}

}