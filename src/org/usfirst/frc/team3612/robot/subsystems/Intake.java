package org.usfirst.frc.team3612.robot.subsystems;

import org.usfirst.frc.team3612.robot.RobotMap;
import org.usfirst.frc.team3612.robot.commands.runIntake;
import org.usfirst.frc.team3612.robot.util.Constants;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {

	private static WPI_TalonSRX rightMotor = new WPI_TalonSRX(RobotMap.leftIntakeC);
	private static WPI_TalonSRX leftMotor = new WPI_TalonSRX(RobotMap.rightIntakeD);
	private static WPI_TalonSRX intakeDeploy = new WPI_TalonSRX(RobotMap.intakeDeploy);
	DoubleSolenoid leftCylinder = new DoubleSolenoid(RobotMap.leftCylinderFwd, RobotMap.leftCylinderRev);
	DoubleSolenoid rightCylinder = new DoubleSolenoid(RobotMap.rightCylinderFwd, RobotMap.rightCylinderRev);
	private int state = -1;
	
	ErrorCode limitSwitchError;
	
	public void config() {
		rightMotor.setName("Intake", "RightMotor");
		leftMotor.setName("Intake", "LeftMotor");
		
		rightMotor.follow(leftMotor);
		rightMotor.setInverted(true);
		
		leftMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		leftMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		leftMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		leftMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);		
		
		limitSwitchError = leftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector , LimitSwitchNormal.NormallyOpen, 1000);
		
	}
	
	public void runMotor(double speed) {
		leftMotor.set(speed);
		
	}
	public void runMotorTimed(double speed, double seconds) {
		leftMotor.set(speed);
		Timer.delay(seconds);
	}
	public void intakeGrab() {
		leftCylinder.set(Value.kForward);
		rightCylinder.set(Value.kForward);
	}
	public void intakeRelease() {

		leftCylinder.set(Value.kReverse);
		rightCylinder.set(Value.kReverse);
	}
	public void runIntakeDeploy(double rotate) {
		intakeDeploy.set(rotate);	
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public boolean getLimitSwitch() {
		return leftMotor.getSensorCollection().isRevLimitSwitchClosed();
	}
	
	
	public void runDeployPID(double setpoint) {
		leftMotor.set(ControlMode.Position, setpoint);
	}
	
	public double getPosition() {
		return leftMotor.getSelectedSensorPosition(0);
	}
	
	public String getStateName() {
		String returnState = "Null";
		
		switch (state) {
			case -1:
				returnState = "Not deployed";
				break;
			case 0:
				returnState = "Deployed and not running";
				break;
			case 1:
				returnState = "Deployed and running";
				break;
			case 2:
				returnState = "Deployed and has cube";
				break;
			case 3:
				returnState = "Ejecting cube";
				break;
		}
		return returnState;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new runIntake(0));
    }

	public void updateSmartDashboard() {
		SmartDashboard.putData("rightIntakeMotor", rightMotor);
		SmartDashboard.putData("leftIntakeMotor", leftMotor);
		SmartDashboard.putBoolean("LeftCylinder", leftCylinder.get() == DoubleSolenoid.Value.kForward);
		SmartDashboard.putBoolean("RightCylinder", rightCylinder.get() == DoubleSolenoid.Value.kForward);
	  //SmartDashboard.putBoolean("CubePresent", getLimitSwitch());
		SmartDashboard.putString("Intake State", getStateName());
		SmartDashboard.putData("intakeDeploy", intakeDeploy);
		SmartDashboard.putString("Limit Error", limitSwitchError.name());
	}
}
