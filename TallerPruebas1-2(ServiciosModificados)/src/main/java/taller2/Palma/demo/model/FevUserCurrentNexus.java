package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FEV_USER_CURRENT_NEXUS database table.
 * 
 */
@Entity
@Table(name="FEV_USER_CURRENT_NEXUS")
@NamedQuery(name="FevUserCurrentNexus.findAll", query="SELECT f FROM FevUserCurrentNexus f")
public class FevUserCurrentNexus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long fubcfId;

	@Column(name="MAX_NEXUS")
	private BigDecimal maxNexus;

	@Column(name="PERS_ID")
	private BigDecimal persId;

	public FevUserCurrentNexus() {
	}

	public BigDecimal getMaxNexus() {
		return this.maxNexus;
	}

	public void setMaxNexus(BigDecimal maxNexus) {
		this.maxNexus = maxNexus;
	}

	public BigDecimal getPersId() {
		return this.persId;
	}

	public void setPersId(BigDecimal persId) {
		this.persId = persId;
	}

}