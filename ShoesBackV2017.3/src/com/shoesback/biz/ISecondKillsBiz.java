package com.shoesback.biz;

import com.shoesback.po.SecondKills;
import com.shoesback.vo.PageBean;

public interface ISecondKillsBiz {
     //��ҳ��ȡ��ɱ��Ʒ
	public PageBean FindByPage(int currentpage,int pageSize);
	//����������ɱ��Ʒ
	public void CreateSecondKill(SecondKills kill);
	//��ȡ������ɱ��Ʒ����
	public SecondKills FindBySkid(int skid);
	//������ɱ��Ʒ
	public void UpdateSecondKill(SecondKills kill);
}
