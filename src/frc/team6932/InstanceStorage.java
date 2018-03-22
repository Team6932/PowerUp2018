package frc.team6932;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class InstanceStorage {

    private static InstanceStorage instance = new InstanceStorage();

    // Sensors
    Ultrasonic cubeDetector = new Ultrasonic(9, 8);
    Gyro gyro = new ADXRS450_Gyro();

    // Motor controllers
    public Spark leftDrive = new Spark(0);
    public Spark rightDrive = new Spark(1);
    public Spark rightCimCubeMotor = new Spark(8);
    public Spark leftCimCubeMotor = new Spark(9);
    public Spark rightRedlineCubeMotor = new Spark(6);
    public Spark leftRedlineCubeMotor = new Spark(7);
    public PWMTalonSRX rightCimGrabberMotor = new PWMTalonSRX(5);
    public PWMTalonSRX leftCimGrabberMotor = new PWMTalonSRX(4);
    public DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

    // Joystick/Controller
    public Joystick joystick = new Joystick(0);
    public Joystick controller = new Joystick(1);

    // Misc. Configuration
    public double cubeThreshold = 12; // Threshold in inches for cube detection

    // Controller Configuration
    public Joystick cubeControl = controller;
    public Joystick driveControl = joystick;
    public int cubeAxis = 1;
    public int horizontalDriveAxis = 2;
    public int verticalDriveAxis = 1;
    public int ratioAxis = 3;

    // Motor configuration
    double driveKp = 0.1;
    double turnKp = 0.07;
    double metersPerSecond = 1.5;
    double driveSpeed = 0.75;
    double turnSpeed = 0.75;
    double turnTolerance = 3;
    double turnSensitivity = 100;

    public static InstanceStorage getInstance() {
        return instance;
    }

}