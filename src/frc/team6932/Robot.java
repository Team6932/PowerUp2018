/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6932;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;

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
        vars.gyro.reset();

        // Init dashboard
        dash.init();
    }

    @Override
    public void robotPeriodic() {
        // Update smart dashboard
        dash.update();
    }

    @Override
    public void autonomousInit() {
        // One-Time Autonomous Program
        if(dash.autoCommand.getSelected() == 3) {
            func.driveStraight(4);
            if(func.getOwnership(0) == vars.RIGHT) {
                func.turn(-75);
                func.driveStraight(-1);
                func.throwCube(false);
            }
        } else if(dash.autoCommand.getSelected() == 2) {
            // Starting backwards
            func.driveStraight(-0.5);
            if(func.getOwnership(0) == vars.RIGHT) {
                func.turn(-30);
                func.driveStraight(-1.25);
                func.turn(30);
                func.driveStraight(-1);
                func.throwCube(false);
            } else if(func.getOwnership(0) == vars.LEFT) {
                func.turn(30);
                func.driveStraight(-1.25);
                func.turn(-30);
                func.driveStraight(-1);
                func.throwCube(false);
            }
        } else if(dash.autoCommand.getSelected() == 1) {
            func.driveStraight(4);
            if(func.getOwnership(0) == vars.LEFT) {
                func.turn(75);
                func.driveStraight(-1);
                func.throwCube(false);
            }
        }
    }

    @Override
    public void autonomousPeriodic() {
        // Repeatedly set motors to 0
        vars.drive.arcadeDrive(0, 0);
    }

    @Override
    public void teleopPeriodic() {
        // Cube control
        double cubeAxisValue = vars.cubeControl.getRawAxis(vars.cubeAxis);
        func.setWithCorrections(cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue, cubeAxisValue);

        // Drive robot
        vars.drive.arcadeDrive(func.ratioValue(vars.driveControl.getRawAxis(vars.verticalDriveAxis)) * -1, func.ratioValue(vars.driveControl.getRawAxis(vars.horizontalDriveAxis)));
    }
}
