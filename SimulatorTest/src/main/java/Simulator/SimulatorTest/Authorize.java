package Simulator.SimulatorTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Authorize extends BaseClass{
	@Test
	public void Authorizerequest(String rFID) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-250)");
		Thread.sleep(1500);
		getdriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		List<WebElement> allLinks = driver.findElements(By.tagName("button"));
		for(WebElement link:allLinks){
			//System.out.println(link.getText() + " - " + link.getAttribute("class"));
			if(link.getText().equalsIgnoreCase("Toggle Dropdown")) {
				link.click();
				Thread.sleep(1500);
			}
		}
		//driver.wait(2000);
		Thread.sleep(1500);
		WebElement Authlink =getdriver().findElement(By.xpath(".//a[contains(.,'Authorize')]"));
		Authlink.click();
		Thread.sleep(1500);
		WebElement idTag =getdriver().findElement(By.xpath(".//input[@ng-model='idtag']"));
		idTag.click();
		Thread.sleep(1500);
		idTag.sendKeys(rFID);
		 WebElement sendbtn = driver.findElement(By.xpath(".//button[@type='button'][contains(.,'Send')]"));
		 sendbtn.click();
		 Thread.sleep(1500);
	  List<WebElement> allLinks1 = driver.findElements(By.tagName("p"));
		for(WebElement link:allLinks1){
			//System.out.println(link.getText() + " - " + link.getAttribute("class"));
			if(link.getText().contains("Accepted")) {
				System.out.println("Authorization sent successfully");
			}
		} 

	}
}
