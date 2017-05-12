package com.shoesback.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class CheckLoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext ac=ActionContext.getContext();
		String result="";
		//�ж��û��Ƿ��Ѿ���½
		if(ac.getSession().get("admininfo")==null){
			result="error";//���ص�¼���ݶ���
		}else{
			result=invocation.invoke();
		}
		return result;
	}

}
