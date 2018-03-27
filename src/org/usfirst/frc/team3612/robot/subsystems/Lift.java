package org.usfirst.frc.team3612.robot.subsystems;

import org.usfirst.frc.team3612.robot.Robot;
import org.usfirst.frc.team3612.robot.RobotMap;
import org.usfirst.frc.team3612.robot.commands.JoystickRunLift;
import org.usfirst.frc.team3612.robot.commands.runLift;
import org.usfirst.frc.team3612.robot.util.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {
	private static WPI_TalonSRX rightMotor = new WPI_TalonSRX(RobotMap.rightLiftD);
	private static WPI_TalonSRX leftMotor = new WPI_TalonSRX(RobotMap.leftLiftC);
	
	public Lift() {
		
		rightMotor.follow(leftMotor);
		leftMotor.setSensorPhase(true);
		
		leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);	
		
	}
	
	public void runMotor(double speed) {
		leftMotor.set(ControlMode.PercentOutput, speed);
		rightMotor.set(ControlMode.PercentOutput, speed);
	}
	
	public void runPID(double setpoint) {
		leftMotor.set(ControlMode.Position, setpoint);
	}
	
	public int getPosition() {
		return 	leftMotor.getSensorCollection().getPulseWidthPosition();
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putData("leftLift", leftMotor);
		SmartDashboard.putData("RightLift", rightMotor);
		SmartDashboard.putNumber("LiftPos: ", getPosition());
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}