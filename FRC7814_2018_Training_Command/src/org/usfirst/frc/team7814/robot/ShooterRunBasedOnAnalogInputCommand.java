package org.usfirst.frc.team7814.robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterRunBasedOnAnalogInputCommand extends Command {

    public ShooterRunBasedOnAnalogInputCommand() {
        // Use requires() here to declare subsystem dependencies
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterWheelSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.commandMessage();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double inputVoltage = Robot.aio0.getVoltage();
    	double desiredPower= inputVoltage/5.0;
    	Robot.shooterWheelSubsystem.turnShootersOn(desiredPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.commandMessage();
    	Robot.shooterWheelSubsystem.turnShootersOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.commandMessage();
    	Robot.shooterWheelSubsystem.turnShootersOff();
    }
}
