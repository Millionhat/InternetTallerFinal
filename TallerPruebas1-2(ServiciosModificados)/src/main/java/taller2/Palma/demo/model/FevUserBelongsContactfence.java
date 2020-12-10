package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FEV_USER_BELONGS_CONTACTFENCE database table.
 * 
 */
@Entity
@Table(name="FEV_USER_BELONGS_CONTACTFENCE")
@NamedQuery(name="FevUserBelongsContactfence.findAll", query="SELECT f FROM FevUserBelongsContactfence f")
public class FevUserBelongsContactfence implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long fubcfId;

	@Column(name="CONTFEN_ID")
	private BigDecimal contfenId;

	@Column(name="PERS_PERS_ID")
	private BigDecimal persPersId;

	public FevUserBelongsContactfence() {
	}
	
	public long getFUBCFID() {
		return fubcfId;
	}
	
	public void setFUBCFID(long id) {
		fubcfId=id;
	}

	public BigDecimal getContfenId() {
		return this.contfenId;
	}

	public void setContfenId(BigDecimal contfenId) {
		this.contfenId = contfenId;
	}

	public BigDecimal getPersPersId() {
		return this.persPersId;
	}

	public void setPersPersId(BigDecimal persPersId) {
		this.persPersId = persPersId;
	}

}