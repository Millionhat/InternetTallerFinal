package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FEV_USER_CURRENT_SYMPTOMS database table.
 * 
 */
@Entity
@Table(name="FEV_USER_CURRENT_SYMPTOMS")
@NamedQuery(name="FevUserCurrentSymptom.findAll", query="SELECT f FROM FevUserCurrentSymptom f")
public class FevUserCurrentSymptom implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long fubcfId;

	@Column(name="PERS_ID")
	private BigDecimal persId;

	@Column(name="TOTAL_SYMPTOMS")
	private BigDecimal totalSymptoms;

	public FevUserCurrentSymptom() {
	}

	public BigDecimal getPersId() {
		return this.persId;
	}

	public void setPersId(BigDecimal persId) {
		this.persId = persId;
	}

	public BigDecimal getTotalSymptoms() {
		return this.totalSymptoms;
	}

	public void setTotalSymptoms(BigDecimal totalSymptoms) {
		this.totalSymptoms = totalSymptoms;
	}

}