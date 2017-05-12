package com.shoesback.po;

/**
 * FriendLinks entity. @author MyEclipse Persistence Tools
 */

public class FriendLinks implements java.io.Serializable {

	// Fields

	private Integer flid;
	private String flname;
	private String flurl;
	private String flimage;
	private String flremarks;

	// Constructors

	/** default constructor */
	public FriendLinks() {
	}

	/** minimal constructor */
	public FriendLinks(String flname, String flurl, String flimage) {
		this.flname = flname;
		this.flurl = flurl;
		this.flimage = flimage;
	}

	/** full constructor */
	public FriendLinks(String flname, String flurl, String flimage,
			String flremarks) {
		this.flname = flname;
		this.flurl = flurl;
		this.flimage = flimage;
		this.flremarks = flremarks;
	}

	// Property accessors

	public Integer getFlid() {
		return this.flid;
	}

	public void setFlid(Integer flid) {
		this.flid = flid;
	}

	public String getFlname() {
		return this.flname;
	}

	public void setFlname(String flname) {
		this.flname = flname;
	}

	public String getFlurl() {
		return this.flurl;
	}

	public void setFlurl(String flurl) {
		this.flurl = flurl;
	}

	public String getFlimage() {
		return this.flimage;
	}

	public void setFlimage(String flimage) {
		this.flimage = flimage;
	}

	public String getFlremarks() {
		return this.flremarks;
	}

	public void setFlremarks(String flremarks) {
		this.flremarks = flremarks;
	}

}