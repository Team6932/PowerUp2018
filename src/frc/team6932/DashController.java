package frc.team6932;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashController {
    private static DashController instance = new DashController();
    private static CustomFunctions func = CustomFunctions.getInstance();
    private static InstanceStorage vars = InstanceStorage.getInstance();

    public SendableChooser autoCommand = new SendableChooser();

    public static DashController getInstance() {
        return instance;
    }

    public void init() {
        autoCommand.addDefault("Position 1", 1);
        autoCommand.addObject("Position 2", 2);
        autoCommand.addObject("Position 3", 3);
        SmartDashboard.putData("Autonomous Selector", autoCommand);
    }

    public void update() {
        //nothin' here, move along
    }
}
