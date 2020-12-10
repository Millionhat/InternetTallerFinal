package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RUPD$_INSTITUTION database table.
 * 
 */
@Entity
@Table(name="RUPD$_INSTITUTION")
@NamedQuery(name="Rupd$Institution.findAll", query="SELECT r FROM Rupd$Institution r")
public class Rupd$Institution implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long fubcfId;

	@Column(name="CHANGE_VECTOR$$")
	private byte[] changeVector$$;

	private String dmltype$$;

	@Column(name="INST_ID")
	private BigDecimal instId;

	private BigDecimal snapid;

	public Rupd$Institution() {
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

	public BigDecimal getInstId() {
		return this.instId;
	}

	public void setInstId(BigDecimal instId) {
		this.instId = instId;
	}

	public BigDecimal getSnapid() {
		return this.snapid;
	}

	public void setSnapid(BigDecimal snapid) {
		this.snapid = snapid;
	}

}