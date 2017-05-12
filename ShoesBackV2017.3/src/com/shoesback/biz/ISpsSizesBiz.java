package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Sizes;
import com.shoesback.po.SpcifyShoes;
import com.shoesback.po.SpsSizes;

public interface ISpsSizesBiz {
    //��ȡ��Ӧ����Ь�ߴ���Ϣ,                  ����Ьid
	public List<SpsSizes> FindBySpsShoes(int spsid);
	//���¶���Ь�ߴ���Ϣ                        ����Ь����                     ���º���Ь�ߴ���Ϣ������ǰ�ߴ���Ϣ��ȫ��Ь�ߴ���Ϣ
	public boolean ModifySpsSizes(SpcifyShoes spcify,String[] chk,List<Integer> sizeid,List<Sizes> lstSpsSize);
	//�������涨��Ь�ߴ���Ϣ
	public void SaveSpsSizesList(List<SpsSizes> lstSpsSizes );
}
