package com.shoesback.po;

import java.sql.Timestamp;

/**
 * ShoesInquirys entity. @author MyEclipse Persistence Tools
 */

public class ShoesInquirys implements java.io.Serializable {

	// Fields

	private Integer sqid;
	private Shoes shoes;
	private Users users;
	private String sqquestion;
	private String sqanswer;
	private Timestamp sqquestiontime;
	private Timestamp sqanswertime;
	private String sqremarks;

	// Constructors

	/** default constructor */
	public ShoesInquirys() {
	}

	/** minimal constructor */
	public ShoesInquirys(Shoes shoes, Users users, String sqquestion,
			String sqanswer, Timestamp sqquestiontime, Timestamp sqanswertime) {
		this.shoes = shoes;
		this.users = users;
		this.sqquestion = sqquestion;
		this.sqanswer = sqanswer;
		this.sqquestiontime = sqquestiontime;
		this.sqanswertime = sqanswertime;
	}

	/** full constructor */
	public ShoesInquirys(Shoes shoes, Users users, String sqquestion,
			String sqanswer, Timestamp sqquestiontime, Timestamp sqanswertime,
			String sqremarks) {
		this.shoes = shoes;
		this.users = users;
		this.sqquestion = sqquestion;
		this.sqanswer = sqanswer;
		this.sqquestiontime = sqquestiontime;
		this.sqanswertime = sqanswertime;
		this.sqremarks = sqremarks;
	}

	// Property accessors

	public Integer getSqid() {
		return this.sqid;
	}

	public void setSqid(Integer sqid) {
		this.sqid = sqid;
	}

	public Shoes getShoes() {
		return this.shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getSqquestion() {
		return this.sqquestion;
	}

	public void setSqquestion(String sqquestion) {
		this.sqquestion = sqquestion;
	}

	public String getSqanswer() {
		return this.sqanswer;
	}

	public void setSqanswer(String sqanswer) {
		this.sqanswer = sqanswer;
	}

	public Timestamp getSqquestiontime() {
		return this.sqquestiontime;
	}

	public void setSqquestiontime(Timestamp sqquestiontime) {
		this.sqquestiontime = sqquestiontime;
	}

	public Timestamp getSqanswertime() {
		return this.sqanswertime;
	}

	public void setSqanswertime(Timestamp sqanswertime) {
		this.sqanswertime = sqanswertime;
	}

	public String getSqremarks() {
		return this.sqremarks;
	}

	public void setSqremarks(String sqremarks) {
		this.sqremarks = sqremarks;
	}

}