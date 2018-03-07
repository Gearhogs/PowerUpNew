package org.usfirst.frc.team3612.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class CustomPDP extends PowerDistributionPanel{

	public CustomPDP() {
		super();
		
		// TODO Auto-generated constructor stub
		
	}
	  @Override
	  public void initSendable(SendableBuilder builder) {
	    builder.setSmartDashboardType("PowerDistributionPanel");
	      
	    builder.addDoubleProperty("Chan" + "FL:", () -> getCurrent(0), null);
	    builder.addDoubleProperty("Chan" + "FR:", () -> getCurrent(1), null);
	    builder.addDoubleProperty("Voltage", this::getVoltage, null);
	    builder.addDoubleProperty("TotalCurrent", this::getTotalCurrent, null);
	    
	  }
}
