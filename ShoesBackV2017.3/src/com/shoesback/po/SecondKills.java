package com.shoesback.po;

import java.sql.Timestamp;

/**
 * SecondKills entity. @author MyEclipse Persistence Tools
 */

public class SecondKills implements java.io.Serializable {

	// Fields

	private Integer skid;
	private Shoes shoes;
	private Float skintegral;
	private Integer skamount;
	private Timestamp skstarttime;
	private Timestamp skduration;
	private Integer skisvalid;
	private Integer sksize;
	private String skremarks;

	// Constructors

	/** default constructor */
	public SecondKills() {
	}

	/** minimal constructor */
	public SecondKills(Shoes shoes, Float skintegral, Integer skamount,
			Timestamp skstarttime, Timestamp skduration, Integer skisvalid,
			Integer sksize) {
		this.shoes = shoes;
		this.skintegral = skintegral;
		this.skamount = skamount;
		this.skstarttime = skstarttime;
		this.skduration = skduration;
		this.skisvalid = skisvalid;
		this.sksize = sksize;
	}

	/** full constructor */
	public SecondKills(Shoes shoes, Float skintegral, Integer skamount,
			Timestamp skstarttime, Timestamp skduration, Integer skisvalid,
			Integer sksize, String skremarks) {
		this.shoes = shoes;
		this.skintegral = skintegral;
		this.skamount = skamount;
		this.skstarttime = skstarttime;
		this.skduration = skduration;
		this.skisvalid = skisvalid;
		this.sksize = sksize;
		this.skremarks = skremarks;
	}

	// Property accessors

	public Integer getSkid() {
		return this.skid;
	}

	public void setSkid(Integer skid) {
		this.skid = skid;
	}

	public Shoes getShoes() {
		return this.shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public Float getSkintegral() {
		return this.skintegral;
	}

	public void setSkintegral(Float skintegral) {
		this.skintegral = skintegral;
	}

	public Integer getSkamount() {
		return this.skamount;
	}

	public void setSkamount(Integer skamount) {
		this.skamount = skamount;
	}

	public Timestamp getSkstarttime() {
		return this.skstarttime;
	}

	public void setSkstarttime(Timestamp skstarttime) {
		this.skstarttime = skstarttime;
	}

	public Timestamp getSkduration() {
		return this.skduration;
	}

	public void setSkduration(Timestamp skduration) {
		this.skduration = skduration;
	}

	public Integer getSkisvalid() {
		return this.skisvalid;
	}

	public void setSkisvalid(Integer skisvalid) {
		this.skisvalid = skisvalid;
	}

	public Integer getSksize() {
		return this.sksize;
	}

	public void setSksize(Integer sksize) {
		this.sksize = sksize;
	}

	public String getSkremarks() {
		return this.skremarks;
	}

	public void setSkremarks(String skremarks) {
		this.skremarks = skremarks;
	}

}