package org.usfirst.frc.team3612.robot.commands;

import org.usfirst.frc.team3612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoIntake extends Command {

	private boolean isDone = false;
	
    public autoIntake() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.intake.getState() == -1) {
    		isDone = true;
    	}
    	else if (Robot.intake.getState() == 0) {
    		Robot.intake.setState(1);
    	}
    	else if (Robot.intake.getState() == 1) {
    		Robot.intake.setState(0);
    	}
    	else if (Robot.intake.getState() == 2) {
    		Robot.intake.setState(3);
    	}
    	else if (Robot.intake.getState() == 3) {
    		Robot.intake.setState(2);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Robot.intake.getState() == 0) {
    		isDone = true;
    	}
    	else if(Robot.intake.getState() == 1) {
    		Robot.intake.runMotor(1);
    		Robot.intake.intakeGrab();
    		if (Robot.intake.getLimitSwitch()) {
    			isDone = true;
    		}
    	}
    	else if (Robot.intake.getState() == 2) {
    	}
    	else if (Robot.intake.getState() == 3) {
    		Robot.intake.runMotorTimed(-1, 3);
    		Robot.intake.intakeRelease();
    		isDone=true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	if (Robot.intake.getState() == 0) {
    		Robot.intake.runMotor(0);
    		Robot.intake.intakeRelease();
    	}
    	else if (Robot.intake.getState() == 1) {
    		Robot.intake.runMotor(0);
    		Robot.intake.setState(2);
    	}
    	else if (Robot.intake.getState() == 3) {
    		Robot.intake.runMotor(0);
    		Robot.intake.intakeRelease();
    		Robot.intake.setState(0);
    	}
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
