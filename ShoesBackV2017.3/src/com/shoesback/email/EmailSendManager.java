package com.shoesback.email;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import freemarker.template.Template;

public class EmailSendManager {
   public EmailSendManager(){
	   
   }
   //整个邮件对象
   private MimeMessage mimeMsg;
   //专门用于发送邮件的Session回话
   private Session session;
   //封装邮件发送的一些配置信息的一个属性对象
   private Properties props;
   //发件人用户名
   private String username;
   //发件人密码
   private String password;
   //用来实现附件添加的组件
   private MimeMultipart mp;   
   //发送参数初始化，对用户名密码进行初始化
   public EmailSendManager(String smtp){
	   username="";
	   password="";//
	   //设置邮件服务器
	   setSmtpHost(smtp);
	   //创建邮件
	   createMimeMessage();
   }
   //设置发送邮件主机
   public void setSmtpHost(String hostName){
	   if(props==null){
		   props=System.getProperties();
	   }
	   props.put("mail.smtp.host",hostName);
   }
   //session对象需要知道用来处理邮件的SMTP服务器创建
   public boolean createMimeMessage(){
	   session=Session.getDefaultInstance(props, null);
	   mimeMsg=new MimeMessage(session);
	   mp=new MimeMultipart("related");
	   return true;
   }
   //设置smtp身份验证
   public void setNeedAuth(boolean need){
	   if(props==null){
		   props=System.getProperties();
	   }
	   if(need){
		   props.put("mail.smtp.auth","true");
	   }else{
		   props.put("mail.smtp.auth","false");
	   }	   
   }
   //进行用户身份验证时，设置其用户名和密码
   public void setNamePass(String name,String pass){
	   username=name;
	   password=pass;
   }
   //设置邮件主题
   public boolean setSubject(String mailsubject){
	   try {
		mimeMsg.setSubject(mailsubject);
		return true;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
   }
   //设置邮件内容，并设置其为文本格式或html文件格式，编码方式为utf-8
   public boolean setBody(String mailBody){	   
	   try {
		BodyPart bp=new MimeBodyPart();
		bp.setContent("<meta http-equiv=Content-Type content=text/html;charset=UTF-8>"+mailBody,"text/html;charset=UTF-8");
		mp.setSubType("related");
		mp.addBodyPart(bp);
		return true;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
   }   
   //添加发送附件
   public boolean addFileAffix(String filename){	   
	   try {
		   BodyPart bp=new MimeBodyPart();
		   FileDataSource fileds=new FileDataSource(filename);
		   bp.setDataHandler(new DataHandler(fileds));
		   bp.setFileName("1.jpg");
		   bp.setHeader("content-id","1.jpg");
		   //添加附件
		   mp.addBodyPart(bp);
		   return true;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	  
   }
   //设置发件人地址
   public boolean setFrom(String from){
	   try {
		mimeMsg.setFrom(new InternetAddress(from));
		return true;
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}	   
   }
   //设置收件人地址
   public boolean setTo(String to){
	   if(to==null||to==""){
		   return false;
	   }
	   try {
		mimeMsg.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(to));
		return true;
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
   }   
   //发送附件
   public boolean setCopyTo(String copyTo){
	   if(copyTo==null||copyTo==""){
		   return false;
	   }
	   try {
		mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC,InternetAddress.parse(copyTo));
		 return true;
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 return false;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 return false;
	}
   }
   //发送邮件
   public boolean sendout(){
	   try {
		mimeMsg.setContent(mp);
		mimeMsg.saveChanges();
		Session mailsession=Session.getInstance(props, null);
		Transport transport=mailsession.getTransport("smtp");
		//真正的连接邮件服务器并进行身份验证
		transport.connect(props.getProperty("mail.smtp.host"),username, password);
		//发送邮件
		transport.sendMessage(mimeMsg, mimeMsg.getRecipients(javax.mail.Message.RecipientType.TO));
		transport.close();
		return true;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}	  
   }
   ///调用freemarket，接口action调用管理方法，发送邮件主体，邮件主题，以及提交的用户发送邮件邮箱地址
   public boolean SendEmailManager(String body,String subject,String email){
	   boolean flg=true;
	   //调用freemarket ，生成html页面  
	   EmailFreemarket obj=new EmailFreemarket();
	   try {
			obj.init();
			Map<String,String> root=new HashMap<String, String>();
			root.put("content",body);
			Template t=obj.getCfg().getTemplate("email.ftl");
			//freemarket生成的html页面发送硬盘位置，根据实际情况更改
			Writer out=new OutputStreamWriter(new FileOutputStream("F:/freemarker/hello.html"),"GBK");
			t.process(root, out);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flg=false;
		}
		//读取html页面
		BufferedReader br=null;
		String line=null,lin="";
		try {
			br=new BufferedReader(new FileReader("F:/freemarker/hello.html"));
			while ((line=br.readLine())!=null) {
				lin+=line;				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flg=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flg=false;
		}
		//邮件发送页面                                                                        根据发送邮件服务器类型更改
		EmailSendManager themail=new EmailSendManager("smtp.163.com");
		themail.setNeedAuth(true);
		themail.setSubject(subject);
		//设置附件                       附件所在的路径
		themail.setBody(lin);
		 //设置发送邮件的邮箱地址
		themail.setFrom("18037465800@163.com");
		 //设置发送邮件的邮箱地址,和邮箱密码
		themail.setNamePass("18037465800@163.com","CHUANG102008");
		long start=System.currentTimeMillis();
		themail.setTo(email);
		themail.sendout();
		long end=System.currentTimeMillis();;
		System.out.println("发送该邮件时间："+(end-start)+"ms");
		//关键io流
		if(br!=null){
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flg=false;
			}
		}
		return flg;
   }  
}