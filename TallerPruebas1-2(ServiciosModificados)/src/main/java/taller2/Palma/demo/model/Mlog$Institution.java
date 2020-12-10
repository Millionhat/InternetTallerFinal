package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MLOG$_INSTITUTION database table.
 * 
 */
@Entity
@Table(name="MLOG$_INSTITUTION")
@NamedQuery(name="Mlog$Institution.findAll", query="SELECT m FROM Mlog$Institution m")
public class Mlog$Institution implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long fubcfId;

	@Column(name="CHANGE_VECTOR$$")
	private byte[] changeVector$$;

	private String dmltype$$;

	@Column(name="INST_ID")
	private BigDecimal instId;

	@Column(name="OLD_NEW$$")
	private String oldNew$$;

	@Temporal(TemporalType.DATE)
	private Date snaptime$$;

	private BigDecimal xid$$;

	public Mlog$Institution() {
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

	public String getOldNew$$() {
		return this.oldNew$$;
	}

	public void setOldNew$$(String oldNew$$) {
		this.oldNew$$ = oldNew$$;
	}

	public Date getSnaptime$$() {
		return this.snaptime$$;
	}

	public void setSnaptime$$(Date snaptime$$) {
		this.snaptime$$ = snaptime$$;
	}

	public BigDecimal getXid$$() {
		return this.xid$$;
	}

	public void setXid$$(BigDecimal xid$$) {
		this.xid$$ = xid$$;
	}

}