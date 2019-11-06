package com.sba.payment.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "txn_type", nullable = false)
	private String txnType = "CR";

	@Column(name = "razorpay_payment_id", nullable = false)
	private String razorpayPaymentId;

	@Column(name = "amount", nullable = false)
	private Float amount;

	@Column(name = "remarks", nullable = false)
	private String remarks;

	@Column(name = "mentor_id", nullable = false)
	private Long mentorId;

	@Column(name = "training_id", nullable = false)
	private Long trainingId;

	@Column(name = "persistent", nullable = false)
	private Boolean persistent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getMentorId() {
		return mentorId;
	}

	public void setMentorId(Long mentorId) {
		this.mentorId = mentorId;
	}

	public Long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public Boolean getPersistent() {
		return persistent;
	}

	public void setPersistent(Boolean persistent) {
		this.persistent = persistent;
	}
}
