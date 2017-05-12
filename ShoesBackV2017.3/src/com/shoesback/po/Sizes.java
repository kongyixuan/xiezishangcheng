package com.shoesback.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Sizes entity. @author MyEclipse Persistence Tools
 */

public class Sizes implements java.io.Serializable {

	// Fields

	private Integer sizeid;
	private Float sizenum;
	private String sremarks;
	private Set shoesizeses = new HashSet(0);
	private Set spsSizeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Sizes() {
	}

	/** minimal constructor */
	public Sizes(Float sizenum) {
		this.sizenum = sizenum;
	}

	/** full constructor */
	public Sizes(Float sizenum, String sremarks, Set shoesizeses, Set spsSizeses) {
		this.sizenum = sizenum;
		this.sremarks = sremarks;
		this.shoesizeses = shoesizeses;
		this.spsSizeses = spsSizeses;
	}

	// Property accessors

	public Integer getSizeid() {
		return this.sizeid;
	}

	public void setSizeid(Integer sizeid) {
		this.sizeid = sizeid;
	}

	public Float getSizenum() {
		return this.sizenum;
	}

	public void setSizenum(Float sizenum) {
		this.sizenum = sizenum;
	}

	public String getSremarks() {
		return this.sremarks;
	}

	public void setSremarks(String sremarks) {
		this.sremarks = sremarks;
	}

	public Set getShoesizeses() {
		return this.shoesizeses;
	}

	public void setShoesizeses(Set shoesizeses) {
		this.shoesizeses = shoesizeses;
	}

	public Set getSpsSizeses() {
		return this.spsSizeses;
	}

	public void setSpsSizeses(Set spsSizeses) {
		this.spsSizeses = spsSizeses;
	}

}