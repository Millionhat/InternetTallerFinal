package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MLOG$_EVENTSTATUS database table.
 * 
 */
@Entity
@Table(name="MLOG$_EVENTSTATUS")
@NamedQuery(name="Mlog$Eventstatus.findAll", query="SELECT m FROM Mlog$Eventstatus m")
public class Mlog$Eventstatus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long fubcfId;

	@Column(name="CHANGE_VECTOR$$")
	private byte[] changeVector$$;

	private String dmltype$$;

	@Column(name="EVESTAT_ID")
	private BigDecimal evestatId;

	@Column(name="OLD_NEW$$")
	private String oldNew$$;

	@Temporal(TemporalType.DATE)
	private Date snaptime$$;

	private BigDecimal xid$$;

	public Mlog$Eventstatus() {
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