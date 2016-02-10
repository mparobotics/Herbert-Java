package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;


public class DriveFunctions {
    Joystick leftStick;
    double leftInput;
    Joystick rightStick;
    double rightInput;


    RobotDrive driveControl;

        public DriveFunctions(Joystick leftStick, Joystick rightStick, RobotDrive driveSystem){
            this.leftStick = leftStick;
            this.rightStick = rightStick;
            driveControl = driveSystem;
        }
        public void masterFunction(){
            leftInput = leftStick.getY();
            rightInput = rightStick.getY();

            driveControl.tankDrive(leftInput, rightInput);

            if (rightStick.getRawButton(1)){
                leftInput = rightInput;
            }

            if (leftStick.getRawButton(1)){
                leftInput /= 2;
                rightInput /= 2;
            }
        }


}


