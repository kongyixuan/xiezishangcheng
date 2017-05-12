package com.shoesback.po;

import java.sql.Timestamp;

/**
 * Ads entity. @author MyEclipse Persistence Tools
 */

public class EnterpriseCertification implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer busiid;
	//private String ext1;
	private String busiName;
	private String busiimage;
	private String busiNo;
	private String acctName;
	private String acctNo;
	private String bankNo;
	private String busiremarks;	
	/*public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}*/
	//请求报文
	/*key	string	是	API KEY
busiName	String	是	营业执照全称
busiNo	String	是	营业执照注册号
acctName	String	是	对公账户名
acctNo	String	是	对公账号
bankNo	String	是	清算联行号*/
	//响应报文
	/*ResDesc	String	认证描述
ResCode	String	认证结果（00:认证通过;98:认证未通过;01:营业执照全称格式校验不通过;02:营业执照注册号格式校验不通过;03:对公账户名格式校验不通过;04:对公账号格式校验不通过;05:清算联行号格式校验不通过）
RespData	String	验证码
BankNo	String	清算联行号
AcctNo	String	对公账号
AcctName	String	对公账户名
BusiNo	String	营业执照注册号
BusiName	String	营业执照全称*/
	// Constructors
	/** default constructor */
	public EnterpriseCertification() {
	}
	/** minimal constructor */
	public EnterpriseCertification(Integer busiid, String busiName,
			String busiimage, String busiNo, String acctName, String acctNo,
			String bankNo) {
		super();
		this.busiid = busiid;
		/*this.ext1 = ext1;*/
		this.busiName = busiName;
		this.busiimage = busiimage;
		this.busiNo = busiNo;
		this.acctName = acctName;
		this.acctNo = acctNo;
		this.bankNo = bankNo;
	}

	/** full constructor */
	public EnterpriseCertification(Integer busiid, String ext1, String busiName,
			String busiimage, String busiNo, String acctName, String acctNo,
			String bankNo, String busiremarks) {
		super();
		this.busiid = busiid;
		/*this.ext1 = ext1;*/
		this.busiName = busiName;
		this.busiimage = busiimage;
		this.busiNo = busiNo;
		this.acctName = acctName;
		this.acctNo = acctNo;
		this.bankNo = bankNo;
		this.busiremarks = busiremarks;
	}

	public Integer getBusiid() {
		return busiid;
	}



	public void setBusiid(Integer busiid) {
		this.busiid = busiid;
	}


	public String getBusiName() {
		return busiName;
	}



	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}



	public String getBusiimage() {
		return busiimage;
	}



	public void setBusiimage(String busiimage) {
		this.busiimage = busiimage;
	}



	public String getBusiNo() {
		return busiNo;
	}



	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}



	public String getAcctName() {
		return acctName;
	}



	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}



	public String getAcctNo() {
		return acctNo;
	}



	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}



	public String getBankNo() {
		return bankNo;
	}



	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}



	public String getBusiremarks() {
		return busiremarks;
	}



	public void setBusiremarks(String busiremarks) {
		this.busiremarks = busiremarks;
	}
}