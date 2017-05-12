package com.shoesback.po;

/**
 * SpsSizes entity. @author MyEclipse Persistence Tools
 */

public class SpsSizes implements java.io.Serializable {

	// Fields

	private Integer spssid;
	private SpcifyShoes spcifyShoes;
	private Sizes sizes;
	private Integer spsstate;
	private String spremarks;

	// Constructors

	/** default constructor */
	public SpsSizes() {
	}

	/** minimal constructor */
	public SpsSizes(SpcifyShoes spcifyShoes, Sizes sizes, Integer spsstate) {
		this.spcifyShoes = spcifyShoes;
		this.sizes = sizes;
		this.spsstate = spsstate;
	}

	/** full constructor */
	public SpsSizes(SpcifyShoes spcifyShoes, Sizes sizes, Integer spsstate,
			String spremarks) {
		this.spcifyShoes = spcifyShoes;
		this.sizes = sizes;
		this.spsstate = spsstate;
		this.spremarks = spremarks;
	}

	// Property accessors

	public Integer getSpssid() {
		return this.spssid;
	}

	public void setSpssid(Integer spssid) {
		this.spssid = spssid;
	}

	public SpcifyShoes getSpcifyShoes() {
		return this.spcifyShoes;
	}

	public void setSpcifyShoes(SpcifyShoes spcifyShoes) {
		this.spcifyShoes = spcifyShoes;
	}

	public Sizes getSizes() {
		return this.sizes;
	}

	public void setSizes(Sizes sizes) {
		this.sizes = sizes;
	}

	public Integer getSpsstate() {
		return this.spsstate;
	}

	public void setSpsstate(Integer spsstate) {
		this.spsstate = spsstate;
	}

	public String getSpremarks() {
		return this.spremarks;
	}

	public void setSpremarks(String spremarks) {
		this.spremarks = spremarks;
	}

}