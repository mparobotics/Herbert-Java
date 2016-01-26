
package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
    Talon armWheels;
    Talon mysteryTalon; //This is hooked up but idk what it is supposed to go to
    
    Joystick leftStick;
    double leftInput;
    Joystick rightStick;
    double rightInput;
    
    DoubleSolenoid mainLift; //The main giant cylinder
    DoubleSolenoid sideLiftR; //The right side cylinder
    DoubleSolenoid sideLiftL; //The left side cylinder
    
    final int ID = 0; //ID number of the PCM (pnuematics control moduel)
    final int liftForward = 0; //These need to be the channel numbers on the PCM (only like this so we can write other code)
    final int liftReverse = 0;
    final int rSideForward = 0;
    final int rSideReverse = 0;
    final int lSideForward = 0;
    final int lSideReverse = 0;
	
    public void robotInit() {
    	talon_FL = new Talon(0);
    	talon_BL = new Talon(1);
    	talon_FR = new Talon(2);
    	talon_BR = new Talon(3);
    	driveSystem = new RobotDrive(talon_FR, talon_BR, talon_FR, talon_BR);
    	armWheels = new Talon(9);
    	mysteryTalon = new Talon(5);
    	
    	leftStick = new Joystick(0);
    	rightStick = new Joystick(1);
    	
    	mainLift = new DoubleSolenoid(ID, liftForward, liftReverse);
    	sideLiftR = new DoubleSolenoid(ID, rSideForward, rSideReverse);
    	sideLiftL = new DoubleSolenoid(ID, lSideForward, lSideReverse);
    } 
    ////End robotInit()////

    public void autonomousPeriodic() {

    } 
    ////End autonomousPeriodic()////

    public void teleopPeriodic() {
        if (rightStickReturn() < .6) rightInput = rightStickReturn()*rightStickReturn();
        if (leftStickReturn() < .6) leftInput = leftStickReturn()*leftStickReturn();
        
        if (leftStick.getRawButton(1)) rightInput = leftInput;
        
        driveSystem.tankDrive(leftInput, rightInput);
    }  
    ////End teleopPeriodic()////
    
    public double leftStickReturn() {return leftStick.getY();}
    ////End leftStickReturn()////
    
    public double rightStickReturn() {return rightStick.getY();}
    ////End rightStickReturn()////
} 
////End Robot////