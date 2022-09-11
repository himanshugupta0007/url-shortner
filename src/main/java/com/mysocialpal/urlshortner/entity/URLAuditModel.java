/**
 * 
 */
package com.mysocialpal.urlshortner.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.mysocialpal.urlshortner.constant.URLShortnerContants;


/**
 * This class is used to automatically add/Modify audit columns like create
 * timestamp
 * 
 * @author Himanshu.Gupta
 *
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class URLAuditModel {

	@Column(name = "CREATED_ON")
	private LocalDateTime created_on;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	@Column(name = "CREATED_BY")
	private String created_by;

	@Column(name = "UPDATED_BY")
	private String updated_by;

	/**
	 * @return the created_on
	 */
	public LocalDateTime getCreated_on() {
		return created_on;
	}

	/**
	 * @param created_on the created_on to set
	 */
	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}

	/**
	 * @return the updated_on
	 */
	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	/**
	 * @param updated_on the updated_on to set
	 */
	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	/**
	 * @return the created_by
	 */
	public String getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	/**
	 * @return the updated_by
	 */
	public String getUpdated_by() {
		return updated_by;
	}

	/**
	 * @param updated_by the updated_by to set
	 */
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	/**
	 * This method will be called during the persist operation in db.
	 */
	@PrePersist
	private void beforePersistOperation() {
		LocalDateTime timeStamp = LocalDateTime.now();
		this.setCreated_by(URLShortnerContants.LOCAL_USER.getConstant());
		this.setUpdated_by(URLShortnerContants.LOCAL_USER.getConstant());
		this.setCreated_on(timeStamp);
		this.setUpdated_on(timeStamp);
	}

	/**
	 * This method will be called when update operation is called on DB
	 */
	@PreUpdate
	private void beforeUpdateOperation() {
		this.setUpdated_on(LocalDateTime.now());
	}
}
