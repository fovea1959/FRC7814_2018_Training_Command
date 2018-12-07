package org.usfirst.frc.team7814.robot.cg;

import org.usfirst.frc.team7814.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class SillyCommand extends TimedCommand {
	String label;

	public SillyCommand(String _label, double timeout) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		label = _label;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.timedCommandMessage(label);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Called once after timeout
	protected void end() {
		Robot.timedCommandMessage(label);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.timedCommandMessage(label);
	}
}
