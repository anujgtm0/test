package Simulator.SimulatorTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.gson.Gson;

public class StopTransaction extends BaseClass {
	String tid=null;
	@Test
	public void transIDs() {
		 List<WebElement> allLinks1 = driver.findElements(By.tagName("p"));
		 
			for(WebElement link:allLinks1){
				//System.out.println(link.getText() + " - " + link.getAttribute("class"));
				if(link.getText().contains("Accepted")) {
				
					String Transactionresponse=link.getText();
					String payloadString = Transactionresponse.substring(Transactionresponse.indexOf("{"),Transactionresponse.length()-1);
					Payload payload = new Gson().fromJson(payloadString, Payload.class);
					  tid=payload.getTransactionId().toString();
					System.out.println("Transaction id is "+ tid);
					
				}
				
				
			}
		
		
		
		
	}
	@Test(dependsOnMethods= { "transIDs" })
	public void SendStopTransaction(String message, String rfid, String timestamp2) throws InterruptedException {
		
		//System.out.println("Transaction ID is ");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-250)");
		Thread.sleep(1500);
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
		WebElement StartTrans =getdriver().findElement(By.id("stopTxAction"));
		StartTrans.click();
		Thread.sleep(1500);
		WebElement reasonlink =getdriver().findElement(By.xpath(".//input[@ng-model='selectedreason']"));
		reasonlink.click();
		reasonlink.sendKeys(message);
		
		
		
		
		Thread.sleep(1500);
		WebElement Tid =getdriver().findElement(By.xpath(".//input[@ng-model='transactionid']"));
		Tid.clear();
		Thread.sleep(1500);
		Tid.sendKeys(tid);
		WebElement idTag =getdriver().findElement(By.xpath(".//input[@ng-model='idTag']"));
	    idTag.clear();
	    idTag.sendKeys(rfid);
		Thread.sleep(1500);
		//ConnectorID.sendKeys("1");
		WebElement TimeStamp =getdriver().findElement(By.xpath(".//input[@ng-model='timestamp']"));
		TimeStamp.clear();
		Thread.sleep(1500);
		TimeStamp.sendKeys(timestamp2);
		 WebElement sendbtn = driver.findElement(By.xpath(".//button[@type='button'][contains(.,'Send')]"));
		 sendbtn.click();
		 Thread.sleep(1500);
	  List<WebElement> allLinks2 = driver.findElements(By.tagName("p"));
		for(WebElement link:allLinks2){
			//System.out.println(link.getText() + " - " + link.getAttribute("class"));
			if(link.getText().contains("Accepted")) {
				System.out.println("Stop Transaction sent successfully");
			}
		} 

	}
}
