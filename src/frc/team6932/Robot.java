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
        vars.leftCimCubeMotor.set(0.53); // Oddball motor
        vars.rightCimCubeMotor.set(0.5);
        vars.rightRedlineCubeMotor.set(0.43); // Oddball gearbox?
        vars.leftRedlineCubeMotor.set(0.4);
        vars.rightCimGrabberMotor.set(0.4);
        vars.leftCimGrabberMotor.set(0.4);
        vars.drive.arcadeDrive(vars.forwardAxis, vars.sideAxis);
    }

    public void pickup() {
        vars.leftCimCubeMotor.set(0.53); // Oddball motor
        vars.rightCimCubeMotor.set(0.5);
        vars.rightCimGrabberMotor.set(0.4);
        vars.leftCimGrabberMotor.set(0.4);
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }
}
