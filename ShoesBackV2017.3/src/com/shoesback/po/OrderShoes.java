package com.shoesback.po;

/**
 * OrderShoes entity. @author MyEclipse Persistence Tools
 */

public class OrderShoes implements java.io.Serializable {

	// Fields

	private Integer osid;
	private Orders orders;
	private Shoes shoes;
	private Float ossize;
	private Integer osstate;
	private Integer osnum;
	private String oremarks;

	// Constructors

	/** default constructor */
	public OrderShoes() {
	}

	/** minimal constructor */
	public OrderShoes(Orders orders, Shoes shoes, Float ossize,
			Integer osstate, Integer osnum) {
		this.orders = orders;
		this.shoes = shoes;
		this.ossize = ossize;
		this.osstate = osstate;
		this.osnum = osnum;
	}

	/** full constructor */
	public OrderShoes(Orders orders, Shoes shoes, Float ossize,
			Integer osstate, Integer osnum, String oremarks) {
		this.orders = orders;
		this.shoes = shoes;
		this.ossize = ossize;
		this.osstate = osstate;
		this.osnum = osnum;
		this.oremarks = oremarks;
	}

	// Property accessors

	public Integer getOsid() {
		return this.osid;
	}

	public void setOsid(Integer osid) {
		this.osid = osid;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Shoes getShoes() {
		return this.shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public Float getOssize() {
		return this.ossize;
	}

	public void setOssize(Float ossize) {
		this.ossize = ossize;
	}

	public Integer getOsstate() {
		return this.osstate;
	}

	public void setOsstate(Integer osstate) {
		this.osstate = osstate;
	}

	public Integer getOsnum() {
		return this.osnum;
	}

	public void setOsnum(Integer osnum) {
		this.osnum = osnum;
	}

	public String getOremarks() {
		return this.oremarks;
	}

	public void setOremarks(String oremarks) {
		this.oremarks = oremarks;
	}

}