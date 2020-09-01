package com.omnibridge.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

public class TestBase {
	
	/*
	 * WebDriver 
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * 
	 * Excel
	 * Mail
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLog");
	
	
	@BeforeSuite
	public void setUp()
	{
		if(driver==null){
			
			 
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				config.load(fis);
				log.debug("Congig file loaded..");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
			 try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				OR.load(fis);
				log.debug("OR file loaded..");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(config.getProperty("browser").equals("firefox")){
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if(config.getProperty("browser").equals("chrome")){
		
		//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\test\\resources\\executables\\chromedriver.exe");
      		    System.setProperty("webdriver.chrome.driver", "D:\\SecondEclipse\\BindurekhaDataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");	
				
				driver = new ChromeDriver();
				log.debug("Chrome Launched..");
				
				
				
			} else if(config.getProperty("browser").equals("ie")){
				
				System.setProperty("webdriver.chromr.driver", System.getProperty("user.dir")+"src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to Bindurekha application..");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
 
		 
			 
		}	
		
	}
	
	public boolean isElementPresent(By by) {
		
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
		
		
	}
	
	@AfterSuite
	public void tearDown()
	{
		if (driver != null) {
			driver.quit();
		}

		log.debug("test execution completed !!!");
	}

}
