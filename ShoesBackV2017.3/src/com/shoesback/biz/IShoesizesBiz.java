package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;

public interface IShoesizesBiz {
    //����Ь����Ϣ��ȡ��Ӧshoesizes����
	public List<Shoesizes> FindByShoes(Shoes shoe);
	//����ɾ��ԭ����Ϣ
    public void BatchDeleteShoesize(int sid);	
	//���������Ϣ
    public void CreateShoesize(Shoesizes shoesize);
}
