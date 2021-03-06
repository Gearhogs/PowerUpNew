/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3612.robot;

import org.usfirst.frc.team3612.robot.commands.*;
import org.usfirst.frc.team3612.robot.subsystems.Gyro.GoStraight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
	
public class OI {

	private Joystick leftJoystick;
	private Joystick rightJoystick;
	private Joystick controller;
	
	private JoystickButton liftUp;
	private JoystickButton liftDown;
	private JoystickButton intakeGrab;
	private JoystickButton intakeRelease;
	private JoystickButton intakeUp;
	private JoystickButton intakeDown;
	private JoystickButton intakeIn;
	private JoystickButton intakeOut;
	private JoystickButton autoIntake;
	private JoystickButton liftPIDHighScale;
	private JoystickButton liftPIDMidScale;
	private JoystickButton liftPIDLowScale;
	private JoystickButton liftPIDSwitch;
	private JoystickButton climbOn;
	private JoystickButton setIntakeState;
	private JoystickButton driveStraight;
	
	public OI() {
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		controller = new Joystick(2);
		
		liftUp= new JoystickButton(leftJoystick, 6);
		liftDown= new JoystickButton(leftJoystick, 4);
		
		liftUp= new JoystickButton(controller, 7); // Left Trigger on Controller
		liftDown= new JoystickButton(controller, 8); // Right Trigger on Controller
		
		intakeGrab= new JoystickButton(rightJoystick, 9);
		intakeRelease= new JoystickButton(rightJoystick, 10);
		intakeUp= new JoystickButton(rightJoystick, 12);
		intakeDown= new JoystickButton(rightJoystick, 11);
		intakeIn = new JoystickButton(leftJoystick, 5);
		intakeOut = new JoystickButton(leftJoystick, 3);
		
		intakeGrab= new JoystickButton(controller, 1); // Button_X on Controller
		intakeRelease= new JoystickButton(controller, 3); // Button_B on Controller
		intakeUp= new JoystickButton(controller, 5); // Left Bumper on Controller
		intakeDown= new JoystickButton(controller, 6); // Right Bumper on Controller
		intakeIn = new JoystickButton(controller, 4); // Button_Y on Controller
		intakeOut = new JoystickButton(controller, 2); // Button_A on Controller
		
		autoIntake = new JoystickButton(leftJoystick, 12);
		
		liftPIDHighScale= new JoystickButton(rightJoystick, 5);
		liftPIDMidScale= new JoystickButton(rightJoystick, 3);
		liftPIDLowScale= new JoystickButton(rightJoystick, 4);
		liftPIDSwitch= new JoystickButton(rightJoystick, 6);
		
		climbOn= new JoystickButton(rightJoystick, 8);
		setIntakeState= new JoystickButton(rightJoystick, 2);
		
		driveStraight = new JoystickButton(leftJoystick, 1);
		
		///////////////////////////////////////////////
		
		liftUp.whenPressed(new runLift(-1));
		liftUp.whenReleased(new runLift(0));
		liftDown.whenPressed(new runLift(.5));
		liftDown.whenReleased(new runLift(0));
		
		intakeUp.whenPressed(new intakeAngleMove(1));
		intakeUp.whenReleased(new intakeAngleMove(0));
		intakeDown.whenPressed(new intakeAngleMove(-1));
		intakeDown.whenReleased(new intakeAngleMove(0));
		
		intakeGrab.whenPressed(new closeIntake());
		intakeRelease.whenPressed(new openIntake());
		
		intakeIn.whenPressed(new runIntake(-1));
		intakeIn.whenReleased(new runIntake(0));
		intakeOut.whenPressed(new runIntake(1));
		intakeOut.whenReleased(new runIntake(0));
		
		autoIntake.whenPressed(new autoIntake());

		liftPIDHighScale.whenPressed(new pidLift(20000));
		liftPIDMidScale.whenPressed(new pidLift(0));
		liftPIDLowScale.whenPressed(new pidLift(4));
		liftPIDSwitch.whenPressed(new pidLift(6));
		climbOn.whileHeld(new runClimber(1));
		setIntakeState.whenPressed(new setIntakeState(0));
		
		driveStraight.whenPressed(new changeStraightness(true));
		driveStraight.whenReleased(new changeStraightness(false));
		
		
	}
	
	public Joystick getLeftJoystick() {
		return leftJoystick;
	}
	public Joystick getRightJoystick() {
		return rightJoystick;
	}
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("LeftJoystickX", leftJoystick.getX());
		SmartDashboard.putNumber("LeftJoystickY", leftJoystick.getY() * -1);
		SmartDashboard.putNumber("RightJoystickX", rightJoystick.getX());
		SmartDashboard.putNumber("RightJoystickY", rightJoystick.getY() * -1);
		
		SmartDashboard.putNumber("Forward Throttle", Math.abs(getLeftJoystick().getThrottle()-1)/2);
		SmartDashboard.putNumber("Turn Throttle", Math.abs(getRightJoystick().getThrottle()-1)/2);
	}
	
}
