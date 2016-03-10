
package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.*;


public class Robot extends IterativeRobot {
	Talon talon_FL;
	Talon talon_BL;
	Talon talon_FR;
    Talon talon_BR;
    RobotDrive driveSystem;
    Joystick leftStick;
    double leftInput;
    Joystick rightStick;
    double rightInput;
    Joystick xBox;
   Compressor compress;
   DoubleSolenoid rightSolenoid;
   DoubleSolenoid leftSolenoid;
   DoubleSolenoid centerSolenoid;
   boolean rightSolenoidBool = false;
   boolean leftSolenoidBool = false;
   boolean centerSolenoidBool = false;
   
    public void robotInit() {
       talon_FL = new Talon(0);
       talon_BL = new Talon(1);
       talon_FR = new Talon(2);
       talon_BR = new Talon(3);
       driveSystem = new RobotDrive(talon_FL, talon_BL, talon_FR, talon_BR);
       leftStick = new Joystick(0);
       rightStick = new Joystick(1);
       compress = new Compressor(1);
       compress.setClosedLoopControl(true);
       leftSolenoid = new DoubleSolenoid(1,4,3);
       centerSolenoid = new DoubleSolenoid(1, 5, 2);
       rightSolenoid = new DoubleSolenoid(1, 6, 1);
       xBox = new Joystick(2);
      
    }
   
    public void teleopPeriodic() {
    	leftInput = leftStick.getY();
    	rightInput = rightStick.getY();
    	//leftInput /= 2;
    	//rightInput /=2;
    	driveSystem.tankDrive(leftInput, rightInput);
    	
    	
    	if (xBox.getRawButton(1) && !xBox.getRawButton(2)) {
    		leftSolenoid.set(DoubleSolenoid.Value.kForward);
    		rightSolenoid.set(DoubleSolenoid.Value.kForward);
    	} else if (!xBox.getRawButton(1) && xBox.getRawButton(2)) {
    		leftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    	} else {
    		leftSolenoid.set(DoubleSolenoid.Value.kOff);
    		rightSolenoid.set(DoubleSolenoid.Value.kOff);
    	}
    	// solonoids 4 forward, 3 reverse ||  5 forward 2 reverse || big one 6 forward 1 reverse 
    	
    	if (xBox.getRawButton(3) && !xBox.getRawButton(4)) {
    		centerSolenoid.set(DoubleSolenoid.Value.kForward);
    	} else if (!xBox.getRawButton(3) && xBox.getRawButton(4)) {
    		centerSolenoid.set(DoubleSolenoid.Value.kReverse);
    	} else {
    		centerSolenoid.set(DoubleSolenoid.Value.kOff);
    	}
    } 
}
