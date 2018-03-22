package frc.team6932;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashController {
    private static DashController instance = new DashController();
    private static CustomFunctions func = CustomFunctions.getInstance();
    private static InstanceStorage vars = InstanceStorage.getInstance();

    public static DashController getInstance() {
        return instance;
    }

    public void update() {
        SmartDashboard.putBoolean("Cube in Robot", func.cubeInRobot());
    }
}
