package org.usfirst.frc.team3926.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitchControl {
    private final DigitalInput limit; //The limit switch that we are monitoring
    private int madeCheck; //The int to store our debounce count

    //if a Limit Switch is "made", the opening is closed

    public LimitSwitchControl(DigitalInput Limit) {
        this.limit = Limit;
    }
    ////END LimitSwitchControl constructor////

    public boolean getState() {
        boolean made;

        if (limit.get()) {
            ++madeCheck;

            if (madeCheck >= 20) {
                made = true;
            } else {
                made = false;
            }
        } else {
            made = false;
            madeCheck = 0;
        }

        return made;

    }
}
