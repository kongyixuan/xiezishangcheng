package com.shoesback.biz;

import com.shoesback.po.ShoesInquirys;
import com.shoesback.vo.PageBean;

public interface IInquirysBiz {
    //��ҳ��ȡ�ظ�������Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//��ȡ�ظ����ⵥ������
	public ShoesInquirys FindBysqid(int sqid);
	//�ظ�����
	public void AnswerInquirys(ShoesInquirys inquirys);
	//ģ�������ظ�����
	public PageBean FuzzySearchInquirys(String params,int currentpage,int pageSize);
}
