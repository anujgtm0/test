package Simulator.SimulatorTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SendBootOnly extends BaseClass{
	public String Res="PASS";
	ITestResult result;
	Write obj1 = new Write();
	public int DataSet = -1;
	public int DataSet1 = -1;
	public int DataSet2 = -1;
	InitiateSimulator IS= new InitiateSimulator();
	BootNotification BN= new BootNotification();
	testconnections tc= new testconnections();
	//Dataproviders dp= new Dataproviders();
	
	  @Test(dataProvider = "Readconfigdata", dataProviderClass =
	  Dataproviders.class,priority=1) public void opensimulator(String URL,String
	  Endpoint,String ChargeboxID,String Version,String RS) throws Exception {
	  DataSet++;
	  
	  IS.OpenSimulator(URL);
	  
	  IS.EnterConfigurations(Endpoint,ChargeboxID,Version);
	  
	  IS.Submitconfiguration(); try { WebElement
	  alert=getdriver().findElement(By.xpath(
	  ".//div[@ng-class='alertclass'][@ng-show='isShow'")); String alertmessage =
	  alert.getText(); Assert.assertEquals(alertmessage, "Configuration applied!");
	  
	  } catch(NoSuchElementException ex) {
	  
	  } Res="PASS"; 
	  obj1.WriteResult(Res, DataSet+1, "Configurations");
	  
	  }
	 
		  @Test(dataProvider = "BootData", dataProviderClass =
		  Dataproviders.class,priority=2) public void sendboot(String cpno11,String
		  cbno1,String RS) throws Exception {
			  DataSet1++;
		  System.out.println("---------------Before Boot Status ------------------");
		  tc.testevsestatus(cpno11); tc.testocpplogs(); BN.Entervalues(cpno11, cbno1);
		  BN.SendRequest();
		  System.out.println("---------------After Boot Status ------------------");
		  tc.testevsestatus(cpno11);
		  System.out.println("---------------OCPP Log after Boot ------------------");
		  tc.testocpplogs(); 
		  Res="PASS";
		  obj1.WriteResult("PASS", DataSet1+1,"BootNotification"); 
		  }
		 


@AfterClass
public void afterclass() {
	driver.close();
}


}
