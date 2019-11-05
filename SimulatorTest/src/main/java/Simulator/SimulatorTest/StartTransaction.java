package Simulator.SimulatorTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.gson.Gson;

public class StartTransaction extends BaseClass {
	@Test
	public void SendStartnotification(String iDTag2, String connID, String timestamp2) throws InterruptedException {
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
		WebElement StartTrans =getdriver().findElement(By.id("startTxAction"));
		StartTrans.click();
		Thread.sleep(1500);
		WebElement idTag =getdriver().findElement(By.xpath(".//input[@ng-model='idTag']"));
		idTag.clear();
		Thread.sleep(1500);
		idTag.sendKeys(iDTag2);
		WebElement ConnectorID =getdriver().findElement(By.xpath(".//input[@aria-describedby='connid']"));
	     ConnectorID.clear();
		Thread.sleep(1500);
		ConnectorID.sendKeys(connID);
		WebElement TimeStamp =getdriver().findElement(By.xpath(".//input[@ng-model='timestamp']"));
		TimeStamp.clear();
		Thread.sleep(1500);
		TimeStamp.sendKeys(timestamp2);
		 WebElement sendbtn = driver.findElement(By.xpath(".//button[@type='button'][contains(.,'Send')]"));
		 sendbtn.click();
		 Thread.sleep(1500);
	  List<WebElement> allLinks1 = driver.findElements(By.tagName("p"));
		for(WebElement link:allLinks1){
			//System.out.println(link.getText() + " - " + link.getAttribute("class"));
			if(link.getText().contains("Accepted")) {
				System.out.println("Start Transaction sent successfully");
				String Transactionresponse=link.getText();
				String payloadString = Transactionresponse.substring(Transactionresponse.indexOf("{"),Transactionresponse.length()-1);
				Payload payload = new Gson().fromJson(payloadString, Payload.class);
				Integer tid=payload.getTransactionId();
				System.out.println(payload.getTransactionId());
			}
		} 
		 Thread.sleep(1500);
		WebElement transID =getdriver().findElement(By.xpath(".//input[@ng-model='transactionid']"));
		String TransnID=transID.getText();
		Thread.sleep(3000);
		//System.out.println("Transaction ID is "+ TransnID);
	}
}
