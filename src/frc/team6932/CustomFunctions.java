package frc.team6932;

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

    // Check if cube in robot
    public boolean cubeInRobot() {
        vars.cubeDetector.ping();
        return vars.cubeDetector.getRangeInches() < vars.cubeThreshold;
    }

    public double ratioValue(double val) {
        return val * (((vars.driveControl.getRawAxis(vars.ratioAxis) * -1) + 1) / 2);
    }

    // Blocking functions

    // Drive in a straight line and correct for being rammed
    public void driveStraight(double distanceMeters) {
        vars.gyro.reset(); // Set gyro heading to 0
        double currentTime = System.currentTimeMillis();
        double end = currentTime + ((distanceMeters / vars.metersPerSecond) * 1000);
        // Run for amount of seconds it takes to go distance
        while(System.currentTimeMillis() < end) {
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
        boolean continueTurning = true;
        int sensitivity = 0;
        while(true) {
            double angleOffset = vars.gyro.getAngle();
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
            if(angleOffset > (rotationDegrees - vars.turnTolerance) && angleOffset < (rotationDegrees + vars.turnTolerance)) {
                sensitivity += 1;
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
}