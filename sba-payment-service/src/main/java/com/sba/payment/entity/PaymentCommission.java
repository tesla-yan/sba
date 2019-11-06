package com.sba.payment.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment_commission")
public class PaymentCommission extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "commission_percent", nullable = false)
	private Float commissionPercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(Float commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

}
