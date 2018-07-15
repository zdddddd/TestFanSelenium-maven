package com.business;

import org.openqa.selenium.WebDriver;
import com.config.GetTestProperties;
import com.page.Kfgl_LoginPage_New;
import com.util.LogsInit;

public class Kfgl_LoginBusiness_New extends LogsInit {

	
	private WebDriver driver;
	
	public Kfgl_LoginBusiness_New(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 打开测试URL
	 * 
	 * @param driver
	 */
	public void goToUrl() {
		driver.get(GetTestProperties.getTestUrl());
	}

	/**
	 * 定义登陆业务
	 * 
	 * @param driver
	 * @param usename
	 * @param password
	 * @return
	 * @throws InterruptedException
	 */
	public void login(String username, String password)
			throws Exception {
		this.goToUrl();//打开测试网址
		
		Kfgl_LoginPage_New login_page = new Kfgl_LoginPage_New(driver);
		
		login_page.setUsername(username);//输入用户名
		login_page.setPassword(password);// 输入密码
		login_page.clickButton();//点击登录按钮
	}

	
	
	/**
	 * 默认登录-管理员
	 * 
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public boolean defaultLogin() throws Exception {

		this.goToUrl();
		
		Kfgl_LoginPage_New page = new Kfgl_LoginPage_New(driver);
		page.setUsername(GetTestProperties.getname());
		page.setPassword(GetTestProperties.getpassword());
		page.clickButton();
		
		Thread.sleep(500);
		// boolean result = page.waitUntil(driver);
		boolean result = driver.getTitle().contains("软件应用框架");
		return result;
	}







}
