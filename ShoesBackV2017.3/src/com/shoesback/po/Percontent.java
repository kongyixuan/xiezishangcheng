package com.shoesback.po;

import java.io.Serializable;

public class Percontent implements Serializable,Comparable<Percontent>{

	//fields
	
	private Integer pcid;
	private Permission permission;
	private String pcitems;
	private String pcmenu;
	private String pcurl;
	private String pctnt;
	private String pcremarks;
	
	public Percontent(){}
	public Percontent(Integer pcid,Permission permission,String pcitems,String pcmenu,
	               String pcurl,String pctnt,String pcremarks){
		this.pcid=pcid;
		this.permission=permission;
		this.pcitems=pcitems;
		this.pcmenu=pcmenu;
		this.pcurl=pcurl;
		this.pctnt=pctnt;
		this.pcremarks=pcremarks;
	}
	public Percontent(Permission permission,String pcitems,String pcmenu,
            String pcurl,String pctnt,String pcremarks){	
	this.permission=permission;
	this.pcitems=pcitems;
	this.pcmenu=pcmenu;
	this.pcurl=pcurl;
	this.pctnt=pctnt;
	this.pcremarks=pcremarks;
}
	public Integer getPcid() {
		return pcid;
	}
	public void setPcid(Integer pcid) {
		this.pcid = pcid;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public String getPcitems() {
		return pcitems;
	}
	public void setPcitems(String pcitems) {
		this.pcitems = pcitems;
	}
	public String getPcmenu() {
		return pcmenu;
	}
	public void setPcmenu(String pcmenu) {
		this.pcmenu = pcmenu;
	}
	public String getPcurl() {
		return pcurl;
	}
	public void setPcurl(String pcurl) {
		this.pcurl = pcurl;
	}
	public String getPctnt() {
		return pctnt;
	}
	public void setPctnt(String pctnt) {
		this.pctnt = pctnt;
	}
	public String getPcremarks() {
		return pcremarks;
	}
	public void setPcremarks(String pcremarks) {
		this.pcremarks = pcremarks;
	}
	@Override
	public int compareTo(Percontent o) {
		// TODO Auto-generated method stub
		int num=this.pcitems.compareTo(o.pcitems);
		return num;
	}	
}
