package com.sba.training.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sba.security.model.Constants;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name = "trainings")
public class Training extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status", nullable = false)
	private String status = Constants.TrainingStatus.PROPOSED.getStatus();

	@Column(name = "progress", nullable = false)
	private Integer progress = 0;

	@Column(name = "fees", nullable = false)
	private Float fees = 0.0f;

	@Column(name = "commission_amount", nullable = false)
	private Float commissionAmount = 0.0f;

	@Column(name = "rating", nullable = false)
	private Integer rating = 0;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date", nullable = false)
	private String startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date", nullable = false)
	private String endDate;

	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "start_time", nullable = false)
	private String startTime;

	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "end_time", nullable = false)
	private String endTime;

	@Column(name = "amount_received", nullable = false)
	private Float amountReceived = 0.0f;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "mentor_id", nullable = false)
	private Long mentorId;

	@Column(name = "skill_id", nullable = false)
	private Long skillId;

	@Column(name = "razorpay_payment_id", nullable = false)
	private String razorpayPaymentId;

	@Column(name = "persistent", nullable = false)
	private Boolean persistent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Float getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(Float amountReceived) {
		this.amountReceived = amountReceived;
	}

	public Float getFees() {
		return fees;
	}

	public void setFees(Float fees) {
		this.fees = fees;
	}

	public Float getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Float commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMentorId() {
		return mentorId;
	}

	public void setMentorId(Long mentorId) {
		this.mentorId = mentorId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
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
