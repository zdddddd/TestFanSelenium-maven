package com.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.config.GetTestProperties;
import com.page.Kfgl_LoginPage;
import com.util.LogsInit;
import com.util.Selenium2Proxy;

public class Kfgl_LoginBusiness extends LogsInit {

	Kfgl_LoginPage page = new Kfgl_LoginPage();

	/**
	 * 打开测试URL
	 * 
	 * @param driver
	 */
	public void goToUrl(WebDriver driver) {
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
	public void loginTest(WebDriver driver, String username, String password)
			throws Exception {
		this.goToUrl(driver);//打开测试网址
		
		page.setUsername(driver, username);//输入用户名
		page.setPassword(driver, password);//数据密码
		page.clickButton(driver);//点击登录按钮
		
		// boolean result = page.waitUntil(driver);

		// return result;
	}

	/**
	 * 默认登录-管理员
	 * 
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public boolean defaultLogin(WebDriver driver) throws Exception {

		this.goToUrl(driver);
		page.setUsername(driver, GetTestProperties.getname());
		page.setPassword(driver, GetTestProperties.getpassword());
		page.clickButton(driver);
		Thread.sleep(500);
		// boolean result = page.waitUntil(driver);
		boolean result = driver.getTitle().contains("软件应用框架");
		return result;
	}

	/**
	 * 默认登录-PM
	 * 
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public boolean defaultLoginPm(WebDriver driver) throws Exception {

		this.goToUrl(driver);
		page.setUsername(driver, GetTestProperties.getnamepm());
		page.setPassword(driver, GetTestProperties.getpasswordpm());
		page.clickButton(driver);
		// boolean result = page.waitUntil(driver);
		boolean result = driver.getTitle().contains("软件应用框架");
		return result;
	}

	/**
	 * 默认登录-CD
	 * 
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public boolean defaultLoginCd(WebDriver driver) throws Exception {

		this.goToUrl(driver);
		page.setUsername(driver, GetTestProperties.getnamecd());
		page.setPassword(driver, GetTestProperties.getpasswordcd());
		page.clickButton(driver);
		// boolean result = page.waitUntil(driver);
		boolean result = driver.getTitle().contains("软件应用框架");
		return result;
	}

	/**
	 * 点击Alert的确定按钮
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void acceptAlert(WebDriver driver) throws Exception {
		Selenium2Proxy tool = new Selenium2Proxy(driver);
		tool.acceptAlert();
	}

	/**
	 * 核实结果
	 * 
	 * @param driver
	 * @param flag
	 * @throws Exception
	 */
	public void checkResult(WebDriver driver, int flag) throws Exception {
		switch (flag) {
		case 1:
			driver.findElements(By.tagName("a")).get(0).click();
			break;

		}

	}
}
