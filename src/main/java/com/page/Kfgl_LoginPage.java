package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.config.GetTestProperties;
import com.util.LogsInit;

public class Kfgl_LoginPage extends LogsInit {


	/**
	 * 设置用户名
	 * 
	 * @param driver
	 * @param userText
	 */
	public void setUsername(WebDriver driver, String userText)
			throws NotFoundException {
		// this.getElement(driver, By.id("username")).setText(userText);
		driver.findElement(By.id("username")).sendKeys(userText);
	}

	/**
	 * 设置密码
	 * 
	 * @param driver
	 * @param passText
	 */
	public void setPassword(WebDriver driver, String passText)
			throws NotFoundException {
		// this.getElement(driver, By.name("password")).setText(passText);
		//driver.findElement(By.name("imageField")).clear();
		driver.findElement(By.name("imageField")).sendKeys(passText);
	}

	/**
	 * 点击登录按钮
	 * 
	 * @param driver
	 */
	public void clickButton(WebDriver driver) throws NotFoundException {
		driver.findElement(By.name("imageField")).click();
	}

}
