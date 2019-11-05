package Simulator.SimulatorTest;

import java.sql.SQLException;

import org.testng.ITestResult;
import org.testng.annotations.Test;

public class AuthorizeSuccessfull {
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
	Authorize auth=new Authorize();
@Test(dataProvider = "Readconfigdata", dataProviderClass = 	Dataproviders.class,priority=1)
public void opensimulator(String URL,String Endpoint,String ChargeboxID,String Version,String RS) throws Exception {
	DataSet++;
	
	IS.OpenSimulator(URL);

	IS.EnterConfigurations(Endpoint,ChargeboxID,Version);

	IS.Submitconfiguration();
	
	System.out.println("---------------Before Boot Status ------------------");
	tc.testevsestatus(ChargeboxID);
	tc.testocpplogs();
	Res="PASS";
    obj1.WriteResult(Res, DataSet+1, "BootNotification");
}
@Test(dataProvider = "BootData", dataProviderClass = 	Dataproviders.class,priority=2)
public void sendboot(String cpno1,String cbno1,String RS) throws Exception {
	DataSet1++;
	BN.Entervalues(cpno1, cbno1);
	BN.SendRequest();
	System.out.println("---------------After Boot Status ------------------");
	tc.testevsestatus(cpno1);
	System.out.println("---------------OCPP Log after Boot ------------------");
	tc.testocpplogs();
	Res="PASS";
    obj1.WriteResult(Res, DataSet+1, "BootNotification");
}

@Test(dataProvider = "Authdata", dataProviderClass = 	Dataproviders.class,priority=3)
public void Authenticate(String RFID,String RS) throws Exception {
	DataSet2++;
	auth.Authorizerequest(RFID);
	//testconnections tc=new testconnections();
	System.out.println("---------------OCPP log after Auth sucessfull ------------------");
	tc.testocpplogs();
	
	Res="PASS";
    obj1.WriteResult(Res, DataSet+1, "BootNotification");
}
}
