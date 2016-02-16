
package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;


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
   
    public void robotInit() {
       talon_FL = new Talon(0);
       talon_BL = new Talon(1);
       talon_FR = new Talon(2);
       talon_BR = new Talon(3);
       driveSystem = new RobotDrive(talon_FL, talon_BL, talon_FR, talon_BR);
       leftStick = new Joystick(0);
       rightStick = new Joystick(1);
    
    }
   
    public void teleopPeriodic() {
    	leftInput = leftStick.getY();
    	rightInput = rightStick.getY();
    	
    	driveSystem.tankDrive(leftInput, rightInput);
    	
    	 	if (rightStick.getRawButton(1)){
    	 		leftInput = rightInput;
    	 	}
    	 	if (leftStick.getRawButton(1)){
    	 		leftInput /= 2;
    	 		rightInput /= 2;
    	 	}
    }
    
 
  
}
