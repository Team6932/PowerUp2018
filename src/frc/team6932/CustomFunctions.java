package frc.team6932;

public class CustomFunctions {
    private static CustomFunctions instance = new CustomFunctions();
    private static InstanceStorage vars = InstanceStorage.getInstance();

    public static CustomFunctions getInstance() {
        return instance;
    }

    public void setWithCorrections(double leftCimGrabber, double rightCimGrabber, double leftCimCube, double rightCimCube, double leftRedlineCube, double rightRedlineCube) {
        vars.leftCimGrabberMotor.set(0.55 * leftCimGrabber);
        vars.rightCimGrabberMotor.set(0.55 * rightCimGrabber);
        vars.leftCimCubeMotor.set(0.55 * leftCimCube);
        vars.rightCimCubeMotor.set((0.55 * rightCimCube) * 1.3); // Oddball motor
        vars.leftRedlineCubeMotor.set(0.5 * leftRedlineCube);
        vars.rightRedlineCubeMotor.set((0.5 * rightRedlineCube) * 1.1); // Oddball motor/gearbox
    }

    public boolean cubeInRobot() {
        vars.cubeDetector.ping();
        return vars.cubeDetector.getRangeInches() < vars.cubeThreshold;
    }

    public double ratioValue(double val) {
        return val * (((vars.driveControl.getRawAxis(vars.ratioAxis) * -1) + 1) / 2);
    }
}
