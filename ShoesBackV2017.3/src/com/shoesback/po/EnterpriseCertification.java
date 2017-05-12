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
	//������
	/*key	string	��	API KEY
busiName	String	��	Ӫҵִ��ȫ��
busiNo	String	��	Ӫҵִ��ע���
acctName	String	��	�Թ��˻���
acctNo	String	��	�Թ��˺�
bankNo	String	��	�������к�*/
	//��Ӧ����
	/*ResDesc	String	��֤����
ResCode	String	��֤�����00:��֤ͨ��;98:��֤δͨ��;01:Ӫҵִ��ȫ�Ƹ�ʽУ�鲻ͨ��;02:Ӫҵִ��ע��Ÿ�ʽУ�鲻ͨ��;03:�Թ��˻�����ʽУ�鲻ͨ��;04:�Թ��˺Ÿ�ʽУ�鲻ͨ��;05:�������кŸ�ʽУ�鲻ͨ����
RespData	String	��֤��
BankNo	String	�������к�
AcctNo	String	�Թ��˺�
AcctName	String	�Թ��˻���
BusiNo	String	Ӫҵִ��ע���
BusiName	String	Ӫҵִ��ȫ��*/
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