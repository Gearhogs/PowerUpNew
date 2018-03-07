
package org.usfirst.frc.team3612.robot.commands;

import org.usfirst.frc.team3612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double forwardThrottle = Math.abs(Robot.oi.getLeftJoystick().getThrottle()-1)/2;
    	double turnThrottle =  Math.abs(Robot.oi.getRightJoystick().getThrottle()-1)/2;
    	Robot.drive.ArcadeDrive(Robot.oi.getLeftJoystick().getY()*forwardThrottle, Robot.oi.getRightJoystick().getX()*turnThrottle);
    	
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
