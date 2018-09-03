package com.webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TypeFun {
	public void typefun(String s5, String s6,String s7,int i,String Object_Type1,WebDriver driver1) {
		
		
		
		if(Object_Type1.equals("Id"))
		{
		driver1.findElement(By.id(s5)).sendKeys(s6);
		}
		if(Object_Type1.equals("Css"))
		{
		driver1.findElement(By.cssSelector(s5)).sendKeys(s6);
		}
		if(Object_Type1.equals("Classname"))
		{
		driver1.findElement(By.className(s5)).sendKeys(s6);
		}
		if(Object_Type1.equals("Xpath"))
		{
		driver1.findElement(By.xpath(s5)).sendKeys(s6);
		}
		/*if(s7.equals("yes"))
		{
			selenium.captureScreenshot("D:\\type"+i+".png");
		}*/
		
	}
}

