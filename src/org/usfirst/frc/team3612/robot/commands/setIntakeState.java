package org.usfirst.frc.team3612.robot.commands;

import org.usfirst.frc.team3612.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class setIntakeState extends InstantCommand {

	private int setState;
    public setIntakeState(int setState) {
        super();
        requires(Robot.intake);
        this.setState= setState;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.intake.setState(setState);	
    }

}
