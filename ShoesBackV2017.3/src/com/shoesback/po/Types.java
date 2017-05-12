package com.shoesback.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Types entity. @author MyEclipse Persistence Tools
 */

public class Types implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String tname;
	private String tremarks;
	private Integer tdelete;
	private Set shoeses = new HashSet(0);
	private Set spcifyShoeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Types() {
	}

	/** minimal constructor */
	public Types(String tname, Integer tdelete) {
		this.tname = tname;
		this.tdelete = tdelete;
	}

	/** full constructor */
	public Types(String tname, String tremarks, Integer tdelete, Set shoeses,
			Set spcifyShoeses) {
		this.tname = tname;
		this.tremarks = tremarks;
		this.tdelete = tdelete;
		this.shoeses = shoeses;
		this.spcifyShoeses = spcifyShoeses;
	}

	// Property accessors

	public Integer getTdelete() {
		return tdelete;
	}

	public void setTdelete(Integer tdelete) {
		this.tdelete = tdelete;
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTremarks() {
		return this.tremarks;
	}

	public void setTremarks(String tremarks) {
		this.tremarks = tremarks;
	}

	public Set getShoeses() {
		return this.shoeses;
	}

	public void setShoeses(Set shoeses) {
		this.shoeses = shoeses;
	}

	public Set getSpcifyShoeses() {
		return this.spcifyShoeses;
	}

	public void setSpcifyShoeses(Set spcifyShoeses) {
		this.spcifyShoeses = spcifyShoeses;
	}

}