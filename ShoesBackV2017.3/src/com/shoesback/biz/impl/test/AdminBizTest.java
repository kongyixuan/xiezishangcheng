package com.shoesback.biz.impl.test;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shoesback.biz.IEnterpriseCertificationBiz;
import com.shoesback.biz.impl.AdminsBizImpl;
import com.shoesback.po.Admins;
import com.shoesback.po.EnterpriseCertification;
import com.shoesback.po.Permission;
import com.shoesback.vo.PageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AdminBizTest {
    @Autowired
	private AdminsBizImpl adminBiz;
    @Autowired
    private IEnterpriseCertificationBiz enterpriseCertificationBiz;
    @Test
    public void testAdminLogin(){
        String asn="root";
        String pwd="root";
		//测试
		Assert.assertNotNull(adminBiz.AdminLogin(asn, pwd));
    }
	@Test
	public void testSaveAdmins() {
		Admins admin = new Admins();
		admin.setAcount("ads3");
		admin.setApwd("12345");
		Permission per = new Permission();
		per.setPerid(3);
		admin.setPermission(per);
		admin.setAremarks("广告管理员");
		adminBiz.SaveAdmins(admin);
		//测试
		Assert.assertNotNull(adminBiz.FindByaid(admin.getAid()));
	}

	@Test
	public void testDeleteAdmins() {
		adminBiz.DeleteAdmins(11);
		//测试
		Assert.assertNull(adminBiz.FindByaid(11));
	}
	@Test
	public void testEnterprise() {
		EnterpriseCertification et=new EnterpriseCertification();
		et.setAcctName("1234");
		et.setBankNo("1234");
		PageBean pb=new PageBean();
		enterpriseCertificationBiz.SaveAds(et);
		int id=et.getBusiid();
		et=enterpriseCertificationBiz.findById(et.getBusiid());
		pb=enterpriseCertificationBiz.FindByPage(1, 1);
		 List list=pb.getData();
		 for(Object li:list){
			 EnterpriseCertification eli=(EnterpriseCertification)li;
			 System.out.print(eli.getAcctName());
		 }
		System.out.print(et.getAcctName());
		
	}

}
