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
    	                         //路径所指向：src中email包根目录下位置，所指后面的ftl模板位置，根据实际环境再进行更改
    	cfg.setDirectoryForTemplateLoading(new File("F:/freemarker"));
    } 
}
