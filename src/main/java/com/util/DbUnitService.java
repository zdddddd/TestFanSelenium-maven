/**
 * Copy Right Information   : SinoData
 * Project                  : SeleniumAuto
 * JDK version used         : jdk1.6.35
 * Comments                 : DbUnitService
 * Version                  : 1.00
 * Modification history     : 
 * 1. 2013.1.5 Harry W.xu      	new
 **/
package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import com.config.GetTestProperties;

/**
 * DatabaseService
 * 
 * @author Harry W.xu, Jan 5, 2013
 * 
 */
public class DbUnitService extends DBTestCase {
	private final Logger log = Logger.getLogger(getClass());
	// 定义了备份文件存放目录名
	// String dir_name = "dbbackup";
	String dir_name;

	public DbUnitService() {
		dir_name = GetTestProperties.getDbDir();
		String dbusername = GetTestProperties.getdbusername();
		String dbpassword = GetTestProperties.getdbpassword();
		String dbtype = GetTestProperties.getdbtype();
		String dburl = GetTestProperties.getdburl();

		try {
			PropertyConfigurator.configure(CommonMethord.getRealath()
					+ "conf/log4j.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CommonMethord.createDir(dir_name);

		if (dbtype.equals("mysql")) {
			System.setProperty(
					PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
					"com.mysql.jdbc.Driver");
		} else {
			log.error("undefined db type !");
		}
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, dburl);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				dbusername);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				dbpassword);
	}

	/**
	 * 给定数据集
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected IDataSet getDataSet() throws Exception {
		log.info("init...");
		return new FlatXmlDataSet(new FileInputStream(""));
	}

	/**
	 * getSetUpOperation
	 * 
	 * @return DatabaseOperation
	 * @throws Exception
	 */
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	/**
	 * getTearDownOperation
	 * 
	 * @return DatabaseOperation
	 * @throws Exception
	 */
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	/**
	 * 给定数据集
	 */
	@SuppressWarnings("deprecation")
	protected IDataSet getDataSet(String fileName) throws Exception {
		log.info("init...");
		return new FlatXmlDataSet(new FileInputStream(fileName));
	}

	/**
	 * 备份单个数据表
	 * 
	 * @param tbname
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void backupTable(String tbname, String xmlFileName) throws Exception {
		IDatabaseConnection connection = getConnection();
		try {

			QueryDataSet dataSet = new QueryDataSet(connection);
			// 将表里的数据导出到 xml文件里
			dataSet.addTable(tbname);
			// 将表里符合条件的数据导出到xml文件里
			// dataSet.addTable("users", "select * from users where id < 4");
			// 导出到dbunit.xml文件里
			File f_file = new File(dir_name + File.separator + xmlFileName);
			FlatXmlDataSet.write(dataSet, new FileOutputStream(f_file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 备份多个数据表,英文逗号分隔多个表名
	 * 
	 * @param str
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void backupTables(String str, String xmlFileName) throws Exception {
		IDatabaseConnection connection = getConnection();
		try {
			String tbname;
			List<String> tbs = CommonMethord.getList(str);
			QueryDataSet dataSet = new QueryDataSet(connection);
			// 添加多个table
			for (int i = 0; i < tbs.size(); i++) {
				tbname = tbs.get(i);
				dataSet.addTable(tbname);
			}
			// 导出到dbunit.xml文件里
			File f_file = new File(dir_name + File.separator + xmlFileName);
			FlatXmlDataSet.write(dataSet, new FileOutputStream(f_file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 备份全部数据表
	 * 
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void backupAllTables(String xmlFileName) throws Exception {
		IDatabaseConnection connection = getConnection();

		try {
			// 如果想把某个数据库里的所有表里的数据全部导出到某个xml里,又不想通过addTable一个个来添加的话。
			// 则必须通过IDatabaseConnection的createDataSet()来创建IDataSet
			IDataSet dataSet = connection.createDataSet();
			// 导出到dbunit.xml文件里
			File f_file = new File(dir_name + File.separator + xmlFileName);
			FlatXmlDataSet.write(dataSet, new FileOutputStream(f_file));
			// 也可以用FlatDtdDataSet导出一个对应的dtd文件
			// FlatDtdDataSet.write(dataSet, new FileOutputStream(
			// "dbunit_alltb.dtd"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * restoreDb
	 * 
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void restoreDb(String xmlFileName) throws Exception {
		IDatabaseConnection connection = getConnection();
		try {
			// IDataSet xmlDataSet = new FlatXmlDataSet(new FileInputStream(
			// fileName));
			File f_file = new File(CommonMethord.getRealath() + File.separator
					+ dir_name + File.separator + xmlFileName);
			IDataSet xmlDataSet = getDataSet(f_file.getAbsolutePath());
			DatabaseOperation.CLEAN_INSERT.execute(connection, xmlDataSet);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}
	}

}
