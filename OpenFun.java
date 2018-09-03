package com.webdriver;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class OpenFun {
	public static int i=0;
	public void openfun(String s5, String s6,int i,WebDriver driver1) throws Exception {
		driver1.get(s5);
		
		if(s6.equals("yes"))
		{
			File scrFile = ((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("d:\\screenshot"+i+".png"));
		}
		
	}
}

