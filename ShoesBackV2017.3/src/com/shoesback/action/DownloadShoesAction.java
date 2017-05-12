package com.shoesback.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shoesback.biz.IShoesBiz;

@SuppressWarnings("serial")
public class DownloadShoesAction extends ActionSupport {
	IShoesBiz shoesBiz;
	private String inputPath;
	private String fuzzy;
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	public String getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}
	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}
	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}	
	/**
	 *获取下载文件方法
	 */
	public InputStream getInputStream() throws Exception{
	    System.out.println("---------"+ServletActionContext.getServletContext().getResourceAsStream(inputPath));
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	
	public String execute() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
}