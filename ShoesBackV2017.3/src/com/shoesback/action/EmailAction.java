package com.shoesback.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.email.EmailSendManager;

@SuppressWarnings("serial")
public class EmailAction extends ActionSupport implements Preparable{
	ActionContext ac;
	//���������ַ
    String mailto;
    //��������
    String subject;
    public String getMailto() {
		return mailto;
	}
	public void setMailto(String mailto) {
		this.mailto = mailto;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	//��������
    String body;
	/**
	 *����վ���ʼ�
	 */
	public String execute() {
		System.out.println("�����ʼ�");
		EmailSendManager mail=new EmailSendManager();
		if(mail.SendEmailManager(body, subject, mailto)){
			ac.put("emailLoginMSG",10);
		}else{
			ac.put("emailLoginMSG",11);
		}
		return SUCCESS;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
	}
}