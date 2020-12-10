package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RUPD$_EPIDEMEVENT database table.
 * 
 */
@Entity
@Table(name="RUPD$_EPIDEMEVENT")
@NamedQuery(name="Rupd$Epidemevent.findAll", query="SELECT r FROM Rupd$Epidemevent r")
public class Rupd$Epidemevent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long fubcfId;

	@Column(name="CHANGE_VECTOR$$")
	private byte[] changeVector$$;

	private String dmltype$$;

	@Column(name="EPIEVE_ID")
	private BigDecimal epieveId;

	private BigDecimal snapid;

	public Rupd$Epidemevent() {
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

	public BigDecimal getEpieveId() {
		return this.epieveId;
	}

	public void setEpieveId(BigDecimal epieveId) {
		this.epieveId = epieveId;
	}

	public BigDecimal getSnapid() {
		return this.snapid;
	}

	public void setSnapid(BigDecimal snapid) {
		this.snapid = snapid;
	}

}