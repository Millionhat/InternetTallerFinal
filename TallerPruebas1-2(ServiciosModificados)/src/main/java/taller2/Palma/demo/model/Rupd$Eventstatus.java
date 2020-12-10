package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RUPD$_EVENTSTATUS database table.
 * 
 */
@Entity
@Table(name="RUPD$_EVENTSTATUS")
@NamedQuery(name="Rupd$Eventstatus.findAll", query="SELECT r FROM Rupd$Eventstatus r")
public class Rupd$Eventstatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long fubcfId;

	@Column(name="CHANGE_VECTOR$$")
	private byte[] changeVector$$;

	private String dmltype$$;

	@Column(name="EVESTAT_ID")
	private BigDecimal evestatId;

	private BigDecimal snapid;

	public Rupd$Eventstatus() {
	}

	public byte[] getChangeVector$$() {
		return this.changeVector$$;
	}

	public void setChangeVector$$(byte[] changeVector$$) {
		this.changeVector$$ = changeVector$$;
	}

	public String getDmltype$$() {
		return this.dmltype$$;
	}

	public void setDmltype$$(String dmltype$$) {
		this.dmltype$$ = dmltype$$;
	}

	public BigDecimal getEvestatId() {
		return this.evestatId;
	}

	public void setEvestatId(BigDecimal evestatId) {
		this.evestatId = evestatId;
	}

	public BigDecimal getSnapid() {
		return this.snapid;
	}

	public void setSnapid(BigDecimal snapid) {
		this.snapid = snapid;
	}

}