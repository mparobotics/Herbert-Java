
package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
    
    Joystick leftStick;
    Joystick rightStick;

    DriveFunctions driveControl;

    DigitalInput topLimitSwitch;
    DigitalInput botLimitSwitch;
    LimitSwitchControl topLimit;
    LimitSwitchControl botLimit;


    Compressor compressor;
    DoubleSolenoid mainLift; //The main giant cylinder
    DoubleSolenoid sideLiftR; //The right side cylinder
    DoubleSolenoid sideLiftL; //The left side cylinder 

    public void robotInit() {
    	talon_FL = new Talon(VariableStore.TALON_FRONT_LEFT);
    	talon_BL = new Talon(VariableStore.TALON_BACK_LEFT);
    	talon_FR = new Talon(VariableStore.TALON_FRONT_RIGHT);
    	talon_BR = new Talon(VariableStore.TALON_BACK_RIGHT);
    	driveSystem = new RobotDrive(talon_FL, talon_BL, talon_FR, talon_BR);

    	armWheels = new Talon(VariableStore.TALON_ARM_WHEEL_MOTOR);
    	
    	leftStick = new Joystick(VariableStore.LEFT_JOYSTICK_ID);
    	rightStick = new Joystick(VariableStore.RIGHT_JOYSTICK_ID);

        botLimitSwitch = new DigitalInput(VariableStore.BOTTOM_LIMIT);
        topLimitSwitch = new DigitalInput(VariableStore.TOP_LIMIT);

    	topLimit = new LimitSwitchControl(topLimitSwitch);
    	botLimit = new LimitSwitchControl(botLimitSwitch);

    	mainLift = new DoubleSolenoid(VariableStore.PCM_ID, VariableStore.MAIN_FORWARD_LIFT,
                VariableStore.MAIN_REVERSE_LIFT);
    	sideLiftR = new DoubleSolenoid(VariableStore.PCM_ID, VariableStore.RIGHT_SIDE_FORWARD_LIFT,
                VariableStore.RIGHT_SIDE_REVERSE_LIFT);
    	sideLiftL = new DoubleSolenoid(VariableStore.PCM_ID, VariableStore.LEFT_SIDE_FORWARD_LIFT,
                VariableStore.LEFT_SIDE_REVERSE_LIFT);
        compressor = new Compressor(VariableStore.PCM_ID);
        compressor.setClosedLoopControl(true);
    } 
    ////End robotInit()////

    public void autonomousPeriodic() {

    } 
    ////End autonomousPeriodic()////

    public void teleopPeriodic() {


        driveControl.masterFunction();
        
        if (rightStick.getRawButton(1)) {
            sideLifts(DoubleSolenoid.Value.kForward);
        } else if (rightStick.getRawButton(4)) {
            sideLifts(DoubleSolenoid.Value.kReverse);
        } else if (leftStick.getRawButton(1) && !topLimit.getState()) {
            mainLift.set(DoubleSolenoid.Value.kForward);
        } else if (leftStick.getRawButton(4) && !botLimit.getState()) {
            mainLift.set(DoubleSolenoid.Value.kReverse);
        } else  {
        	sideLifts(DoubleSolenoid.Value.kOff);
        	mainLift.set(DoubleSolenoid.Value.kOff);
        }
        

    }  
    ////End teleopPeriodic()////
    /**
     * @param value: The value to set all solenoids to (forward, reverse, or off);
     */
   public void sideLifts(Value value) {
    	sideLiftR.set(value);
    	sideLiftL.set(value);
    }
    ////End sideLifts()////

    public double leftStickReturn() {return leftStick.getY() * -1;}
    ////End leftStickReturn()////
    
    public double rightStickReturn() {return rightStick.getY() * -1;}
    ////End rightStickReturn()////
} 
////End Robot////