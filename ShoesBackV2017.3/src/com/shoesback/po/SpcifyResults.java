package com.shoesback.po;

/**
 * SpcifyResults entity. @author MyEclipse Persistence Tools
 */

public class SpcifyResults implements java.io.Serializable {

	// Fields

	private Integer sprid;
	private Orders orders;
	private SpcifyShoes spcifyShoes;
//	private Shoes shoes;
	private String sprscheme;
	private Float sprsize;
	private Integer sprnum;
	private String spremarks;
    private Integer spstate; 

	/** default constructor */
	public SpcifyResults() {
	}

	/** minimal constructor */
	public SpcifyResults(SpcifyShoes spcifyShoes, Orders orders,
			String sprscheme, Float sprsize, Integer sprnum,Integer spstate) {
		this.spcifyShoes = spcifyShoes;
		this.orders = orders;
		this.sprscheme = sprscheme;
		this.sprsize = sprsize;
		this.sprnum = sprnum;
		this.spstate=spstate;
	}

	/** full constructor */
	public SpcifyResults(SpcifyShoes spcifyShoes, Orders orders,
			String sprscheme, Float sprsize, Integer sprnum, String spremarks,Integer spstate) {
		this.spcifyShoes = spcifyShoes;
		this.orders = orders;
		this.sprscheme = sprscheme;
		this.sprsize = sprsize;
		this.sprnum = sprnum;
		this.spremarks = spremarks;
		this.spstate=spstate;
	}

	// Property accessors

	public Integer getSprid() {
		return this.sprid;
	}

	public void setSprid(Integer sprid) {
		this.sprid = sprid;
	}

	public SpcifyShoes getSpcifyShoes() {
		return this.spcifyShoes;
	}

	public void setSpcifyShoes(SpcifyShoes spcifyShoes) {
		this.spcifyShoes = spcifyShoes;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getSprscheme() {
		return this.sprscheme;
	}

	public void setSprscheme(String sprscheme) {
		this.sprscheme = sprscheme;
	}

	public Float getSprsize() {
		return this.sprsize;
	}

	public void setSprsize(Float sprsize) {
		this.sprsize = sprsize;
	}

	public Integer getSprnum() {
		return this.sprnum;
	}

	public void setSprnum(Integer sprnum) {
		this.sprnum = sprnum;
	}

	public String getSpremarks() {
		return this.spremarks;
	}

	public void setSpremarks(String spremarks) {
		this.spremarks = spremarks;
	}

	public Integer getSpstate() {
		return spstate;
	}

	public void setSpstate(Integer spstate) {
		this.spstate = spstate;
	}
}