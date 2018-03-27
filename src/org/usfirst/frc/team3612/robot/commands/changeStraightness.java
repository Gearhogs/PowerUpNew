package org.usfirst.frc.team3612.robot.commands;

import org.usfirst.frc.team3612.robot.Robot;
import org.usfirst.frc.team3612.robot.subsystems.Gyro.*;

import com.ctre.phoenix.ErrorCode;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class changeStraightness extends InstantCommand {

	private boolean wantGoStraight;
	
    public changeStraightness(boolean wantStraight) {
        super();
        this.wantGoStraight = wantStraight;
    }

    // Called once when the command executes
    protected void initialize() { 
    	if (wantGoStraight) {
    		if(Robot.drive.gyro.isGyroWorking() == ErrorCode.SensorNotPresent) {
    			Robot.drive.gyro.setStraightness(GoStraight.sameThrottle);
    		}
    		else {
    			Robot.drive.gyro.setStraightness(GoStraight.usePidgey);
    		}
    	}
    	else {
    		Robot.drive.gyro.setStraightness(GoStraight.Off);
    	}
    }

}
