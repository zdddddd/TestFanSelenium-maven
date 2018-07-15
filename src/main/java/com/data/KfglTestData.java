package com.data;

import org.testng.annotations.DataProvider;



public class KfglTestData {


	@DataProvider(name = "LoginSucc")
	public static Object[][] LoginSucc() {
		return new Object[][] { new Object[] { 1, "admin", "admin", "系统管理员" },
				new Object[] { 2, "xuwei", "xuwei", "徐伟" }, };
	}

	@DataProvider(name = "LoginFail")
	public static Object[][] LoginFail() {
		return new Object[][] { new Object[] { 1, "admin", "admin1", "系统管理员" },
				new Object[] { 2, "xuwei1", "xuwei", "徐伟" }, };
	}
	
	
}
