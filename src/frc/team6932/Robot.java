/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6932;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;

import static java.lang.Math.abs;

public class Robot extends TimedRobot {

    // Get storage objects
    private InstanceStorage vars = InstanceStorage.getInstance();
    private CustomFunctions func = CustomFunctions.getInstance();
    private DashController dash = DashController.getInstance();

    @Override
    public void robotInit() {
        // Start automatic camera capture
        CameraServer.getInstance().startAutomaticCapture();

        // Reset gyro
        vars.gyro.calibrate();
    }

    @Override
    public void robotPeriodic() {
        // Update smart dashboard
        dash.update();
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {
        // TODO: Autonomous code; get done during 6hr testing period
    }

    @Override
    public void teleopPeriodic() {
        // Create axis deadzone and wait for axis release when cube in robot to prevent accidental shooting
        double cubeAxisValue = vars.cubeControl.getRawAxis(vars.cubeAxis);
        /*if (abs(cubeAxisValue) > vars.axisDeadzone) {
            if (!vars.waitingForCubeAxisRelease) {
                // Not waiting for axis release
                if (!func.cubeInRobot()) {
                    // Cube is not in robot, attempt grabbing
                    func.setWithCorrections(cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, 0, 0);
                    if (func.cubeInRobot()) {
                        // Cube is now in robot, wait for release
                        vars.waitingForCubeAxisRelease = true;
                    }
                } else if (func.cubeInRobot() && !vars.waitingForCubeAxisRelease) {
                    // Cube is in robot and axis has been released, attempt shooting
                    func.setWithCorrections(0, 0, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue);
                }
            } else {
                // Waiting for axis release, do not shoot cube
                func.setWithCorrections(0, 0, 0, 0, 0, 0);
            }
        } else {
            if (vars.waitingForCubeAxisRelease) {
                // Axis has been released
                vars.waitingForCubeAxisRelease = false;
            }
        }*/
        func.setWithCorrections(cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue);

        // Drive robot
        vars.drive.arcadeDrive(vars.driveControl.getRawAxis(vars.verticalDriveAxis) * -1, vars.driveControl.getRawAxis(vars.horizontalDriveAxis));
    }

    @Override
    public void testPeriodic() {

    }
}
