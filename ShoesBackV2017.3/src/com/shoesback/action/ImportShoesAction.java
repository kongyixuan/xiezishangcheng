
package com.shoesback.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IBrandsBiz;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.biz.IShoesizesBiz;
import com.shoesback.biz.ISizesBiz;
import com.shoesback.biz.ITypesBiz;
import com.shoesback.po.Brands;
import com.shoesback.po.ImportShoes;
import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;
import com.shoesback.po.Sizes;
import com.shoesback.po.Types;
import com.shoesback.vo.ImportExcel;
/*
 * Excel���뷽����
 * ���ȣ��ϴ�excel�ļ���������·��
 * Ȼ��excelPoi��Excel�ļ���Ϣ�ֽ�ΪList���
 * ��������������ݿ�
 */
@SuppressWarnings("serial")
public class ImportShoesAction extends ActionSupport implements Preparable{
	IShoesBiz shoesBiz;
    ISizesBiz sizesBiz;
    IBrandsBiz brandsBiz;
    ITypesBiz typesBiz;
    IShoesizesBiz shoesizesBiz;   
    ActionContext ac;
    private File uploadImage;//�ϴ�ͼƬ����
    private String uploadImageContentType;//�õ��ϴ��ļ�����   
	private String uploadImageFileName;//�õ��ϴ��ļ�����
	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}

	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}

	public ISizesBiz getSizesBiz() {
		return sizesBiz;
	}

	public void setSizesBiz(ISizesBiz sizesBiz) {
		this.sizesBiz = sizesBiz;
	}

	public IBrandsBiz getBrandsBiz() {
		return brandsBiz;
	}

	public void setBrandsBiz(IBrandsBiz brandsBiz) {
		this.brandsBiz = brandsBiz;
	}

	public ITypesBiz getTypesBiz() {
		return typesBiz;
	}

	public void setTypesBiz(ITypesBiz typesBiz) {
		this.typesBiz = typesBiz;
	}

	public IShoesizesBiz getShoesizesBiz() {
		return shoesizesBiz;
	}

	public void setShoesizesBiz(IShoesizesBiz shoesizesBiz) {
		this.shoesizesBiz = shoesizesBiz;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		//�ϴ�excel�ļ�
		String realpath=ServletActionContext.getServletContext().getRealPath("/upload/excel");
		//����file����
		File file=new File(realpath);
		if(!file.exists()){
			file.mkdirs();
		}	
		try {
			FileUtils.copyFile(uploadImage, new File(file,uploadImageFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����Poiת��ExcelΪlist,����File����
        File fi=new File(realpath+"/"+uploadImageFileName);
        //����ImportExcel���󣬲�ʵ�������������캯��
        ImportExcel read=new ImportExcel(fi);
        //��ȡƷ�Ƽ���
        List<Brands> lstBrands=new ArrayList<Brands>();
        if(ac.getSession().get("lstBrands")!=null){
        	lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
        }else{
        	lstBrands=brandsBiz.FindAll();
        }
        //��ȡ���ͼ���
        List<Types> lstTypes=new ArrayList<Types>();
        if(ac.getSession().get("lstTypes")!=null){
        	lstTypes=(List<Types>) ac.getSession().get("lstTypes");
        }else{
        	lstTypes=typesBiz.FindAll();
        }
        //��ȡ�ߴ缯��
        List<Sizes> lstSizes=new ArrayList<Sizes>();
        if(ac.getSession().get("lstSizes")!=null){
        	lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
        }else{
        	lstSizes=sizesBiz.FindAll();
        }
        try {
			//��װ���ϣ�����Ь\�ߴ����,������
			Shoes shoes=null;
			Shoesizes shoesize=null;
			List<Shoes> lstShoes=new ArrayList<Shoes>();
			List<Shoesizes> lstShoesizes=new ArrayList<Shoesizes>();
			//ЬΨһ��׼�ַ���
			String shoenum="";        
			//��ȡexcel���뼯��
			List<ImportShoes> lisimp=read.ImportShoes();
			if(lisimp!=null){
				//ѭ�����������Ь����
			    for (ImportShoes importShoes : lisimp) {
			        //�ж��ֶβ�Ϊ��
			    	if(importShoes.getBrands()!=null){
			    	    //�ж��Ƿ�����Ь
			    		if(shoenum!=importShoes.getSname()){
			    			//֤������Ь
			    			shoenum=importShoes.getSname();
			    			shoes=new Shoes();
			    			//��Ь����ֵ���Ͷ���
			    			for (Types types : lstTypes) {
								if(importShoes.getTypes().equals(types.getTname())){
									shoes.setTypes(types);
								}
							}
			    			//��Ь����ֵƷ�ƶ���
			    			for (Brands brands : lstBrands) {
								if(importShoes.getBrands().equals(brands.getBname())){
									shoes.setBrands(brands);
								}
							}
			    			//���θ�ֵ��������
			    			shoes.setSnum(importShoes.getSnum());
			    			shoes.setSname(importShoes.getSname());
			    			shoes.setSprices(importShoes.getSprices());
			    			shoes.setSdiscount(importShoes.getSdiscount());
			    			shoes.setSpubtime(new Timestamp(new Date().getTime()));
			    			shoes.setSproducer(importShoes.getSproducer());
			    			shoes.setSgender(importShoes.getSgender());
			    			shoes.setScolor(importShoes.getScolor());
			    			shoes.setSinfo(importShoes.getSinfo());
			    			shoes.setStimessold(importShoes.getStimessold());
			    			shoes.setSdelete(0);
			    			shoes.setSdetail(importShoes.getSdetail());
			    			shoes.setSintegral(importShoes.getSintegral());
			    			shoes.setSremarks(importShoes.getSremarks());
			    			lstShoes.add(shoes);
			    		}
			    		shoesize=new Shoesizes();
			    		shoesize.setShoes(shoes);
			    		//��ֵ�ߴ���Ϣ
			    		for (Sizes sizes : lstSizes) {
							if(sizes.getSizenum().equals(importShoes.getSizes())){
								shoesize.setSizes(sizes);
							}
						}
			    		shoesize.setScount(importShoes.getSizenum());
			    		shoesize.setSstate(1);
			    		lstShoesizes.add(shoesize);
			    	}            		
				}            
			}
			//������ӵ����ݿ�
			if(shoesBiz.ImportShoes(lstShoes, lstShoesizes)){
				
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
	}
}