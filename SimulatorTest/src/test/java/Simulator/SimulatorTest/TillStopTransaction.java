package Simulator.SimulatorTest;

import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
 
public class TillStopTransaction extends BaseClass {
	InitiateSimulator IS= new InitiateSimulator();
	public String Res="PASS";
	Write obj1 = new Write();
	public int DataSet = -1;
	public int DataSet3 = -1;
	public int DataSet1 = -1;
	public int DataSet2 = -1;
	public int DataSet4 = -1;
	BootNotification BN= new BootNotification();
	testconnections tc= new testconnections();
	//Dataproviders dp= new Dataproviders();
	Authorize auth=new Authorize();
	StartTransaction ST=new StartTransaction();
	StopTransaction Stop= new StopTransaction();
@Test(dataProvider = "Readconfigdata", dataProviderClass = 	Dataproviders.class,priority=1)
public void opensimulator(String URL,String Endpoint,String ChargeboxID,String Version,String RS) throws Exception {
	DataSet++;	
	
	IS.OpenSimulator(URL);

	IS.EnterConfigurations(Endpoint,ChargeboxID,Version);

	IS.Submitconfiguration();
	 Res="PASS";
     obj1.WriteResult(Res, DataSet+1, "Configurations");
	
	System.out.println("---------------Before Boot Status ------------------");
	tc.testevsestatus(ChargeboxID);
	tc.testocpplogs();
}
@Test(dataProvider = "BootData", dataProviderClass = 	Dataproviders.class,priority=2)
public void sendboot(String cpno1,String cbno1,String RS) throws Exception {
	DataSet1++;
	BN.Entervalues(cpno1, cbno1);
	BN.SendRequest();
	System.out.println("---------------After Boot Status ------------------");
	tc.testevsestatus(cbno1);
	System.out.println("---------------OCPP Log after Boot ------------------");
	tc.testocpplogs();
	 Res="PASS";
     obj1.WriteResult(Res, DataSet1+1, "BootNotification");
}

@Test(dataProvider = "Authdata", dataProviderClass = 	Dataproviders.class,priority=3)
public void Authenticate(String RFID,String RS) throws Exception {
	DataSet2++;
	auth.Authorizerequest(RFID);
	//testconnections tc=new testconnections();
	System.out.println("---------------OCPP log after Auth sucessfull ------------------");
	tc.testocpplogs();
	 Res="PASS";
     obj1.WriteResult(Res, DataSet2+1, "Authorization");
	
	
}

@Test(dataProvider = "StartTransData", dataProviderClass = 	Dataproviders.class,priority=4)
public void StartTrans(String evseid,String IDTag,String ConnID,String Timestamp,String RS) throws Exception {
	DataSet3++;
	ST.SendStartnotification( IDTag, ConnID, Timestamp);
	System.out.println("---------------After Start Transaction Status ------------------");
	tc.testevsestatus(evseid);
	System.out.println("---------------OCPP Log After  Start Transaction  ------------------");
	tc.testocpplogs();	
	System.out.println("---------------Session Log After  Start Transaction  ------------------");	
	tc.sessionlogs();
	 Res="PASS";
     obj1.WriteResult(Res, DataSet3+1, "StartTransaction");
}

@Test(dataProvider = "StopTransData", dataProviderClass = 	Dataproviders.class,priority=4)
public void StopTrans(String evseid,String message,String rfid,String Timestamp,String RS) throws Exception {
	DataSet4++;
	
	Stop.transIDs();
	Stop.SendStopTransaction(message,rfid,Timestamp);
	System.out.println("---------------After  Stop Transaction Status ------------------");
	tc.testevsestatus(evseid);
	System.out.println("---------------OCPP Log After  Stop Transaction  ------------------");
	tc.testocpplogs();	
	System.out.println("---------------Session log After  Stop Transaction  ------------------");
	tc.sessionlogs();
	 Res="PASS";
     obj1.WriteResult(Res, DataSet4+1, "StopTransaction");
	
}
@AfterClass
public void afterclass() {
	//driver.close();
}

}
