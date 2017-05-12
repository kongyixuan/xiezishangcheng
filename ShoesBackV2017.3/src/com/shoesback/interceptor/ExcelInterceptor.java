package com.shoesback.interceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.po.Shoes;
import com.shoesback.vo.ExceptExcel;

@SuppressWarnings("serial")
public class ExcelInterceptor extends MethodFilterInterceptor {
	IShoesBiz shoesBiz;
	String fuzzy;
	public String getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}
	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}
	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}
	/*
	 *Excel���������������û����ص�����Ь����Ϣ֮ǰ��Ҫexcel��������Ь�Ӽ��� 
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//��ȡǰ̨ҳ�������ύ������map����
		Map<String, Object> map=arg0.getInvocationContext().getParameters();
		//���Դ�ӡ������ֵ
		Iterator<Object>  irr=map.values().iterator();
		while (irr.hasNext()) {
			System.out.println("valuesMap:"+irr.next());			
		}
		//��ȡģ����ѯ����ֵ
		String[] content=(String[]) map.get("fuzzy");
		System.out.println("ģ��������"+content);
		//����exceptexcel����
		ExceptExcel ex=new ExceptExcel();
		List<Shoes> s=shoesBiz.FuzzySearchShoe(content[0]);
		String result=null;
		//����ѯ��ȡ�ļ���ת��excel
		if(ex.ExceptShoes(s)){
			System.out.println("sizeS:"+s.size());
			result=arg0.invoke();//��תaction����
			System.out.println("resutl:"+result);
			return result;
		}else{
			return null;	
		}		
	}
}
