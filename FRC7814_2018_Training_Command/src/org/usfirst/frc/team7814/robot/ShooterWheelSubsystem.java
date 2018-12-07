package org.usfirst.frc.team7814.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterWheelSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void turnShootersOn(double power) {
		Robot.leftShooterController.set(power);
		Robot.rightShooterController.set(power);
	}

	public void turnShootersOff() {
		Robot.leftShooterController.stopMotor();
		Robot.rightShooterController.stopMotor();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

