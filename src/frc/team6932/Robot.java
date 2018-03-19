/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6932;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import static java.lang.Math.abs;

public class Robot extends TimedRobot {

    // Get storage objects
    private InstanceStorage vars = InstanceStorage.getInstance();
    private CustomFunctions func = CustomFunctions.getInstance();
    //private DashController dash = DashController.getInstance();

    @Override
    public void robotInit() {
        // Start automatic camera capture
        CameraServer.getInstance().startAutomaticCapture();

        // Calibrate gyro
        vars.gyro.calibrate();
    }

    @Override
    public void robotPeriodic() {
        // Update smart dashboard
        //dash.update();
        double angle = vars.gyro.getAngle(); // get current heading
        System.out.println(angle);
    }

    @Override
    public void autonomousInit() {
        func.driveStraight(2);
        //func.turn(180);
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {
        // Create axis deadzone and wait for axis release when cube in robot to prevent accidental shooting
        double cubeAxisValue = vars.cubeControl.getRawAxis(vars.cubeAxis);
        if (abs(cubeAxisValue) > vars.axisDeadzone) {
            if (!vars.waitingForCubeAxisRelease) {
                if (func.cubeInRobot()) {
                    func.setWithCorrections(0, 0, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue);
                } else {
                    func.setWithCorrections(cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, 0, 0);
                    Thread t = new Thread(() -> {
                        try {
                            // Check if cube is in robot after 20ms
                            Thread.sleep(20);
                            if (func.cubeInRobot()) {
                                vars.waitingForCubeAxisRelease = true;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    t.start();
                }
            } else {
                // Waiting for axis release, do not spin motors
                func.setWithCorrections(0, 0, 0, 0, 0, 0);
            }
        } else {
            // Outside of deadzone, do not spin motors
            func.setWithCorrections(0, 0, 0, 0, 0, 0);
            if (vars.waitingForCubeAxisRelease) {
                // Axis has been released
                vars.waitingForCubeAxisRelease = false;
            }
        }
        func.setWithCorrections(cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue);

        // Drive robot
        vars.drive.arcadeDrive(func.ratioValue(vars.driveControl.getRawAxis(vars.verticalDriveAxis)) * -1, func.ratioValue(vars.driveControl.getRawAxis(vars.horizontalDriveAxis)));
    }

    @Override
    public void testPeriodic() {

    }
}
