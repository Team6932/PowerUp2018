/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6932;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    // Get the Instance Storage Object
    private InstanceStorage vars = InstanceStorage.getInstance();
    private CustomFunctions func = CustomFunctions.getInstance();

    // Other
    private final String RED = "Red";
    private final String BLUE = "Blue";

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        vars.colorChooser.addDefault("Red", RED);
        vars.colorChooser.addObject("Blue", BLUE);
        // Init camera
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    public void robotPeriodic() {
        // Smart Dashboard
        SmartDashboard.putBoolean("Cube in Robot", true);
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendableCameraServer.getInstance().startAutomaticCapture();
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     * <p>
     * <p>You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        vars.autoColor = vars.colorChooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector",
        // defaultAuto);
        System.out.println("Color selected: " + vars.colorChooser);
    }

    /**
     * This function is called periodically during autonomous.
     */

    @Override
    public void autonomousPeriodic() {
        switch (vars.autoColor) {
            case RED:
                // Put custom auto code here
                break;
            case BLUE:
                break;
            default:
                // Put default auto code here
                break;
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        // Create axis deadzone and wait for axis release when cube in robot to prevent accidental shooting
        if (vars.cubeAxis > vars.axisDeadzone) {
            if (!vars.waitingForCubeAxisRelease) {
                // Not waiting for axis release
                if (!func.cubeInRobot()) {
                    // Cube is not in robot, attempt grabbing
                    func.setWithCorrections(vars.cubeAxis, vars.cubeAxis, vars.cubeAxis, vars.cubeAxis, 0, 0);
                    if (func.cubeInRobot()) {
                        // Cube is now in robot, wait for release
                        vars.waitingForCubeAxisRelease = true;
                    }
                } else if (func.cubeInRobot() && !vars.waitingForCubeAxisRelease) {
                    // Cube is in robot and axis has been released, attempt shooting
                    func.setWithCorrections(0, 0, vars.cubeAxis, vars.cubeAxis, vars.cubeAxis, vars.cubeAxis);
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
        }

        // Drive robot
        vars.drive.arcadeDrive(vars.verticalAxis, vars.horizontalAxis);
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }
}
