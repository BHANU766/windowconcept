package com.windowconcept;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tabhandle {

	   protected static String url="https://demoqa.com/browser-windows";
	   WebDriver driver;

@BeforeSuite 
public void startchromebrowser() {
	 WebDriverManager.chromedriver().setup();
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}	

@BeforeMethod
public void openurl() {
	  driver.get(url);
}

@Test
public void counttabs() throws InterruptedException {
	driver.switchTo().newWindow(WindowType.TAB); //create and open any window
	Thread.sleep(3000);
	//get all the windows
	Set<String> allwindows=driver.getWindowHandles();
	System.out.println("total tab number "+allwindows.size());
}

@Test
public void switchtotab() throws InterruptedException {
	//get all the window handle
	Set<String> allwindowshandles=driver.getWindowHandles();
	System.out.println("count of window "+allwindowshandles.size()); //1
		
	driver.findElement(By.id("tabButton")).click();
	
	Set<String> newallwindowshandles=driver.getWindowHandles();
	System.out.println("count of window "+newallwindowshandles.size()); //2
	
	 //get the hold over current window
	String parenthandle=driver.getWindowHandle();
	
 //	for(String str:newallwindowshandles) {
 //		System.out.println(str);
 //		if(!str.equals(parenthandle)) {
 //			driver.switchTo().window(childtab);
 //		}
 //	}
	
	Iterator<String> iterator=newallwindowshandles.iterator();
	String maintab=iterator.next();   //current tab
	String childtab=iterator.next();  //child tab
	
	driver.switchTo().window(childtab);
	
	Thread.sleep(5000);
	
	WebElement text=driver.findElement(By.id("sampleHeading"));
	System.out.println("tab text :" +text.getText());
	Thread.sleep(5000);	
	
	driver.close(); //close the child window
}	


@AfterSuite
public void closechromebrowser() {
	  driver.quit();
}
	
	
}
