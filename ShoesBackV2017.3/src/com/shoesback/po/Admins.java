package com.shoesback.po;

/**
 * Admins entity. @author MyEclipse Persistence Tools
 */

public class Admins implements java.io.Serializable {

	// Fields

	private Integer aid;
	private Permission permission;
	private String acount;
	private String apwd;
	private String aremarks;

	// Constructors

	/** default constructor */
	public Admins() {
	}

	/** minimal constructor */
	public Admins(String acount, String apwd, Permission permission) {
		this.acount = acount;
		this.apwd = apwd;
		this.permission=permission;
	}

	/** full constructor */
	public Admins(String acount, String apwd, Permission permission, String aremarks) {
		this.acount = acount;
		this.apwd = apwd;
		this.permission=permission;
		this.aremarks = aremarks;
	}

	// Property accessors

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAcount() {
		return this.acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getApwd() {
		return this.apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public String getAremarks() {
		return this.aremarks;
	}

	public void setAremarks(String aremarks) {
		this.aremarks = aremarks;
	}

}