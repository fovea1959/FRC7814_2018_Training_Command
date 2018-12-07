package org.usfirst.frc.team7814.robot.cg;

import org.usfirst.frc.team7814.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SillyCommandGroup extends CommandGroup {

    public SillyCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new SillyCommand ("t1", 2.0));
    	
    	addSequential(new SillyCommand ("t2", 1.0));
    	
    	addParallel(new SillyCommand ("t3", 2.0));
    	addSequential(new SillyCommand ("t4", 3.0));
    	
    	addParallel(new SillyCommand ("t5", 0.5));
    	addParallel(new SillyCommand ("t6", 1.5));
    	addSequential(new SillyCommand ("t7", 2.5));
    	
    	addSequential(new SillyCommand ("t8", 0.1));
    }

	@Override
	protected void initialize() {
		Robot.resetT0();
		
		// and now do the normal command stuff
		
		super.initialize();
	}
}
