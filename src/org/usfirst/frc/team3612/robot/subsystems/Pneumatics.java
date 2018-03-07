package org.usfirst.frc.team3612.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {

	Compressor compressor = new Compressor(0);
	
	public Pneumatics() {
		compressor.setClosedLoopControl(true);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void updateSmartDashboard() {
    	SmartDashboard.putData("Compressor", compressor);
    	SmartDashboard.putBoolean("Compressor Closed Loop", compressor.getClosedLoopControl());
    }
    
}

