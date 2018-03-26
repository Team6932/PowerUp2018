/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6932;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.networktables.*;

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

        // Initialize dashboard
        dash.init();
    }

    @Override
    public void robotPeriodic() {
        // Update smart dashboard
        dash.update();
        System.out.println(dash.autoCommand.getSelected());
    }

    @Override
    public void autonomousInit() {

        // One-Time Autonomous Program
        vars.selectedPos = dash.autoCommand.getSelected();
        if (vars.selectedPos.equals(3)) {
            if (func.getOwnership(0) == vars.RIGHT) {
                func.driveStraight(1);
                func.turn(-90);
                double currentTime = System.currentTimeMillis();
                double end = currentTime + (vars.throwSeconds * 1000);
                while (System.currentTimeMillis() < end) {
                    func.setWithCorrections(1, 1, 1, 1, 1, 1);
                }
            } else if (func.getOwnership(0) == vars.LEFT) {
                //TODO
            }
        } else if (vars.selectedPos.equals(2)) {
            if (func.getOwnership(0) == vars.RIGHT) {
                func.driveStraight(0.5);
                func.turn(180 + 35); // Flip backwards
                func.driveStraight(-2);
                func.turn(-35);
                func.driveStraight(-1);
                double currentTime = System.currentTimeMillis();
                double end = currentTime + (vars.throwSeconds * 1000);
                while (System.currentTimeMillis() < end) {
                    func.setWithCorrections(1, 1, 1, 1, 1, 1);
                }
            } else if (func.getOwnership(0) == vars.LEFT) {
                //TODO
            }
        } else if (vars.selectedPos.equals(1)) {
            //TODO
        }
    }

    @Override
    public void autonomousPeriodic() {
        // Repeatedly set motors to 0
        vars.drive.arcadeDrive(0, 0);
        func.setWithCorrections(0, 0, 0, 0, 0, 0);
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
