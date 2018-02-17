/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6932;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    // GUI variables
    private String autoColor;
    private SendableChooser<String> colorChooser = new SendableChooser<>();
    private final String RED = "Red";
    private final String BLUE = "Blue";

    // Motor controllers
    private Spark leftDrive = new Spark(0);
    private Spark rightDrive = new Spark(1);
    private Spark rightCimCubeMotors = new Spark(8);
    private Spark leftCimCubeMotors = new Spark(9);
    private Spark redlineCubeMotors = new Spark(3);
    private DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

    // Joystick
    private Joystick joystick = new Joystick(0);
    private Joystick controller = new Joystick(1);
    private double sideAxis = joystick.getRawAxis(0);
    private double forwardAxis = joystick.getRawAxis(1);

    // Sensor variables
    PowerDistributionPanel pdp = new PowerDistributionPanel();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        colorChooser.addDefault("Red", RED);
        colorChooser.addObject("Blue", BLUE);
        SmartDashboard.putData("Auto Colors", colorChooser);

        // Init camera
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    public void robotPeriodic() {
        // Smart Dashboard
        SmartDashboard.putBoolean("Cube in Robot: ", true);
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
        autoColor = colorChooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector",
        // defaultAuto);
        System.out.println("Color selected: " + colorChooser);
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        switch (autoColor) {
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
        rightCimCubeMotors.set(forwardAxis);
        leftCimCubeMotors.set(forwardAxis);
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }
}
