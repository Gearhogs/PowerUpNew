package org.usfirst.frc.team3612.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LED extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private PWM led = new PWM(0);
	
	public LED() {
		led.setBounds(2000, 2000, 1500, 1000, 1000);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setRainbow() {
    	
    }
}

