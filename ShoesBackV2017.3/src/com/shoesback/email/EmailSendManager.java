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
   //�����ʼ�����
   private MimeMessage mimeMsg;
   //ר�����ڷ����ʼ���Session�ػ�
   private Session session;
   //��װ�ʼ����͵�һЩ������Ϣ��һ�����Զ���
   private Properties props;
   //�������û���
   private String username;
   //����������
   private String password;
   //����ʵ�ָ�����ӵ����
   private MimeMultipart mp;   
   //���Ͳ�����ʼ�������û���������г�ʼ��
   public EmailSendManager(String smtp){
	   username="";
	   password="";//
	   //�����ʼ�������
	   setSmtpHost(smtp);
	   //�����ʼ�
	   createMimeMessage();
   }
   //���÷����ʼ�����
   public void setSmtpHost(String hostName){
	   if(props==null){
		   props=System.getProperties();
	   }
	   props.put("mail.smtp.host",hostName);
   }
   //session������Ҫ֪�����������ʼ���SMTP����������
   public boolean createMimeMessage(){
	   session=Session.getDefaultInstance(props, null);
	   mimeMsg=new MimeMessage(session);
	   mp=new MimeMultipart("related");
	   return true;
   }
   //����smtp�����֤
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
   //�����û������֤ʱ���������û���������
   public void setNamePass(String name,String pass){
	   username=name;
	   password=pass;
   }
   //�����ʼ�����
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
   //�����ʼ����ݣ���������Ϊ�ı���ʽ��html�ļ���ʽ�����뷽ʽΪutf-8
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
   //��ӷ��͸���
   public boolean addFileAffix(String filename){	   
	   try {
		   BodyPart bp=new MimeBodyPart();
		   FileDataSource fileds=new FileDataSource(filename);
		   bp.setDataHandler(new DataHandler(fileds));
		   bp.setFileName("1.jpg");
		   bp.setHeader("content-id","1.jpg");
		   //��Ӹ���
		   mp.addBodyPart(bp);
		   return true;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	  
   }
   //���÷����˵�ַ
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
   //�����ռ��˵�ַ
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
   //���͸���
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
   //�����ʼ�
   public boolean sendout(){
	   try {
		mimeMsg.setContent(mp);
		mimeMsg.saveChanges();
		Session mailsession=Session.getInstance(props, null);
		Transport transport=mailsession.getTransport("smtp");
		//�����������ʼ������������������֤
		transport.connect(props.getProperty("mail.smtp.host"),username, password);
		//�����ʼ�
		transport.sendMessage(mimeMsg, mimeMsg.getRecipients(javax.mail.Message.RecipientType.TO));
		transport.close();
		return true;
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}	  
   }
   ///����freemarket���ӿ�action���ù������������ʼ����壬�ʼ����⣬�Լ��ύ���û������ʼ������ַ
   public boolean SendEmailManager(String body,String subject,String email){
	   boolean flg=true;
	   //����freemarket ������htmlҳ��  
	   EmailFreemarket obj=new EmailFreemarket();
	   try {
			obj.init();
			Map<String,String> root=new HashMap<String, String>();
			root.put("content",body);
			Template t=obj.getCfg().getTemplate("email.ftl");
			//freemarket���ɵ�htmlҳ�淢��Ӳ��λ�ã�����ʵ���������
			Writer out=new OutputStreamWriter(new FileOutputStream("F:/freemarker/hello.html"),"GBK");
			t.process(root, out);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flg=false;
		}
		//��ȡhtmlҳ��
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
		//�ʼ�����ҳ��                                                                        ���ݷ����ʼ����������͸���
		EmailSendManager themail=new EmailSendManager("smtp.163.com");
		themail.setNeedAuth(true);
		themail.setSubject(subject);
		//���ø���                       �������ڵ�·��
		themail.setBody(lin);
		 //���÷����ʼ��������ַ
		themail.setFrom("18037465800@163.com");
		 //���÷����ʼ��������ַ,����������
		themail.setNamePass("18037465800@163.com","CHUANG102008");
		long start=System.currentTimeMillis();
		themail.setTo(email);
		themail.sendout();
		long end=System.currentTimeMillis();;
		System.out.println("���͸��ʼ�ʱ�䣺"+(end-start)+"ms");
		//�ؼ�io��
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