package com.shoesback.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String uaccount;
	private String uname;
	private String utel;
	private String ugender;
	private String upwd;
	private String uemail;
	private Timestamp uregtime;
	private Float uintegral;
	private String uinfo;
	private String upwdask;
	private String upwdans;
	private Integer udelete;
	private String uremarks;
	private Set shoesInquiryses = new HashSet(0);
	private Set receiveses = new HashSet(0);
	private Set orderses = new HashSet(0);
	private Set commentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String uaccount, String uname, String utel, String ugender,
			String upwd, String uemail, Integer udelete) {
		this.uaccount = uaccount;
		this.uname = uname;
		this.utel = utel;
		this.ugender = ugender;
		this.upwd = upwd;
		this.uemail = uemail;
		this.udelete = udelete;
	}

	/** full constructor */
	public Users(String uaccount, String uname, String utel, String ugender,
			String upwd, String uemail, Timestamp uregtime, Float uintegral,
			String uinfo, String upwdask, String upwdans, Integer udelete,
			String uremarks, Set shoesInquiryses, Set receiveses, Set orderses,
			Set commentses) {
		this.uaccount = uaccount;
		this.uname = uname;
		this.utel = utel;
		this.ugender = ugender;
		this.upwd = upwd;
		this.uemail = uemail;
		this.uregtime = uregtime;
		this.uintegral = uintegral;
		this.uinfo = uinfo;
		this.upwdask = upwdask;
		this.upwdans = upwdans;
		this.udelete = udelete;
		this.uremarks = uremarks;
		this.shoesInquiryses = shoesInquiryses;
		this.receiveses = receiveses;
		this.orderses = orderses;
		this.commentses = commentses;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUaccount() {
		return this.uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUtel() {
		return this.utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public String getUgender() {
		return this.ugender;
	}

	public void setUgender(String ugender) {
		this.ugender = ugender;
	}

	public String getUpwd() {
		return this.upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUemail() {
		return this.uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public Timestamp getUregtime() {
		return this.uregtime;
	}

	public void setUregtime(Timestamp uregtime) {
		this.uregtime = uregtime;
	}

	public Float getUintegral() {
		return this.uintegral;
	}

	public void setUintegral(Float uintegral) {
		this.uintegral = uintegral;
	}

	public String getUinfo() {
		return this.uinfo;
	}

	public void setUinfo(String uinfo) {
		this.uinfo = uinfo;
	}

	public String getUpwdask() {
		return this.upwdask;
	}

	public void setUpwdask(String upwdask) {
		this.upwdask = upwdask;
	}

	public String getUpwdans() {
		return this.upwdans;
	}

	public void setUpwdans(String upwdans) {
		this.upwdans = upwdans;
	}

	public Integer getUdelete() {
		return this.udelete;
	}

	public void setUdelete(Integer udelete) {
		this.udelete = udelete;
	}

	public String getUremarks() {
		return this.uremarks;
	}

	public void setUremarks(String uremarks) {
		this.uremarks = uremarks;
	}

	public Set getShoesInquiryses() {
		return this.shoesInquiryses;
	}

	public void setShoesInquiryses(Set shoesInquiryses) {
		this.shoesInquiryses = shoesInquiryses;
	}

	public Set getReceiveses() {
		return this.receiveses;
	}

	public void setReceiveses(Set receiveses) {
		this.receiveses = receiveses;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

	public Set getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set commentses) {
		this.commentses = commentses;
	}

}