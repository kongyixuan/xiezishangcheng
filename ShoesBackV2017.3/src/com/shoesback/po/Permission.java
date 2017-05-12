package com.shoesback.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Permission implements Serializable {
    
	//Fields
	
	private Integer perid;
	private String pername;
	private String percont;
	private String peremarks;
	private Set percontents=new HashSet(0);
	private Set adminses=new HashSet(0);
	
	//constructors
	
	public Permission(){}
	public Permission(Integer perid,String pername,String percont,String peremarks,
	             Set percontents, Set adminses){
		this.perid=perid;
		this.pername=pername;
		this.percont=percont;
		this.peremarks=peremarks;
		this.percontents=percontents;
		this.adminses=adminses;
	}
	public Permission(String pername,String percont,String peremarks,
            Set percontents, Set adminses){	
	this.pername=pername;
	this.percont=percont;
	this.peremarks=peremarks;
	this.percontents=percontents;
	this.adminses=adminses;
    }
	public Integer getPerid() {
		return perid;
	}
	public void setPerid(Integer perid) {
		this.perid = perid;
	}
	public String getPername() {
		return pername;
	}
	public void setPername(String pername) {
		this.pername = pername;
	}
	public String getPercont() {
		return percont;
	}
	public void setPercont(String percont) {
		this.percont = percont;
	}
	public String getPeremarks() {
		return peremarks;
	}
	public void setPeremarks(String peremarks) {
		this.peremarks = peremarks;
	}
	public Set getPercontents() {
		return percontents;
	}
	public void setPercontents(Set percontents) {
		this.percontents = percontents;
	}
	public Set getAdminses() {
		return adminses;
	}
	public void setAdminses(Set adminses) {
		this.adminses = adminses;
	}
	
}
