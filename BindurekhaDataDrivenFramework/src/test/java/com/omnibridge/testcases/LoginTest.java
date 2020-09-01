package com.omnibridge.testcases;



import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.omnibridge.base.TestBase;

public class LoginTest extends TestBase{
	
	
	@Test
	public void loginAsUser() throws Exception
	{
		log.debug("Inside Login test..");
		driver.findElement(By.xpath(OR.getProperty("LoginHomeLink"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("UsernameField"))).sendKeys("8975661426");
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("PasswordField"))).sendKeys("Reset@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("LoginBtn"))).click();
		Thread.sleep(2000);
	
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Logo"))), "Login not successfull");
		Thread.sleep(2000);
		
		
		
	}

}
