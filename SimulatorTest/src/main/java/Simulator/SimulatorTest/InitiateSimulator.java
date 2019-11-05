package Simulator.SimulatorTest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InitiateSimulator extends BaseClass {
	//Runsimulator bc=new Runsimulator();
	
	String alertmessage=null;
		
	public void OpenSimulator(String uRL) throws InterruptedException
	{
		

		// Initialize browser
		 
		
		Thread.sleep(1500);

		// Open facebook


		// Maximize browser
		
		getdriver().manage().window().maximize();
		getdriver().navigate().refresh();
		getdriver().get(uRL);
		getdriver().navigate().refresh();
		getdriver().get(uRL);
		//driver.wait(2000);
		getdriver().findElement(By.id("config")).click();
		Thread.sleep(1500);
	}
	
	public void EnterConfigurations(String endpoint2, String chargeboxID2, String version) throws InterruptedException
	{
		WebElement Endpoint=getdriver().findElement(By.xpath(".//input[@name='csendpoint']"));
		Endpoint.clear();
		Endpoint.sendKeys(endpoint2);
		Thread.sleep(1500);
		WebElement ChargeBoxId =getdriver().findElement(By.xpath(".//input[@ng-model='cpserialno']"));
		ChargeBoxId.clear();
		ChargeBoxId.sendKeys(chargeboxID2);
		//WebElement Version = driver.findElement(By.xpath(".//button[contains(.,'Version')]"));
		//Version.click();
		Thread.sleep(1500);
		WebElement Versionselect = getdriver().findElement(By.xpath(".//input[@ng-model='selectedocppversion']"));
		Versionselect.click();
		Versionselect.sendKeys(version);
		Thread.sleep(1500);
	}
	
@Test
	public void Submitconfiguration() throws InterruptedException
	{
		
		WebElement submit = getdriver().findElement(By.xpath(".//button[@class='btn btn-primary']"));
		
		submit.click();
		Thread.sleep(1500);
		try {
		WebElement alert=getdriver().findElement(By.xpath(".//div[@ng-class='alertclass'][@ng-show='isShow'"));
		 alertmessage=alert.getText();
        Assert.assertEquals(alertmessage, "Configuration applied!");
       
		}
		catch(NoSuchElementException ex) {
			
		}
		
		
		}
	
	
			
	}
	
	
	
	
	

