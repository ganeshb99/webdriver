package com.webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ClickFun {
	public void clickfun(String s5, String s6,int i,String Object_Type1,WebDriver driver1) {
		if(Object_Type1.equals("Id"))
		{
		driver1.findElement(By.id(s5)).click();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		if(Object_Type1.equals("Css"))
		{
		driver1.findElement(By.cssSelector(s5)).click();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		if(Object_Type1.equals("Classname"))
		{
		driver1.findElement(By.cssSelector(s5)).click();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		if(Object_Type1.equals("Xpath"))
		{
		driver1.findElement(By.xpath(s5)).click();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}
}
		/*if(s7.equals("yes"))
		{
			selenium.captureScreenshot("D:\\type"+i+".png");
		}*/
		