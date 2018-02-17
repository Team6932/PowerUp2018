package frc.team6932;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class InstanceStorage {

    private static InstanceStorage instance = new InstanceStorage();

    // GUI variables
    public String autoColor;
    public SendableChooser<String> colorChooser = new SendableChooser<>();

    // Motor controllers
    public Spark leftDrive = new Spark(0);
    public Spark rightDrive = new Spark(1);
    public Spark rightCimCubeMotor = new Spark(9);
    public Spark leftCimCubeMotor = new Spark(8);
    public Spark rightRedlineCubeMotor = new Spark(6);
    public Spark leftRedlineCubeMotor = new Spark(7);
    public PWMTalonSRX rightCimGrabberMotor = new PWMTalonSRX(5);
    public PWMTalonSRX leftCimGrabberMotor = new PWMTalonSRX(4);
    public DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

    // Joystick
    public Joystick joystick = new Joystick(0);
    public Joystick controller = new Joystick(1);
    public double sideAxis = joystick.getRawAxis(0);
    public double forwardAxis = joystick.getRawAxis(1);

    // Sensor variables
    PowerDistributionPanel pdp = new PowerDistributionPanel(0);

    public static InstanceStorage getInstance() {
        return instance;
    }

}
