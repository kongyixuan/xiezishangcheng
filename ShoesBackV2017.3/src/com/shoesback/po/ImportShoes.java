package com.shoesback.po;

/*
 * 导入ShoesExcel表对应实体类
 * 目的是把导入的excel文件转换为此实体类对象文件
 */
public class ImportShoes {
	private String types;
	private String brands;
	private String snum;
	private String sname;
	private Float sizes;//尺码
	private Integer sizenum;//尺码相应数量
	private Float sprices;
	private Integer sdiscount;
	private String sproducer;
	private String sgender;
	private String scolor;
	private String sinfo;
	private Integer stimessold;
	private String simage;
	private String sdetail;
	private Float sintegral;
	private String sdelete;
	private String sremarks;
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Float getSizes() {
		return sizes;
	}
	public void setSizes(Float sizes) {
		this.sizes = sizes;
	}
	public Integer getSizenum() {
		return sizenum;
	}
	public void setSizenum(Integer sizenum) {
		this.sizenum = sizenum;
	}
	public Float getSprices() {
		return sprices;
	}
	public void setSprices(Float sprices) {
		this.sprices = sprices;
	}
	public Integer getSdiscount() {
		return sdiscount;
	}
	public void setSdiscount(Integer sdiscount) {
		this.sdiscount = sdiscount;
	}
	public String getSproducer() {
		return sproducer;
	}
	public void setSproducer(String sproducer) {
		this.sproducer = sproducer;
	}
	public String getSgender() {
		return sgender;
	}
	public void setSgender(String sgender) {
		this.sgender = sgender;
	}
	public String getScolor() {
		return scolor;
	}
	public void setScolor(String scolor) {
		this.scolor = scolor;
	}
	public String getSinfo() {
		return sinfo;
	}
	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}
	public Integer getStimessold() {
		return stimessold;
	}
	public void setStimessold(Integer stimessold) {
		this.stimessold = stimessold;
	}
	public String getSimage() {
		return simage;
	}
	public void setSimage(String simage) {
		this.simage = simage;
	}
	public String getSdetail() {
		return sdetail;
	}
	public void setSdetail(String sdetail) {
		this.sdetail = sdetail;
	}
	public Float getSintegral() {
		return sintegral;
	}
	public void setSintegral(Float sintegral) {
		this.sintegral = sintegral;
	}
	public String getSdelete() {
		return sdelete;
	}
	public void setSdelete(String sdelete) {
		this.sdelete = sdelete;
	}
	public String getSremarks() {
		return sremarks;
	}
	public void setSremarks(String sremarks) {
		this.sremarks = sremarks;
	}
}
