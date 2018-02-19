package frc.team6932;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class InstanceStorage {

    private static InstanceStorage instance = new InstanceStorage();

    // Sensors
    Ultrasonic cubeDetector = new Ultrasonic(9, 8);
    PowerDistributionPanel pdp = new PowerDistributionPanel(0);
    AnalogGyro gyro = new AnalogGyro(1);

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

    // Configuration
    public double axisDeadzone = 0.1; // Axis deadzone for controllers/joysticks
    public double cubeThreshhold = 12; // Threshold in inches for cube detection
    public Joystick cubeControl = controller; // Joystick to use for controlling cube motors
    public Joystick driveControl = joystick; // Joystick to use for controlling drive motors
    public int cubeAxis = 1;
    public int horizontalDriveAxis = 2;
    public int verticalDriveAxis = 1;
    public int ratioAxis = 3;

    // Global variable storage; should not be adjusted
    public boolean waitingForCubeAxisRelease = false;

    public static InstanceStorage getInstance() {
        return instance;
    }

}
