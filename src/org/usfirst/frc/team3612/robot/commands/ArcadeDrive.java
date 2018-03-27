
package org.usfirst.frc.team3612.robot.commands;

import org.usfirst.frc.team3612.robot.Robot;
import org.usfirst.frc.team3612.robot.subsystems.Gyro;
import org.usfirst.frc.team3612.robot.subsystems.Gyro.GoStraight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDrive extends Command {

	private GoStraight goStraight;
	private double forwardThrottle;
	private double turnThrottle;
	private double leftPower;
	private double rightPower;
	
    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
        goStraight = Robot.drive.gyro.getStraightness();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    		forwardThrottle = Math.abs(Robot.oi.getLeftJoystick().getThrottle()-1)/2;
    		turnThrottle =  Math.abs(Robot.oi.getRightJoystick().getThrottle()-1)/2;
        	
    		forwardThrottle *= Robot.oi.getLeftJoystick().getY();
    		turnThrottle *= Robot.oi.getRightJoystick().getX();
    		
    		if (goStraight == Gyro.GoStraight.sameThrottle) {
    			turnThrottle = 0;
    			SmartDashboard.putString("Debug Arcade Drive", "Same Throttle");
    		}
    		else if (goStraight == Gyro.GoStraight.usePidgey) {
    			turnThrottle = Robot.drive.gyro.getCompenstaedTurnThrottle(Robot.drive.gyro.getTargetHeading(), forwardThrottle);
    			SmartDashboard.putString("Debug Arcade Drive", "Same Throttle");
    		}
    	
    		SmartDashboard.putNumber("Forward Throttle", forwardThrottle);
    		SmartDashboard.putNumber("Turn Throttle", turnThrottle);
    		if (goStraight == GoStraight.Off)
    			Robot.drive.ArcadeDrive(forwardThrottle, turnThrottle, true);
    		else
    			Robot.drive.ArcadeDrive(forwardThrottle, turnThrottle, false);
    	*/
    	
    	Robot.drive.ArcadeDrive(Robot.oi.getLeftJoystick().getY(), Robot.oi.getRightJoystick().getX(), false);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
