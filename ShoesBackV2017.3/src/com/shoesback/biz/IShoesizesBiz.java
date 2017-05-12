package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;

public interface IShoesizesBiz {
    //根据鞋子信息获取对应shoesizes集合
	public List<Shoesizes> FindByShoes(Shoes shoe);
	//批量删除原先信息
    public void BatchDeleteShoesize(int sid);	
	//插入更新信息
    public void CreateShoesize(Shoesizes shoesize);
}
