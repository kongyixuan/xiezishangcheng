package com.shoesback.email;

import java.io.File;

import freemarker.template.Configuration;

public class EmailFreemarket {
    private Configuration cfg;

	public Configuration getCfg() {
		return cfg;
	}
	
	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}
    public void init()throws Exception{
    	cfg=new Configuration();
    	                         //·����ָ��src��email����Ŀ¼��λ�ã���ָ�����ftlģ��λ�ã�����ʵ�ʻ����ٽ��и���
    	cfg.setDirectoryForTemplateLoading(new File("F:/freemarker"));
    } 
}
