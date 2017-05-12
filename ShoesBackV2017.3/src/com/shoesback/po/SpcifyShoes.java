package com.shoesback.po;

import java.util.HashSet;
import java.util.Set;

/**
 * SpcifyShoes entity. @author MyEclipse Persistence Tools
 */

public class SpcifyShoes implements java.io.Serializable {

	// Fields

	private Integer spsid;
	private Brands brands;
	private Types types;
	private String spsseq;
	private String spsname;
	private Float spsprices;
	private String spscontent;
	private Integer spspartnum;
	private String spspartinfo;
	private String spsgender;
	private Integer spscss;
	private String spremarks;
	private Set spcifyResultses = new HashSet(0);
	private Set spsSizeses = new HashSet(0);
	


	// Constructors

	/** default constructor */
	public SpcifyShoes() {
	}

	/** minimal constructor */
	public SpcifyShoes(Brands brands, Types types, String spsseq,
			String spsname, Float spsprices, Integer spspartnum,
			String spspartinfo, String spsgender) {
		this.brands = brands;
		this.types = types;
		this.spsseq = spsseq;
		this.spsname = spsname;
		this.spsprices = spsprices;
		this.spspartnum = spspartnum;
		this.spspartinfo = spspartinfo;
		this.spsgender = spsgender;
	}

	/** full constructor */
	public SpcifyShoes(Brands brands, Types types, String spsseq,
			String spsname, Float spsprices, String spscontent,
			Integer spspartnum, String spspartinfo, String spsgender,
			Integer spscss, String spremarks, Set spcifyResultses,
			Set spsSizeses) {
		this.brands = brands;
		this.types = types;
		this.spsseq = spsseq;
		this.spsname = spsname;
		this.spsprices = spsprices;
		this.spscontent = spscontent;
		this.spspartnum = spspartnum;
		this.spspartinfo = spspartinfo;
		this.spsgender = spsgender;
		this.spscss = spscss;
		this.spremarks = spremarks;
		this.spcifyResultses = spcifyResultses;
		this.spsSizeses = spsSizeses;
	}

	// Property accessors

	public Integer getSpsid() {
		return this.spsid;
	}

	public void setSpsid(Integer spsid) {
		this.spsid = spsid;
	}

	public Brands getBrands() {
		return this.brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}

	public Types getTypes() {
		return this.types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public String getSpsseq() {
		return this.spsseq;
	}

	public void setSpsseq(String spsseq) {
		this.spsseq = spsseq;
	}

	public String getSpsname() {
		return this.spsname;
	}

	public void setSpsname(String spsname) {
		this.spsname = spsname;
	}

	public Float getSpsprices() {
		return this.spsprices;
	}

	public void setSpsprices(Float spsprices) {
		this.spsprices = spsprices;
	}

	public String getSpscontent() {
		return this.spscontent;
	}

	public void setSpscontent(String spscontent) {
		this.spscontent = spscontent;
	}

	public Integer getSpspartnum() {
		return this.spspartnum;
	}

	public void setSpspartnum(Integer spspartnum) {
		this.spspartnum = spspartnum;
	}

	public String getSpspartinfo() {
		return this.spspartinfo;
	}

	public void setSpspartinfo(String spspartinfo) {
		this.spspartinfo = spspartinfo;
	}

	public String getSpsgender() {
		return this.spsgender;
	}

	public void setSpsgender(String spsgender) {
		this.spsgender = spsgender;
	}

	public Integer getSpscss() {
		return this.spscss;
	}

	public void setSpscss(Integer spscss) {
		this.spscss = spscss;
	}

	public String getSpremarks() {
		return this.spremarks;
	}

	public void setSpremarks(String spremarks) {
		this.spremarks = spremarks;
	}

	public Set getSpcifyResultses() {
		return this.spcifyResultses;
	}

	public void setSpcifyResultses(Set spcifyResultses) {
		this.spcifyResultses = spcifyResultses;
	}

	public Set getSpsSizeses() {
		return this.spsSizeses;
	}

	public void setSpsSizeses(Set spsSizeses) {
		this.spsSizeses = spsSizeses;
	}

}