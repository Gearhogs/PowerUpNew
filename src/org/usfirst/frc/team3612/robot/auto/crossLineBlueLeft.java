package org.usfirst.frc.team3612.robot.auto;

import org.usfirst.frc.team3612.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class crossLineBlueLeft extends CommandGroup {

    public crossLineBlueLeft() {
	addSequential(new DriveForward(5,0.75));
    }
}
