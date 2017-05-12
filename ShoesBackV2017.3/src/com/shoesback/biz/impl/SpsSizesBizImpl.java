package com.shoesback.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.shoesback.biz.ISpsSizesBiz;
import com.shoesback.dao.ISpsSizesDao;
import com.shoesback.po.Sizes;
import com.shoesback.po.SpcifyShoes;
import com.shoesback.po.SpsSizes;

public class SpsSizesBizImpl implements ISpsSizesBiz {
    ISpsSizesDao spsSizesDao;
	public ISpsSizesDao getSpsSizesDao() {
		return spsSizesDao;
	}
	public void setSpsSizesDao(ISpsSizesDao spsSizesDao) {
		this.spsSizesDao = spsSizesDao;
	}
	@Override
	public List<SpsSizes> FindBySpsShoes(int spsid) {
		// TODO Auto-generated method stub
		String hql="from SpsSizes where spcifyShoes.spsid=? ";
		return spsSizesDao.findByObject(hql, new Object[]{spsid});
	}
	@Override
	public boolean ModifySpsSizes(SpcifyShoes spcify, String[] chk,
			List<Integer> sizeid, List<Sizes> lstSpsSize) {
		//��ȡ���¶���Ь�ߴ���Ϣ��ͬ�����ݣ���ȡ�����ĳߴ���Ϣ������Ӳ�������֮ɾ������
		//���������ߴ���Ϣ����
		List<Sizes> lsit=new ArrayList<Sizes>();
		//����ɾ��ԭ�еĶ���Ь�ߴ���Ϣ
		List<Sizes> lover=new ArrayList<Sizes>();
		//�������μ��ϣ������������Ь��Ϣ
		List<Integer> sizenow=new ArrayList<Integer>();
		List<Integer> sizeover=new ArrayList<Integer>();
		List<Integer> sizenow1=new ArrayList<Integer>();
		//ѭ��������������ɾ��ԭ�ж���Ь�ߴ���Ϣ
		for (int i = 0; i < chk.length; i++) {
			//�ж��Ƿ��������ߴ���Ϣ
			if(!sizeid.contains(Integer.parseInt(chk[i]))){
				//֤������ѡ�е�һ������Ь�ߴ���Ϣ
				sizeover.add(Integer.parseInt(chk[i]));
			}
			if(sizeid.contains(Integer.parseInt(chk[i]))){
				sizenow.add(Integer.parseInt(chk[i]));
			}
		}
		for (Integer integer : sizeid) {
			if(!sizenow.contains(integer)){
				sizenow1.add(integer);
			}
		}
		for (Integer integer : sizenow1) {
			//ɾ��ԭ�гߴ���Ϣ
			for (Sizes sizes : lstSpsSize) {
				if(sizes.getSizeid().equals(integer)){
					lover.add(sizes);
					String hql="delete from SpsSizes where spcifyShoes=? and sizes=?";
					spsSizesDao.bulkUpdate(hql, new Object[]{spcify,sizes});
				}				
			}
		}
		SpsSizes sps=null;
		for (Integer integer : sizeover) {
			//����³ߴ���Ϣ
			for (Sizes sizes: lstSpsSize) {
				if(sizes.getSizeid().equals(integer)){
					lsit.add(sizes);
					sps=new SpsSizes();
					sps.setSizes(sizes);
					sps.setSpcifyShoes(spcify);
					sps.setSpsstate(1);
					spsSizesDao.create(sps);
				}
			}
		}
		return true;
	}
	@Override
	public void SaveSpsSizesList(List<SpsSizes> lstSpsSizes) {
		// TODO Auto-generated method stub
		for (SpsSizes spsSizes : lstSpsSizes) {
			spsSizesDao.create(spsSizes);
		}
	}

}
