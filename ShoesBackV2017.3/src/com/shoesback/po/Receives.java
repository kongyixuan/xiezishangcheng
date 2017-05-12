package com.shoesback.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Receives entity. @author MyEclipse Persistence Tools
 */

public class Receives implements java.io.Serializable {

	// Fields

	private Integer recid;
	private Users users;
	private String recprovince;
	private String reccity;
	private String recdistrict;
	private String recstreet;
	private String rectel;
	private Integer recpostcode;
	private String recmobile;
	private Integer recisdefault;
	private String recreceiver;
	private String remarks;
	private Set orderses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Receives() {
	}

	/** minimal constructor */
	public Receives(Users users, String recprovince, String reccity,
			String recdistrict, String recstreet, String recmobile,
			Integer recisdefault) {
		this.users = users;
		this.recprovince = recprovince;
		this.reccity = reccity;
		this.recdistrict = recdistrict;
		this.recstreet = recstreet;
		this.recmobile = recmobile;
		this.recisdefault = recisdefault;
	}

	/** full constructor */
	public Receives(Users users, String recprovince, String reccity,
			String recdistrict, String recstreet, String rectel,
			Integer recpostcode, String recmobile, Integer recisdefault,
			String recreceiver, String remarks, Set orderses) {
		this.users = users;
		this.recprovince = recprovince;
		this.reccity = reccity;
		this.recdistrict = recdistrict;
		this.recstreet = recstreet;
		this.rectel = rectel;
		this.recpostcode = recpostcode;
		this.recmobile = recmobile;
		this.recisdefault = recisdefault;
		this.recreceiver = recreceiver;
		this.remarks = remarks;
		this.orderses = orderses;
	}

	// Property accessors

	public Integer getRecid() {
		return this.recid;
	}

	public void setRecid(Integer recid) {
		this.recid = recid;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getRecprovince() {
		return this.recprovince;
	}

	public void setRecprovince(String recprovince) {
		this.recprovince = recprovince;
	}

	public String getReccity() {
		return this.reccity;
	}

	public void setReccity(String reccity) {
		this.reccity = reccity;
	}

	public String getRecdistrict() {
		return this.recdistrict;
	}

	public void setRecdistrict(String recdistrict) {
		this.recdistrict = recdistrict;
	}

	public String getRecstreet() {
		return this.recstreet;
	}

	public void setRecstreet(String recstreet) {
		this.recstreet = recstreet;
	}

	public String getRectel() {
		return this.rectel;
	}

	public void setRectel(String rectel) {
		this.rectel = rectel;
	}

	public Integer getRecpostcode() {
		return this.recpostcode;
	}

	public void setRecpostcode(Integer recpostcode) {
		this.recpostcode = recpostcode;
	}

	public String getRecmobile() {
		return this.recmobile;
	}

	public void setRecmobile(String recmobile) {
		this.recmobile = recmobile;
	}

	public Integer getRecisdefault() {
		return this.recisdefault;
	}

	public void setRecisdefault(Integer recisdefault) {
		this.recisdefault = recisdefault;
	}

	public String getRecreceiver() {
		return this.recreceiver;
	}

	public void setRecreceiver(String recreceiver) {
		this.recreceiver = recreceiver;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

}