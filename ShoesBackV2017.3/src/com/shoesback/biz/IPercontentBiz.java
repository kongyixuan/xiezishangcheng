package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Percontent;
import com.shoesback.po.Permission;

public interface IPercontentBiz {
    //��ȡ����Աȫ��items
	public List<Percontent> FindByManager();
	//����percontent
	public void SavePercontent(Percontent percontent);
	//��ȡ����Ȩ����Ϣ��Ӧ��percontent����
	public List<Percontent> FindByperid(int perid);
	//��ɾ��ԭ�к����������Ȩ��items
	public void UpdatePercontent(Permission permission,List<String> nowper,List<String> overper);
}
