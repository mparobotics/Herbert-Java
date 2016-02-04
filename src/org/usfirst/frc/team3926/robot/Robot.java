
package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
	
	
public class Robot extends IterativeRobot {
	Talon talon_FL;
	Talon talon_BL;
	Talon talon_FR;
    Talon talon_BR;
    
    RobotDrive driveSystem;
    Talon armWheels;
    Talon mysteryTalon; // talon of unknown origin 
    
    Joystick leftStick;
    double leftInput;
    Joystick rightStick;
    double rightInput;
    
    Compressor compressor;
    DoubleSolenoid mainLift; //The main giant cylinder
    DoubleSolenoid sideLiftR; //The right side cylinder
    DoubleSolenoid sideLiftL; //The left side cylinder 
    
    final int ID = 1; //ID number of the PCM (pneumatics control module)
    final int liftForward = 5; //These need to be the channel numbers on the PCM (only like this so we can write other code)
    final int liftReverse = 2;
    final int rSideForward = 4;
    final int rSideReverse = 3;
    final int lSideForward = 6;
    final int lSideReverse = 1;
	
    public void robotInit() {
    	talon_FL = new Talon(0);
    	talon_BL = new Talon(1);
    	talon_FR = new Talon(2);
    	talon_BR = new Talon(3);
    	driveSystem = new RobotDrive(talon_FL, talon_BL, talon_FR, talon_BR);
    	armWheels = new Talon(8);
    	
    	leftStick = new Joystick(0);
    	rightStick = new Joystick(1);
    	
    	compressor = new Compressor(ID);
    	compressor.setClosedLoopControl(true);
    	mainLift = new DoubleSolenoid(ID, liftForward, liftReverse);
    	sideLiftR = new DoubleSolenoid(ID, rSideForward, rSideReverse);
    	sideLiftL = new DoubleSolenoid(ID, lSideForward, lSideReverse);
    } 
    ////End robotInit()////

    public void autonomousPeriodic() {

    } 
    ////End autonomousPeriodic()////

    public void teleopPeriodic() {
        rightInput = rightStickReturn();
        leftInput = leftStickReturn();
        
        if (leftStick.getRawButton(1)) rightInput = leftInput;
        
        driveSystem.tankDrive(leftInput, rightInput);
        
        if (leftStick.getRawButton(2)) armWheels.set(1);
        else if (leftStick.getRawButton(3)) armWheels.set(-1);
        else armWheels.set(0);
        
<<<<<<< HEAD
        
        if (rightStick.getRawButton(1)) solenoidControl(DoubleSolenoid.Value.kForward); //TODO implement the limit switch
        else if (leftStick.getRawButton(1)) {
        	mainLift.set(DoubleSolenoid.Value.kForward);
        	SmartDashboard.putBoolean("BigLift", true);
        }
        else if (rightStick.getRawButton(2)) solenoidControl(DoubleSolenoid.Value.kReverse); //TODO implement the limit switch
        else solenoidControl(DoubleSolenoid.Value.kOff);
=======
        //if (rightStick.getRawButton(1)) solenoidControl(DoubleSolenoid.Value.kForward); //TODO impliment the limit switch
        //if (rightStick.getRawButton(2)) solenoidControl(DoubleSolenoid.Value.kReverse); //TODO impliment the limti switch
        //else solenoidControl(DoubleSolenoid.Value.kOff);
>>>>>>> origin/master
    }  
    ////End teleopPeriodic()////
    /**
     * @param value: The value to set all solenoids to (forward, reverse, or off);
     */
   public void solenoidControl(Value value) {
    	//mainLift.set(value);
    	sideLiftR.set(value);
    	sideLiftL.set(value);
    }
    ////End solenoidControl()////
    
    int debounceCounter = 0;
    /**
     * @param limitSwitch: The name of the limit switch which we are looking at
     * @param joystick: The name of the joystick who's button we will check
     * @param button: the button on the joystick which we will check
     * @return If true, the limit switch is actually pressed and the joystick is actually pressed
     */
    public boolean debounceLimit(DigitalInput limitSwitch, Joystick joystick, int button) {
    	boolean check = false;
    	
    	if (joystick.getRawButton(button)) {
    		if (limitSwitch.get()) ++debounceCounter;
    		else debounceCounter = 0;
    		
    		if (debounceCounter > 20) check = true;
    		else check = false;
    	}
    	else {
    		check = false;
    		debounceCounter = 0;
    	}
    	
    	return check;
    }
    ////End debounceLimit()////
    public double leftStickReturn() {return leftStick.getY() * -1;}
    ////End leftStickReturn()////
    
    public double rightStickReturn() {return rightStick.getY() * -1;}
    ////End rightStickReturn()////
} 
////End Robot////