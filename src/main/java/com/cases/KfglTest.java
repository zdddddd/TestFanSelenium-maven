package com.cases;

import java.lang.reflect.Method;
import java.util.Map;

import org.databene.benerator.anno.Database;
import org.databene.benerator.anno.InvocationCount;
import org.databene.benerator.anno.Source;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.business.Kfgl_LoginBusiness;
import com.data.KfglTestData;
import com.data.StaticProvider;
import com.util.Selenium2Proxy;

//import com.config.ExcelDataProvider;

@Database(id = "db", environment = "conf/fee4db")
public class KfglTest extends TestBase {

	Kfgl_LoginBusiness kfgl_lb = new Kfgl_LoginBusiness();

	// static DBSystem db;

	// 用户成功登陆
	@Test(dataProvider = "LoginSucc", dataProviderClass = KfglTestData.class, enabled = true)
	public void kfgl_0001_LoginSucc(Integer n, String username,
			String password, String name, Method method) throws Exception {
		String methodName = "";
		methodName = method.getName();
		try {
			//log.info(message)
			log.info(methodName + " is running !");
			
			
			
			
			String sql = "select codename,codevalue from codedata";
			
			// String a = "SELECT COUNT(*) from userinfo where useruuid like 'zhangsan%'";
			
			
			log.info("DB value is  --- : " + ds.getData(conn, sql, 5, 2));
			
			// System.out.println("数据库结果是："+ds.getData(conn, sql, 1, 1));
			
			
			
			
			
			
			
			//-----------------
			//kfgl_lb.loginTest(driver, username, password);//调用登录的一个事务
			// kfgl_qb.query();//
			
			//-----------------
			
			
			//Thread.sleep(1500);
			//Assert.assertTrue(driver.getTitle().contains("软件应用框架"));//验证点
			captureflag = false;//断言执行成功才执行此行代码，否则跳过
			// } catch (Error e) {
			// log.info("断言失败"+e.getMessage());
			// e.printStackTrace();
			// Assert.fail("fail"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			captureflag = false;
			Selenium2Proxy se = new Selenium2Proxy(driver);
			se.captureScreenshot(methodName);
			Thread.sleep(1000);
			Assert.fail("fail: " + e.getMessage());
		} catch (AssertionError e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			captureflag = false;
			Selenium2Proxy se = new Selenium2Proxy(driver);
			se.captureScreenshot(methodName);
			Thread.sleep(1000);
			log.info(e1.getMessage());
			Assert.fail("fail: " + e1.getMessage());
		} finally {
			log.info(methodName + " is end !");
		}
	}

	// 用户登录失败
	@Test(dataProvider = "LoginFail", dataProviderClass = KfglTestData.class, enabled = false)
	public void kfgl_0002_LoginFail(Integer n, String username,
			String password, String name, Method method) throws Exception {
		String methodName = "";
		methodName = method.getName();
		try {
			log.info(methodName + " is running !");
			kfgl_lb.loginTest(driver, username, password);
			Thread.sleep(1500);
			Assert.assertTrue(driver.getPageSource().contains("错误代码"));
			captureflag = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Assert.fail("fail: " + e.getMessage());
		} catch (AssertionError e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			captureflag = false;
			Selenium2Proxy se = new Selenium2Proxy(driver);
			se.captureScreenshot(methodName);
			Thread.sleep(1000);
			Assert.fail("fail: " + e1.getMessage());
		} finally {
			log.info(methodName + " is end !");
		}
	}

	// 用户成功登陆--excel数据驱动1--这个案例会和第五个案例冲突，暂时废弃
	@Test(dataProvider = "feeder", enabled = false)
	@Source("testdata/test.xls")
	public void kfgl_0003_LoginSucc(Integer n, String username, String password)
			throws Exception {
		String methodName = "";
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			log.info(methodName + " is running !");
			kfgl_lb.loginTest(driver, username, password);
			Thread.sleep(1500);
			Assert.assertTrue(driver.getTitle().contains("软件应用框架"));
			captureflag = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Assert.fail("fail: " + e.getMessage());
		} catch (AssertionError e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			captureflag = false;
			Selenium2Proxy se = new Selenium2Proxy(driver);
			se.captureScreenshot(methodName);
			Thread.sleep(1000);
			Assert.fail("fail: " + e1.getMessage());
		} finally {
			log.info(methodName + " is end !");
		}
	}

	// 用户成功登陆--excel数据驱动2
	@Test(dataProvider = "dp", dataProviderClass = StaticProvider.class, enabled = false)
	public void kfgl_0004_LoginSucc(Map<String, String> data, Method method)
			throws Exception {
		String methodName = "";
		methodName = method.getName();
		try {
			log.info(methodName + " is running !");
			
			
			
			
			kfgl_lb.loginTest(driver, data.get("用户名"), data.get("密码"));
			
			
			
			
			
			
			
			Thread.sleep(1500);
			Assert.assertTrue(driver.getTitle().contains("软件应用框架"));
			captureflag = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Assert.fail("fail: " + e.getMessage());
		} catch (AssertionError e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			captureflag = false;
			Selenium2Proxy se = new Selenium2Proxy(driver);
			se.captureScreenshot(methodName);
			Thread.sleep(1000);
			Assert.fail("fail: " + e1.getMessage());
		} finally {
			log.info(methodName + " is end !");
		}
	}

	// 用户成功登陆--数据库数据驱动--使用feed4testng时,传入参数中有方法名的话，会导致空指针异常？待处理
	@Test(dataProvider = "feeder", enabled = false)
	@Source(id = "db", selector = "select t.userid from usertable t where t.userid like 'xuwei%'")
	@InvocationCount(2)
	public void kfgl_0005_LoginSucc(String userid) throws Exception {
		String methodName = "";
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			log.info(methodName + " is running !");
			kfgl_lb.loginTest(driver, userid, userid);
			Thread.sleep(1500);
			Assert.assertTrue(driver.getTitle().contains("软件应用框架"));
			captureflag = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Assert.fail("fail: " + e.getMessage());
		} catch (AssertionError e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			captureflag = false;
			Selenium2Proxy se = new Selenium2Proxy(driver);
			se.captureScreenshot(methodName);
			Thread.sleep(1000);
			Assert.fail("fail: " + e1.getMessage());
		} finally {
			log.info(methodName + " is end !");
		}
	}

	/*
	 * @DataProvider public Object[][] dp1() { return new Object[][] { new
	 * Object[] { 1, "admin", "admin", "系统管理员" }, new Object[] { 2, "xuwei",
	 * "xuwei", "徐伟" }, }; }
	 * 
	 * @DataProvider public Object[][] dp2() { return new Object[][] { new
	 * Object[] { 1, "admin", "admin1", "系统管理员" }, new Object[] { 2, "xuwei1",
	 * "xuwei", "徐伟" }, }; }
	 */

	// @DataProvider(name = "dp")
	// public Iterator<Object[]> dataFortestMethod(Method method)
	// throws IOException {
	// return new ExcelDataProvider(this.getClass().getName(),
	// method.getName());
	// }

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

	}

}
