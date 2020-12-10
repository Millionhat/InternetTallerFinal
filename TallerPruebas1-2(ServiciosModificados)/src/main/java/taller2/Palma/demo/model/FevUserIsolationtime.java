package taller2.Palma.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FEV_USER_ISOLATIONTIME database table.
 * 
 */
@Entity
@Table(name="FEV_USER_ISOLATIONTIME")
@NamedQuery(name="FevUserIsolationtime.findAll", query="SELECT f FROM FevUserIsolationtime f")
public class FevUserIsolationtime implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long fubcfId;

	private BigDecimal isolationtime;

	@Column(name="PERS_ID")
	private BigDecimal persId;

	private String status;

	public FevUserIsolationtime() {
	}

	public BigDecimal getIsolationtime() {
		return this.isolationtime;
	}

	public void setIsolationtime(BigDecimal isolationtime) {
		this.isolationtime = isolationtime;
	}

	public BigDecimal getPersId() {
		return this.persId;
	}

	public void setPersId(BigDecimal persId) {
		this.persId = persId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}