package com.shoesback.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Shoes entity. @author MyEclipse Persistence Tools
 */

public class Shoes implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Types types;
	private Brands brands;
	private String snum;
	private String sname;
	private Float sprices;
	private Integer sdiscount;
	private Timestamp spubtime;
	private String sproducer;
	private String sgender;
	private String scolor;
	private String sinfo;
	private Integer stimessold;
	private String simage;
	private String sdetail;
	private Float sintegral;
	private Integer sdelete;
	private String sremarks;
	private String sandroidimg;
	private Set shoePhotoses = new HashSet(0);
	private Set orderShoeses = new HashSet(0);
	private Set spcifyResultses = new HashSet(0);
	private Set commentses = new HashSet(0);
	private Set shoesizeses = new HashSet(0);
	private Set secondKillses = new HashSet(0);
	private Set shoesInquiryses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Shoes() {
	}

	/** minimal constructor */
	public Shoes(Types types, Brands brands, String sname, Float sprices,
			Integer sdiscount, Timestamp spubtime, String sgender,
			String scolor, Integer stimessold, Integer sdelete) {
		this.types = types;
		this.brands = brands;
		this.sname = sname;
		this.sprices = sprices;
		this.sdiscount = sdiscount;
		this.spubtime = spubtime;
		this.sgender = sgender;
		this.scolor = scolor;
		this.stimessold = stimessold;
		this.sdelete = sdelete;
	}

	/** full constructor */
	public Shoes(Types types, Brands brands, String snum, String sname,
			Float sprices, Integer sdiscount, Timestamp spubtime,
			String sproducer, String sgender, String scolor, String sinfo,
			Integer stimessold, String simage, String sdetail, Float sintegral,
			Integer sdelete, String sremarks,String sandroidimg, Set shoePhotoses,
			Set orderShoeses, Set spcifyResultses, Set commentses,
			Set shoesizeses, Set secondKillses, Set shoesInquiryses) {
		this.types = types;
		this.brands = brands;
		this.snum = snum;
		this.sname = sname;
		this.sprices = sprices;
		this.sdiscount = sdiscount;
		this.spubtime = spubtime;
		this.sproducer = sproducer;
		this.sgender = sgender;
		this.scolor = scolor;
		this.sinfo = sinfo;
		this.stimessold = stimessold;
		this.simage = simage;
		this.sdetail = sdetail;
		this.sintegral = sintegral;
		this.sdelete = sdelete;
		this.sremarks = sremarks;
		this.sandroidimg=sandroidimg;
		this.shoePhotoses = shoePhotoses;
		this.orderShoeses = orderShoeses;
		this.spcifyResultses = spcifyResultses;
		this.commentses = commentses;
		this.shoesizeses = shoesizeses;
		this.secondKillses = secondKillses;
		this.shoesInquiryses = shoesInquiryses;
	}

	// Property accessors
	public String getSandroidimg() {
		return this.sandroidimg;
	}

	public void setSandroidimg(String sandroidimg) {
		this.sandroidimg = sandroidimg;
	}
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Types getTypes() {
		return this.types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public Brands getBrands() {
		return this.brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}

	public String getSnum() {
		return this.snum;
	}

	public void setSnum(String snum) {
		this.snum = snum;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Float getSprices() {
		return this.sprices;
	}

	public void setSprices(Float sprices) {
		this.sprices = sprices;
	}

	public Integer getSdiscount() {
		return this.sdiscount;
	}

	public void setSdiscount(Integer sdiscount) {
		this.sdiscount = sdiscount;
	}

	public Timestamp getSpubtime() {
		return this.spubtime;
	}

	public void setSpubtime(Timestamp spubtime) {
		this.spubtime = spubtime;
	}

	public String getSproducer() {
		return this.sproducer;
	}

	public void setSproducer(String sproducer) {
		this.sproducer = sproducer;
	}

	public String getSgender() {
		return this.sgender;
	}

	public void setSgender(String sgender) {
		this.sgender = sgender;
	}

	public String getScolor() {
		return this.scolor;
	}

	public void setScolor(String scolor) {
		this.scolor = scolor;
	}

	public String getSinfo() {
		return this.sinfo;
	}

	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}

	public Integer getStimessold() {
		return this.stimessold;
	}

	public void setStimessold(Integer stimessold) {
		this.stimessold = stimessold;
	}

	public String getSimage() {
		return this.simage;
	}

	public void setSimage(String simage) {
		this.simage = simage;
	}

	public String getSdetail() {
		return this.sdetail;
	}

	public void setSdetail(String sdetail) {
		this.sdetail = sdetail;
	}

	public Float getSintegral() {
		return this.sintegral;
	}

	public void setSintegral(Float sintegral) {
		this.sintegral = sintegral;
	}

	public Integer getSdelete() {
		return this.sdelete;
	}

	public void setSdelete(Integer sdelete) {
		this.sdelete = sdelete;
	}

	public String getSremarks() {
		return this.sremarks;
	}

	public void setSremarks(String sremarks) {
		this.sremarks = sremarks;
	}

	public Set getShoePhotoses() {
		return this.shoePhotoses;
	}

	public void setShoePhotoses(Set shoePhotoses) {
		this.shoePhotoses = shoePhotoses;
	}

	public Set getOrderShoeses() {
		return this.orderShoeses;
	}

	public void setOrderShoeses(Set orderShoeses) {
		this.orderShoeses = orderShoeses;
	}

	public Set getSpcifyResultses() {
		return this.spcifyResultses;
	}

	public void setSpcifyResultses(Set spcifyResultses) {
		this.spcifyResultses = spcifyResultses;
	}

	public Set getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set commentses) {
		this.commentses = commentses;
	}

	public Set getShoesizeses() {
		return this.shoesizeses;
	}

	public void setShoesizeses(Set shoesizeses) {
		this.shoesizeses = shoesizeses;
	}

	public Set getSecondKillses() {
		return this.secondKillses;
	}

	public void setSecondKillses(Set secondKillses) {
		this.secondKillses = secondKillses;
	}

	public Set getShoesInquiryses() {
		return this.shoesInquiryses;
	}

	public void setShoesInquiryses(Set shoesInquiryses) {
		this.shoesInquiryses = shoesInquiryses;
	}

}