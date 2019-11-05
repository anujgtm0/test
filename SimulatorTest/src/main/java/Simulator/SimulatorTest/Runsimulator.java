package Simulator.SimulatorTest;

import java.io.IOException;

import org.testng.annotations.Test;

public class Runsimulator {
	@Test(priority=1)
	public void Initiatecommand() throws InterruptedException {
		
		 try {
			  
	          String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\anujg\\eclipse-workspace\\SimulatorTest\\sim.bat"};
          Process p =  Runtime.getRuntime().exec(command);   
          Thread.sleep(2000);
	       } catch (IOException ex) {
	      }
	}
}
