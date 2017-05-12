package com.shoesback.po;

/**
 * Shoesizes entity. @author MyEclipse Persistence Tools
 */

public class Shoesizes implements java.io.Serializable {

	// Fields

	private Integer ssid;
	private Shoes shoes;
	private Sizes sizes;
	private Integer sstate;
	private String ssremarks;
	private Integer scount;

	// Constructors

	/** default constructor */
	public Shoesizes() {
	}

	/** minimal constructor */
	public Shoesizes(Shoes shoes, Sizes sizes, Integer sstate, Integer scount) {
		this.shoes = shoes;
		this.sizes = sizes;
		this.sstate = sstate;
		this.scount = scount;
	}

	/** full constructor */
	public Shoesizes(Shoes shoes, Sizes sizes, Integer sstate,
			String ssremarks, Integer scount) {
		this.shoes = shoes;
		this.sizes = sizes;
		this.sstate = sstate;
		this.ssremarks = ssremarks;
		this.scount = scount;
	}

	// Property accessors

	public Integer getSsid() {
		return this.ssid;
	}

	public void setSsid(Integer ssid) {
		this.ssid = ssid;
	}

	public Shoes getShoes() {
		return this.shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public Sizes getSizes() {
		return this.sizes;
	}

	public void setSizes(Sizes sizes) {
		this.sizes = sizes;
	}

	public Integer getSstate() {
		return this.sstate;
	}

	public void setSstate(Integer sstate) {
		this.sstate = sstate;
	}

	public String getSsremarks() {
		return this.ssremarks;
	}

	public void setSsremarks(String ssremarks) {
		this.ssremarks = ssremarks;
	}

	public Integer getScount() {
		return this.scount;
	}

	public void setScount(Integer scount) {
		this.scount = scount;
	}
}