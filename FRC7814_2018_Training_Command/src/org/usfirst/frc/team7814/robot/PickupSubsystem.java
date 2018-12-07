package org.usfirst.frc.team7814.robot;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PickupSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void turnPickupOn() {
		Robot.pickupRelay.set(Value.kReverse);
	}
	
	public void turnPickupOff() {
		Robot.pickupRelay.set(Value.kOff);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

