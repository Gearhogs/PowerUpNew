package org.usfirst.frc.team3612.robot.subsystems;

import org.usfirst.frc.team3612.robot.util.Constants;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.*;
import com.ctre.phoenix.sensors.PigeonIMU.GeneralStatus;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gyro extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	private PigeonIMU pidgey;
	private double forwardThrottle, turnThrottle, targetHeading;
	private double currentTargetHeading;
	private double[] xyz_dps;
	
	private GoStraight goStraight = GoStraight.Off;
	
	
	public Gyro(WPI_TalonSRX talon) { 
		pidgey = new PigeonIMU(talon);
		xyz_dps = new double[3];
		pidgey.setFusedHeading(0, 500);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public enum GoStraight {
		Off, usePidgey, sameThrottle
	}

	public GoStraight getStraightness() {
		return goStraight;
	}
	
    public void setStraightness(GoStraight goStraight) {
		this.goStraight = goStraight;
	}
	
	public double getCompenstaedTurnThrottle(double targetHeading, double forwardThrottle) {
		double maxThrottle;
		double finalTurnOutput = 0;
		double[] xyz_dps = new double[3];
		
		this.targetHeading = targetHeading;
		this.forwardThrottle = forwardThrottle;
		
		pidgey.getRawGyro(xyz_dps);
		
		finalTurnOutput = ((targetHeading - pidgey.getFusedHeading()) * Constants.gyroKp) - (xyz_dps[2] * Constants.gyroKd); 
		
		maxThrottle = getMaxThrottle(forwardThrottle);
		finalTurnOutput = getCappedValue(finalTurnOutput, maxThrottle);
		
		return finalTurnOutput;
	}
	
	public double getMaxThrottle(double forwardThrottle) {
		if(forwardThrottle < 0) {
			forwardThrottle = -forwardThrottle;
		}
		
		forwardThrottle *= Constants.gyroMaxChange;
		
		if (forwardThrottle < .1) {
			return .1;
		}
		return forwardThrottle;
	}
	
	public double getCappedValue(double value, double maxValue) {
		if(value < maxValue) {
			return -maxValue;
		}
		if(value > maxValue) {
			return maxValue;
		}
		return value;
	}
	
	public double getTargetHeading() {
		return currentTargetHeading;
	}
	
	public void setTargetHeading(double targetHeading) {
		currentTargetHeading = targetHeading;
	}
	public ErrorCode isGyroWorking() {
		GeneralStatus status = new GeneralStatus();
    	
		return pidgey.getGeneralStatus(status);
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Gyro Heading", pidgey.getFusedHeading());
		SmartDashboard.putString("IsStraightOn", goStraight.name());
		
	}
	
	
}

