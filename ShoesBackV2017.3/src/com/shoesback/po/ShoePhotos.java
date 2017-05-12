package com.shoesback.po;

/**
 * ShoePhotos entity. @author MyEclipse Persistence Tools
 */

public class ShoePhotos implements java.io.Serializable {

	// Fields

	private Integer spid;
	private Shoes shoes;
	private String spurl;
	private String spremarks;

	// Constructors

	/** default constructor */
	public ShoePhotos() {
	}

	/** minimal constructor */
	public ShoePhotos(Shoes shoes, String spurl) {
		this.shoes = shoes;
		this.spurl = spurl;
	}

	/** full constructor */
	public ShoePhotos(Shoes shoes, String spurl, String spremarks) {
		this.shoes = shoes;
		this.spurl = spurl;
		this.spremarks = spremarks;
	}

	// Property accessors

	public Integer getSpid() {
		return this.spid;
	}

	public void setSpid(Integer spid) {
		this.spid = spid;
	}

	public Shoes getShoes() {
		return this.shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public String getSpurl() {
		return this.spurl;
	}

	public void setSpurl(String spurl) {
		this.spurl = spurl;
	}

	public String getSpremarks() {
		return this.spremarks;
	}

	public void setSpremarks(String spremarks) {
		this.spremarks = spremarks;
	}

}