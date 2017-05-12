package com.shoesback.po;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Users users;
	private Receives receives;
	private String onum;
	private Integer ostate;
	private Timestamp ordertime;
	private Float ototal;
	private String oexpinfo;
	private Float ointegral;
	private String onote;
	private Set spcifyResultses = new HashSet(0);
	private Set orderShoeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Users users, Receives receives, String onum, Integer ostate,
			Timestamp ordertime, Float ototal, String oexpinfo, Float ointegral) {
		this.users = users;
		this.receives = receives;
		this.onum = onum;
		this.ostate = ostate;
		this.ordertime = ordertime;
		this.ototal = ototal;
		this.oexpinfo = oexpinfo;
		this.ointegral = ointegral;
	}

	/** full constructor */
	public Orders(Users users, Receives receives, String onum, Integer ostate,
			Timestamp ordertime, Float ototal, String oexpinfo,
			Float ointegral, String onote, Set spcifyResultses, Set orderShoeses) {
		this.users = users;
		this.receives = receives;
		this.onum = onum;
		this.ostate = ostate;
		this.ordertime = ordertime;
		this.ototal = ototal;
		this.oexpinfo = oexpinfo;
		this.ointegral = ointegral;
		this.onote = onote;
		this.spcifyResultses = spcifyResultses;
		this.orderShoeses = orderShoeses;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Receives getReceives() {
		return this.receives;
	}

	public void setReceives(Receives receives) {
		this.receives = receives;
	}

	public String getOnum() {
		return this.onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public Integer getOstate() {
		return this.ostate;
	}

	public void setOstate(Integer ostate) {
		this.ostate = ostate;
	}

	public Timestamp getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public Float getOtotal() {
		return this.ototal;
	}

	public void setOtotal(Float ototal) {
		this.ototal = ototal;
	}

	public String getOexpinfo() {
		return this.oexpinfo;
	}

	public void setOexpinfo(String oexpinfo) {
		this.oexpinfo = oexpinfo;
	}

	public Float getOintegral() {
		return this.ointegral;
	}

	public void setOintegral(Float ointegral) {
		this.ointegral = ointegral;
	}

	public String getOnote() {
		return this.onote;
	}

	public void setOnote(String onote) {
		this.onote = onote;
	}

	public Set getSpcifyResultses() {
		return this.spcifyResultses;
	}

	public void setSpcifyResultses(Set spcifyResultses) {
		this.spcifyResultses = spcifyResultses;
	}

	public Set getOrderShoeses() {
		return this.orderShoeses;
	}

	public void setOrderShoeses(Set orderShoeses) {
		this.orderShoeses = orderShoeses;
	}

}