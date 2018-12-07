package org.usfirst.frc.team7814.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveInTeleopCommand extends Command {

    public DriveInTeleopCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.commandMessage();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/* 
		 * drive the robot
		 */
		double joyY = Robot.driverJoystick.getY();
		double joyX = Robot.driverJoystick.getX();
		// we need to say -joyY because the joystick registers negative when
		// pushed forward, but arcadeDrive wants a positive number to move forward
		Robot.drive.arcadeDrive(-joyY, joyX);
		SmartDashboard.putNumber("joystick X", joyX);
		SmartDashboard.putNumber("joystick Y", joyY);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.commandMessage();
    	Robot.drive.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.commandMessage();
    	Robot.drive.stopMotor();
    }
}
