package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FEV_USER_CONTACTFENCE_INDEX database table.
 * 
 */
@Entity
@Table(name="FEV_USER_CONTACTFENCE_INDEX")
@NamedQuery(name="FevUserContactfenceIndex.findAll", query="SELECT f FROM FevUserContactfenceIndex f")
public class FevUserContactfenceIndex implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long fubcfId;

	@Column(name="CONTFEN_ID")
	private BigDecimal contfenId;

	@Column(name="INDEX_PERSON_ID")
	private BigDecimal indexPersonId;

	@Column(name="INDEX_STATUS")
	private String indexStatus;

	public FevUserContactfenceIndex() {
	}

	public BigDecimal getContfenId() {
		return this.contfenId;
	}

	public void setContfenId(BigDecimal contfenId) {
		this.contfenId = contfenId;
	}

	public BigDecimal getIndexPersonId() {
		return this.indexPersonId;
	}

	public void setIndexPersonId(BigDecimal indexPersonId) {
		this.indexPersonId = indexPersonId;
	}

	public String getIndexStatus() {
		return this.indexStatus;
	}

	public void setIndexStatus(String indexStatus) {
		this.indexStatus = indexStatus;
	}

}