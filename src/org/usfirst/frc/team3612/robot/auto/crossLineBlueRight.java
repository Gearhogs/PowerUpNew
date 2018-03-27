package org.usfirst.frc.team3612.robot.auto;

import org.usfirst.frc.team3612.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class crossLineBlueRight extends CommandGroup {

    public crossLineBlueRight() {
	addSequential(new DriveForward(5,0.75));
    }
}
