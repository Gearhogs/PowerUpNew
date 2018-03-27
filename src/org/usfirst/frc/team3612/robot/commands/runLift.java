package org.usfirst.frc.team3612.robot.commands;

import org.usfirst.frc.team3612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class runLift extends Command {
	private double speed;
	//private boolean isDone = false;
    public runLift(double speed) {
        super("runLift");
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lift);
        this.speed = speed;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.runMotor(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.runMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.runMotor(0);
    }
}
