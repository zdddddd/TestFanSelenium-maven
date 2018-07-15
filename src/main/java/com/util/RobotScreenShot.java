package com.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.config.GetTestProperties;

/**
 * robot screenshot
 * 
 * @author Harry W.xu, Jan 10, 2013
 * 
 */
public class RobotScreenShot {
	private String fileName;// 文件的前缀
	// private String defaultName = "ScreenShot";
	// static int serialNum = 0;
	private String imageFormat;// 图像文件的格式
	private String defaultImageFormat = "png";
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * 构造函数
	 */
	public RobotScreenShot() {
		fileName = "";
		imageFormat = defaultImageFormat;
	}

	// /**
	// * 本构造支持JPG和PNG文件的存储
	// */
	// public RobotScreenShot(String s, String format) {
	// fileName = s;
	// imageFormat = format;
	// }

	/**
	 * 对屏幕进行拍照
	 * 
	 */
	public void snapShot() {

		// 定义了截图存放目录名
		// String dir_name = "screenshot";
		String dir_name = GetTestProperties.getPicDir();
		// 判断是否存在该目录
		if (!(new File(dir_name).isDirectory())) {
			// 如果不存在则新建一个目录
			new File(dir_name).mkdir();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		// 格式化当前时间，例如20121231-165210
		String time = sdf.format(new Date());

		try {
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenshot = (new Robot())
					.createScreenCapture(new Rectangle(0, 0,
							(int) d.getWidth(), (int) d.getHeight()));
			// serialNum++;
			// 根据文件前缀变量和文件格式变量，自动生成文件名
			String name = dir_name + File.separator + fileName + time + "."
					+ imageFormat;
			File f = new File(name);
			// 将screenshot对象写入图像文件
			ImageIO.write(screenshot, imageFormat, f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 对屏幕进行拍照,追加方法名
	 * 
	 * @param methodname
	 */
	public void snapShot(String methodname) {

		// 定义了截图存放目录名
		// String dir_name = "screenshot";
		String dir_name = GetTestProperties.getPicDir();
		// 判断是否存在该目录
		if (!(new File(dir_name).isDirectory())) {
			// 如果不存在则新建一个目录
			new File(dir_name).mkdir();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		// 格式化当前时间，例如20121231-165210
		String time = sdf.format(new Date());

		try {
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenshot = (new Robot())
					.createScreenCapture(new Rectangle(0, 0,
							(int) d.getWidth(), (int) d.getHeight()));
			// serialNum++;
			// 根据文件前缀变量和文件格式变量，自动生成文件名
			String name = dir_name + File.separator + fileName + time + "."
					+ imageFormat;
			File f = new File(name);
			// 将screenshot对象写入图像文件
			ImageIO.write(screenshot, imageFormat, f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// RobotScreenShot cam = new RobotScreenShot("Test", "png");
	// cam.snapShot();
	// }
}
