package org.usfirst.frc.team3612.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3612.robot.Robot;

/**
 *
 */
public class pidLift extends InstantCommand {
private double setpoint;
    public pidLift(double setpoint) {
        super("PIDLift");
        requires(Robot.lift);
        
        this.setpoint=setpoint;
    }
    
    // Called once when the command executes
    protected void initialize() {
        Robot.lift.runPID(setpoint);
    }

}
