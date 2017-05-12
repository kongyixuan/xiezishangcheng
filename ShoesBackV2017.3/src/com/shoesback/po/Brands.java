package com.shoesback.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Brands entity. @author MyEclipse Persistence Tools
 */

public class Brands implements java.io.Serializable {

	// Fields

	private Integer bid;
	private String bname;
	private String bsex;
	private Integer bstate;
	private String bremarks;
	private Set spcifyShoeses = new HashSet(0);
	private Set shoeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Brands() {
	}

	/** minimal constructor */
	public Brands(String bname, String bsex, Integer bstate) {
		this.bname = bname;
		this.bsex = bsex;
		this.bstate = bstate;
	}

	/** full constructor */
	public Brands(String bname, String bsex, Integer bstate, String bremarks,
			Set spcifyShoeses, Set shoeses) {
		this.bname = bname;
		this.bsex = bsex;
		this.bstate = bstate;
		this.bremarks = bremarks;
		this.spcifyShoeses = spcifyShoeses;
		this.shoeses = shoeses;
	}

	// Property accessors

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBsex() {
		return this.bsex;
	}

	public void setBsex(String bsex) {
		this.bsex = bsex;
	}

	public Integer getBstate() {
		return this.bstate;
	}

	public void setBstate(Integer bstate) {
		this.bstate = bstate;
	}

	public String getBremarks() {
		return this.bremarks;
	}

	public void setBremarks(String bremarks) {
		this.bremarks = bremarks;
	}

	public Set getSpcifyShoeses() {
		return this.spcifyShoeses;
	}

	public void setSpcifyShoeses(Set spcifyShoeses) {
		this.spcifyShoeses = spcifyShoeses;
	}

	public Set getShoeses() {
		return this.shoeses;
	}

	public void setShoeses(Set shoeses) {
		this.shoeses = shoeses;
	}

}