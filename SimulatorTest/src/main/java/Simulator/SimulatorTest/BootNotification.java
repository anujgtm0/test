package Simulator.SimulatorTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BootNotification extends BaseClass{


@Test
public void Entervalues(String cpno1,String cbno1) throws InterruptedException {
	Thread.sleep(1500);
	WebElement openpage=getdriver().findElement(By.xpath(".//a[contains(.,'ChargePoint')]"));
	openpage.click();
	Thread.sleep(1500);
	List<WebElement> allLinks = driver.findElements(By.tagName("button"));
	for(WebElement link:allLinks){
		//System.out.println(link.getText() + " - " + link.getAttribute("class"));
		if(link.getText().equalsIgnoreCase("Toggle Dropdown")) {
			link.click();
		}
	}
	
	Thread.sleep(1500);
	WebElement SelectBoot =getdriver().findElement(By.xpath(".//a[contains(.,'BootNotification')]"));
	SelectBoot.click();
	Thread.sleep(1500);
	WebElement cpno=getdriver().findElement(By.xpath(".//input[@ng-model='cpno']"));
	cpno.clear();
	cpno.sendKeys(cpno1);
	Thread.sleep(1500);
	WebElement cbno=getdriver().findElement(By.xpath(".//input[@ng-model='cbno']"));
	cbno.clear();
	cbno.sendKeys(cbno1);
}
	@Test
	public void SendRequest() throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 WebElement Element = driver.findElement(By.xpath(".//button[@type='button'][contains(.,'Send')]"));
	  js.executeScript("arguments[0].scrollIntoView();", Element);
	  Thread.sleep(1500);
	  Element.click();
	  Thread.sleep(1500);
	//  driver.wait(2000);
	  List<WebElement> allLinks1 = driver.findElements(By.tagName("p"));
		for(WebElement link:allLinks1){
			//System.out.println(link.getText() + " - " + link.getAttribute("class"));
			if(link.getText().contains("Accepted")) {
				String status="true";
				Assert.assertEquals("true", status);
				//System.out.println("Boot notification sent successfully");
			}
		} 
		js.executeScript("window.scrollBy(0,-250)");
	
	
}
	
	
	
}
