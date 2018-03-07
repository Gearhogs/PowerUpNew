package org.usfirst.frc.team3612.robot.subsystems;

import org.usfirst.frc.team3612.robot.RobotMap;
import org.usfirst.frc.team3612.robot.commands.ArcadeDrive;
import org.usfirst.frc.team3612.robot.util.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DriveTrain extends Subsystem {
	private WPI_TalonSRX leftDriveA= new WPI_TalonSRX(RobotMap.leftDrivePortA);
	private WPI_TalonSRX rightDriveA= new WPI_TalonSRX(RobotMap.rightDrivePortA);
	private WPI_TalonSRX leftDriveB= new WPI_TalonSRX(RobotMap.leftDrivePortB);
	private WPI_TalonSRX rightDriveB= new WPI_TalonSRX(RobotMap.rightDrivePortB);
	
	SpeedControllerGroup leftSide = new SpeedControllerGroup(leftDriveA, leftDriveB);
	SpeedControllerGroup rightSide = new SpeedControllerGroup(rightDriveA, rightDriveB);
	
	private DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
	
	public DriveTrain() {
		leftDriveA.follow(leftDriveB);
		rightDriveA.follow(rightDriveB);
		
		rightDriveA.setNeutralMode(NeutralMode.Brake);
		rightDriveB.setNeutralMode(NeutralMode.Brake);
		leftDriveA.setNeutralMode(NeutralMode.Brake);
		leftDriveB.setNeutralMode(NeutralMode.Brake);
		
		
		rightDriveB.configNominalOutputForward(0, Constants.kTimeoutMs);
		rightDriveB.configNominalOutputReverse(0, Constants.kTimeoutMs);
		rightDriveB.configPeakOutputForward(1, Constants.kTimeoutMs);
		rightDriveB.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		leftDriveB.configNominalOutputForward(0, Constants.kTimeoutMs);
		leftDriveB.configNominalOutputReverse(0, Constants.kTimeoutMs);
		leftDriveB.configPeakOutputForward(1, Constants.kTimeoutMs);
		leftDriveB.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		rightDriveB.config_kF(Constants.kPIDLoopIdx, 0.1097, Constants.kTimeoutMs);
		rightDriveB.config_kP(Constants.kPIDLoopIdx, 0.113333, Constants.kTimeoutMs);
		rightDriveB.config_kI(Constants.kPIDLoopIdx, 0, Constants.kTimeoutMs);
		rightDriveB.config_kD(Constants.kPIDLoopIdx, 0, Constants.kTimeoutMs);
		
		leftDriveB.config_kF(Constants.kPIDLoopIdx, 0.1097, Constants.kTimeoutMs);
		leftDriveB.config_kP(Constants.kPIDLoopIdx, 0.113333, Constants.kTimeoutMs);
		leftDriveB.config_kI(Constants.kPIDLoopIdx, 0, Constants.kTimeoutMs);
		leftDriveB.config_kD(Constants.kPIDLoopIdx, 0, Constants.kTimeoutMs);
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
	
	public void runMotor(double speed) {
		leftDriveA.set(speed);
		leftDriveB.set(speed);
		rightDriveA.set(speed);
		rightDriveB.set(speed);
	}
	public void stopMotor() {
		leftDriveA.set(0);
		leftDriveB.set(0);
		rightDriveA.set(0);
		rightDriveB.set(0);
	}	
	
	public void ArcadeDrive(double power, double turn) {
		drive.arcadeDrive(-power,turn,true);
	}
	
	public void CurvatureDrive(double power, double curvature, boolean isTurn) {
		drive.curvatureDrive(power,curvature,isTurn);
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putData("Differential Drive", drive);
		SmartDashboard.putData("leftDriveA", leftDriveA);
		SmartDashboard.putData("leftDriveB", leftDriveB);
		SmartDashboard.putData("rightDriveA", rightDriveA);
		SmartDashboard.putData("rightDriveB", rightDriveB);

		SmartDashboard.putBoolean("FLIsFollower", leftDriveA.getControlMode() == ControlMode.Follower);
		SmartDashboard.putBoolean("FRIsFollower", rightDriveA.getControlMode() == ControlMode.Follower);
		
	}
}