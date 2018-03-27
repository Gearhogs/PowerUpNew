package org.usfirst.frc.team3612.robot.subsystems;

import org.usfirst.frc.team3612.robot.RobotMap;
import org.usfirst.frc.team3612.robot.commands.ArcadeDrive;
import org.usfirst.frc.team3612.robot.util.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.sensors.*;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DriveTrain extends Subsystem {
	private WPI_TalonSRX leftDriveSlave= new WPI_TalonSRX(RobotMap.leftDrivePortA);
	private WPI_TalonSRX rightDriveSlave= new WPI_TalonSRX(RobotMap.rightDrivePortA);
	private WPI_TalonSRX leftDrive= new WPI_TalonSRX(RobotMap.leftDrivePortB);
	private WPI_TalonSRX rightDrive= new WPI_TalonSRX(RobotMap.rightDrivePortB);
		
	SpeedControllerGroup leftSide = new SpeedControllerGroup(leftDriveSlave, leftDrive);
	SpeedControllerGroup rightSide = new SpeedControllerGroup(rightDriveSlave, rightDrive);
	
	public Gyro gyro;
	
	private DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
	
	public DriveTrain() {
		
		gyro = new Gyro(rightDrive);
		
		leftDriveSlave.follow(leftDrive);
		rightDriveSlave.follow(rightDrive);
		
		leftDriveSlave.setInverted(false);
		leftDrive.setInverted(false);
		
		rightDriveSlave.setInverted(false);
		rightDrive.setInverted(false);
		
		rightDriveSlave.setNeutralMode(NeutralMode.Brake);
		rightDrive.setNeutralMode(NeutralMode.Brake);
		leftDriveSlave.setNeutralMode(NeutralMode.Brake);
		leftDrive.setNeutralMode(NeutralMode.Brake);
		
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
	
	public void runMotor(double speed) {
		leftDriveSlave.set(speed);
		leftDrive.set(speed);
		rightDriveSlave.set(speed);
		rightDrive.set(speed);
	}
	public void stopMotor() {
		leftDriveSlave.set(0);
		leftDrive.set(0);
		rightDriveSlave.set(0);
		rightDrive.set(0);
	}	
	
	public void ArcadeDrive(double power, double turn, boolean isSquared) {
		drive.arcadeDrive(-power,turn, isSquared);
	}
	
	public void CurvatureDrive(double power, double curvature, boolean isTurn) {
		drive.curvatureDrive(power,curvature,isTurn);
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putData("Differential Drive", drive);
		SmartDashboard.putData("leftDriveA", leftDriveSlave);
		SmartDashboard.putData("leftDriveB", leftDrive);
		SmartDashboard.putData("rightDriveA", rightDriveSlave);
		SmartDashboard.putData("rightDriveB", rightDrive);	
		
		SmartDashboard.putBoolean("FLIsFollower", leftDriveSlave.getControlMode() == ControlMode.Follower);
		SmartDashboard.putBoolean("FRIsFollower", rightDriveSlave.getControlMode() == ControlMode.Follower);
		gyro.updateSmartDashboard();
		
	}
}