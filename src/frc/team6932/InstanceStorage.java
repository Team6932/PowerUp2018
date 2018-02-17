package frc.team6932;

import edu.wpi.first.wpilibj.Joystick;
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
    public Spark rightCimCubeMotors = new Spark(8);
    public Spark leftCimCubeMotors = new Spark(9);
    public Spark redlineCubeMotors = new Spark(3);
    public DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

    // Joystick
    public Joystick joystick = new Joystick(0);
    public Joystick controller = new Joystick(1);
    public double sideAxis = joystick.getRawAxis(0);
    public double forwardAxis = joystick.getRawAxis(1);

    // Sensor variables
    PowerDistributionPanel pdp = new PowerDistributionPanel();

    public static InstanceStorage getInstance() {
        return instance;
    }

}
