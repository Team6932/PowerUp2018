package frc.team6932;

import edu.wpi.first.wpilibj.DriverStation;

public class CustomFunctions {
    private static CustomFunctions instance = new CustomFunctions();
    private static InstanceStorage vars = InstanceStorage.getInstance();

    public static CustomFunctions getInstance() {
        return instance;
    }

    // Non-blocking functions

    // Send to grabber motors with corrections for derp motors
    public void setWithCorrections(double leftCimGrabber, double rightCimGrabber, double leftCimCube, double rightCimCube, double leftRedlineCube, double rightRedlineCube) {
        vars.leftCimGrabberMotor.set(0.55 * leftCimGrabber);
        vars.rightCimGrabberMotor.set(0.55 * rightCimGrabber);
        vars.leftCimCubeMotor.set(0.55 * leftCimCube);
        vars.rightCimCubeMotor.set((0.55 * rightCimCube) * 1.3); // Oddball motor
        vars.leftRedlineCubeMotor.set(0.5 * leftRedlineCube);
        vars.rightRedlineCubeMotor.set((0.5 * rightRedlineCube) * 1.1); // Oddball motor/gearbox
    }

    // Adjust joystick values for slider
    public double ratioValue(double val) {
        return val * (((vars.driveControl.getRawAxis(vars.ratioAxis) * -1) + 1) / 2);
    }

    // Get ownership from driver station
    public int getOwnership(int position) {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            if(gameData.charAt(position) == 'R') {
                return vars.RIGHT;
            } else if(gameData.charAt(position) == 'L') {
                return vars.LEFT;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    // Blocking functions

    // Drive in a straight line and correct for being rammed
    public void driveStraight(double distanceMeters) {
        vars.gyro.reset(); // Set gyro heading to 0
        double currentTime = System.currentTimeMillis();
        double end = currentTime + ((distanceMeters / vars.metersPerSecond) * 1000);
        // Run for amount of seconds it takes to go distance
        while(System.currentTimeMillis() < end) {
            setWithCorrections(0, 0, 0, 0, 0, 0);
            double angleOffset = vars.gyro.getAngle();
            // Cap desired angle for efficiency
            while(angleOffset > 180) {
                angleOffset -= 360;
            }
            while(angleOffset < -180) {
                angleOffset += 360;
            }
            // Cap correction speed
            double rotationNeeded = angleOffset * vars.driveKp;
            if(rotationNeeded > vars.turnSpeed) {
                rotationNeeded = vars.turnSpeed;
            }
            if(rotationNeeded < -vars.turnSpeed) {
                rotationNeeded = -vars.turnSpeed;
            }
            vars.drive.arcadeDrive(vars.driveSpeed, rotationNeeded); // Drive straight forward
        }
        vars.drive.arcadeDrive(0, 0);
    }

    // Turn and correct for running over stuff or different floor materials
    public void turn(double rotationDegrees) {
        vars.gyro.reset(); //Set gyro heading to 0
        int sensitivity = 0;
        while(true) {
            setWithCorrections(0, 0, 0, 0, 0, 0);
            double angleOffset = vars.gyro.getAngle();
            System.out.println("angleOffset " + angleOffset);
            System.out.println("angleMinusDegrees " + String.valueOf(angleOffset - rotationDegrees));
            System.out.println("rotationNeeded " + (angleOffset - rotationDegrees) * vars.turnKp);
            double rotationNeeded = (angleOffset - rotationDegrees) * vars.turnKp;
            // Cap rotation speed value
            if(rotationNeeded > vars.turnSpeed) {
                rotationNeeded = vars.turnSpeed;
            }
            if(rotationNeeded < -vars.turnSpeed) {
                rotationNeeded = -vars.turnSpeed;
            }
            vars.drive.arcadeDrive(0, rotationNeeded); // Turn towards desired heading
            // Wait to make sure angle is stabilized
            if(angleOffset > (rotationDegrees - (vars.turnTolerance / 2)) && angleOffset < (rotationDegrees + (vars.turnTolerance / 2))) {
                sensitivity += 1;
                try {
                    Thread.sleep(1);
                } catch(Exception e) {
                }
                System.out.println(sensitivity);
                if(sensitivity > vars.turnSensitivity) {
                    break;
                }
            } else {
                sensitivity = 0;
            }
        }
        // Set motors to zero
        vars.drive.arcadeDrive(0, 0);
    }

    public void throwCube(boolean forward) {
        int power = 1;
        if(forward) {
            power = -1;
        }
        double currentTime = System.currentTimeMillis();
        double end = currentTime + (vars.throwSeconds * 1000);
        while (System.currentTimeMillis() < end) {
            setWithCorrections(power, power, power, power, power, power);
        }
        setWithCorrections(0, 0, 0, 0, 0, 0);
    }
}