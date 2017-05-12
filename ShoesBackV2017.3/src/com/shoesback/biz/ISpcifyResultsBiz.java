package com.shoesback.biz;

import com.shoesback.po.SpcifyResults;
import com.shoesback.vo.PageBean;

public interface ISpcifyResultsBiz {
    //��ҳ��ȡ����Ь������Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//���¶��ƶ���״̬
	public void UpdateSpcifyResults(int sprid,int spstate);
	//ģ���������ƶ���
	public PageBean FuzzySearchSpcifyResults(String params,int currentpage,int pageSize);
	//��ȡ�������ƶ�������
	public SpcifyResults FindBysprid(int sprid);
}
