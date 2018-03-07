package org.usfirst.frc.team3612.robot.subsystems;

import org.usfirst.frc.team3612.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climb extends Subsystem {
	private static WPI_TalonSRX climb = new WPI_TalonSRX(RobotMap.climbPort);
	
	public void runMotor(double speed) {
	climb.set(speed);
	}
    public void initDefaultCommand() {
		SmartDashboard.putData("ClimbMotor", climb);
    }
}

