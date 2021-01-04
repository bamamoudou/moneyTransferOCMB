package com.paymybuddy.moneytranfer.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "currency")
@EntityListeners(AuditingEntityListener.class)
public class Currency {

	@Id
	private int id;
	/**
	 * Label: 1 £ 2 $
	 */
	private String label;

	private String description;

	public Currency(int id, String label, String description) {
		super();
		this.id = id;
		this.label = label;
		this.description = description;
	}

	public Currency(String label, String description) {
		super();
		this.label = label;
		this.description = description;
	}

	public Currency() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
