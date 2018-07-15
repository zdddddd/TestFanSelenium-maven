package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.util.LogsInit;

public class Kfgl_LoginPage_New extends LogsInit {

	private WebDriver driver;

	public Kfgl_LoginPage_New(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 设置用户名
	 * 
	 * @param driver
	 * @param userText
	 */
	public void setUsername(String userText) throws NotFoundException {
		// driver.findElement(By.id("username")).clear();
		// driver.findElement(By.id("username")).sendKeys(userText);
		this.setText(driver.findElement(By.id("username")), userText);
	}

	/**
	 * 设置密码
	 * 
	 * @param driver
	 * @param passText
	 */
	public void setPassword(String passText) throws NotFoundException {
		// driver.findElement(By.id("password")).clear();
		// driver.findElement(By.id("password")).sendKeys(userText);
		this.setText(driver.findElement(By.id("password")), passText);
	}

	/**
	 * 点击登录按钮
	 * 
	 * @param driver
	 */
	public void clickButton() throws NotFoundException {
		driver.findElement(By.name("imageField")).click();
	}
	
	private void setText(WebElement e,String text)
	{
		e.clear();
		e.sendKeys(text);
	}

}
