package com.shoesback.po;

import java.sql.Timestamp;

/**
 * Ads entity. @author MyEclipse Persistence Tools
 */

public class Ads implements java.io.Serializable {

	// Fields

	private Integer adid;
	private String adcompany;
	private String adimage;
	private String adlink;
	private Timestamp adtime;
	private Float adincome;
	private String adremarks;

	// Constructors

	/** default constructor */
	public Ads() {
	}

	/** minimal constructor */
	public Ads(String adcompany, String adimage, String adlink,
			Timestamp adtime, Float adincome) {
		this.adcompany = adcompany;
		this.adimage = adimage;
		this.adlink = adlink;
		this.adtime = adtime;
		this.adincome = adincome;
	}

	/** full constructor */
	public Ads(String adcompany, String adimage, String adlink,
			Timestamp adtime, Float adincome, String adremarks) {
		this.adcompany = adcompany;
		this.adimage = adimage;
		this.adlink = adlink;
		this.adtime = adtime;
		this.adincome = adincome;
		this.adremarks = adremarks;
	}

	// Property accessors

	public Integer getAdid() {
		return this.adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public String getAdcompany() {
		return this.adcompany;
	}

	public void setAdcompany(String adcompany) {
		this.adcompany = adcompany;
	}

	public String getAdimage() {
		return this.adimage;
	}

	public void setAdimage(String adimage) {
		this.adimage = adimage;
	}

	public String getAdlink() {
		return this.adlink;
	}

	public void setAdlink(String adlink) {
		this.adlink = adlink;
	}

	public Timestamp getAdtime() {
		return this.adtime;
	}

	public void setAdtime(Timestamp adtime) {
		this.adtime = adtime;
	}

	public Float getAdincome() {
		return this.adincome;
	}

	public void setAdincome(Float adincome) {
		this.adincome = adincome;
	}

	public String getAdremarks() {
		return this.adremarks;
	}

	public void setAdremarks(String adremarks) {
		this.adremarks = adremarks;
	}

}