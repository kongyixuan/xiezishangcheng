package com.shoesback.po;

import java.sql.Timestamp;

/**
 * Comments entity. @author MyEclipse Persistence Tools
 */

public class Comments implements java.io.Serializable {

	// Fields

	private Integer cid;
	private Users users;
	private Shoes shoes;
	private String sccomments;
	private Integer scscore;
	private Timestamp sctime;
	private Integer scoid;
	private String scremarks;

	// Constructors

	/** default constructor */
	public Comments() {
	}

	/** minimal constructor */
	public Comments(Users users, Shoes shoes, String sccomments,
			Integer scscore, Timestamp sctime) {
		this.users = users;
		this.shoes = shoes;
		this.sccomments = sccomments;
		this.scscore = scscore;
		this.sctime = sctime;
	}

	/** full constructor */
	public Comments(Users users, Shoes shoes, String sccomments,
			Integer scscore, Timestamp sctime, Integer scoid, String scremarks) {
		this.users = users;
		this.shoes = shoes;
		this.sccomments = sccomments;
		this.scscore = scscore;
		this.sctime = sctime;
		this.scoid = scoid;
		this.scremarks = scremarks;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Shoes getShoes() {
		return this.shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public String getSccomments() {
		return this.sccomments;
	}

	public void setSccomments(String sccomments) {
		this.sccomments = sccomments;
	}

	public Integer getScscore() {
		return this.scscore;
	}

	public void setScscore(Integer scscore) {
		this.scscore = scscore;
	}

	public Timestamp getSctime() {
		return this.sctime;
	}

	public void setSctime(Timestamp sctime) {
		this.sctime = sctime;
	}

	public Integer getScoid() {
		return this.scoid;
	}

	public void setScoid(Integer scoid) {
		this.scoid = scoid;
	}

	public String getScremarks() {
		return this.scremarks;
	}

	public void setScremarks(String scremarks) {
		this.scremarks = scremarks;
	}

}