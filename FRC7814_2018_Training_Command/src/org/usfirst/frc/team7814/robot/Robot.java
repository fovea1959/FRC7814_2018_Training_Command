/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7814.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	// declare the subsystems
	static public DriveSubsystem driveSubsystem;
	static public PickupSubsystem pickupSubsystem;
	static public ShooterWheelSubsystem shooterWheelSubsystem;
	
	// declare WPILib hardware
	static public DifferentialDrive drive;
	static public Talon leftShooterController, rightShooterController;
	static public Relay pickupRelay;
	static public DigitalInput leftSideShooterSensor, rightSideShooterSensor, ballSensor;
	static public AnalogInput aio0;
	
	// declare some variables for joystick
	static public Joystick driverJoystick;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		/*
		 * set up the hardware first
		 */
		
		// set up the robot drive
		Talon leftDriveController = new Talon(0);
		leftDriveController.setName("Drive", "Left Drive Motor");
		LiveWindow.add(leftShooterController);
		Talon rightDriveController = new Talon(1);
		rightDriveController.setName("Drive", "Right Drive Motor");
		LiveWindow.add(rightShooterController);
		drive = new DifferentialDrive(leftDriveController, rightDriveController);
		drive.setName("Drive", "Differential Drive");
		// LiveWindow.add(drive);

		// set up the shooter controllers
		leftShooterController = new Talon(4);
		leftShooterController.setName("Shooter", "Left Shooter Motor");
		LiveWindow.add(leftShooterController);
		rightShooterController = new Talon(5);
		rightShooterController.setName("Shooter", "Right Shooter Motor");
		LiveWindow.add(rightShooterController);
		
		// set up the pickup relay
		pickupRelay = new Relay(0, Relay.Direction.kBoth);
		pickupRelay.setName("Shooter", "Pickup Motor");
		LiveWindow.add(pickupRelay);
		
		// digital inputs
		leftSideShooterSensor = new DigitalInput(1);
		leftSideShooterSensor.setName("Shooter", "Left Side Shooter Sensor");
		LiveWindow.add(leftSideShooterSensor);
		rightSideShooterSensor = new DigitalInput(2);
		rightSideShooterSensor.setName("Shooter", "Right Side Shooter Sensor");
		LiveWindow.add(rightSideShooterSensor);
		ballSensor = new DigitalInput(3);
		ballSensor.setName("Shooter", "Ball Sensor");
		LiveWindow.add(ballSensor);
		
		// analog inputs
		aio0 = new AnalogInput(0);
		aio0.setName("Drive", "aio0");
		LiveWindow.add(aio0);
		
		/*
		 * set up the subsystems second
		 */
		driveSubsystem = new DriveSubsystem();
		pickupSubsystem = new PickupSubsystem();
		shooterWheelSubsystem = new ShooterWheelSubsystem();
		
		/*
		 * set up the operator interface last
		 */
		driverJoystick = new Joystick(0);
		
		JoystickButton shooterSlowButton = new JoystickButton(driverJoystick, 3);
		shooterSlowButton.toggleWhenPressed(new ShooterRunSlowCommand());
		JoystickButton shooterFastButton = new JoystickButton(driverJoystick, 4);
		shooterFastButton.toggleWhenPressed(new ShooterRunFastCommand());
		
		JoystickButton pickupButton = new JoystickButton(driverJoystick, 1);
		pickupButton.whileHeld(new PickupRunCommand());
	}

	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// let the commands run
		Scheduler.getInstance().run();		

		// let the drivers see the sensors
		putSensorValuesOnDashboard();
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		// let the commands run
		Scheduler.getInstance().run();		
		
		// let the drivers see the sensors
		putSensorValuesOnDashboard();
	}

	@Override
	public void testInit() {
		super.testInit();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		// let the commands run
		Scheduler.getInstance().run();		
		
		// let the drivers see the sensors
		putSensorValuesOnDashboard();
	}
	
	/*
	 * update the dashboard with sensor values
	 */
	void putSensorValuesOnDashboard() {
		SmartDashboard.putBoolean("dio1", leftSideShooterSensor.get());
		SmartDashboard.putBoolean("dio2", rightSideShooterSensor.get());
		SmartDashboard.putNumber("aio0",  aio0.getVoltage());
	}
	
	/*
	 * call this from commands to log command start, end, and interrupted
	 */
    public static void commandMessage () {
  	  Throwable t = new Throwable();
  	  StackTraceElement[] stackTraceElement = t.getStackTrace();
  	  StackTraceElement st1 = stackTraceElement[1];
  	  System.out.println("command " + st1.getClass().getSimpleName() + " " + st1.getMethodName());
    }
}
