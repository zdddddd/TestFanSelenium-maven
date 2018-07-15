package com.cases;

import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.business.Kfgl_LoginBusiness_New;


public class KfglTest_New extends TestBase {

	

	// 用户成功登陆
	@Test()
	public void kfgl_0001_LoginSucc(Method method) throws Exception {
		String methodName = "";
		methodName = method.getName();
		log.info(methodName + " is running !");
		
		
		
		Kfgl_LoginBusiness_New kfgl_lb = new Kfgl_LoginBusiness_New(driver);
		kfgl_lb.login("admin", "admin");// 调用登录的一个事务
		// kfgl_lb.defaultLogin();

		Thread.sleep(1000);
		Assert.assertTrue(driver.getTitle().contains("软件应用框架"));// 验证点
		
		captureflag = false;// 断言执行成功才执行此行代码，否则跳过

	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

	}

}
