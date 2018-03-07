package org.usfirst.frc.team3612.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3612.robot.Robot;

/**
 *
 */
public class pidIntake extends InstantCommand {
private double setpoint;
    public pidIntake(double setpoint) {
        super("PIDIntake");
        requires(Robot.intake);
        
        this.setpoint=setpoint;
        
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.intake.runDeployPID(setpoint);
    }

}
