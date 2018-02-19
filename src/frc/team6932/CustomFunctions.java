package frc.team6932;

public class CustomFunctions {
    private static CustomFunctions instance = new CustomFunctions();
    private static InstanceStorage vars = InstanceStorage.getInstance();

    public static CustomFunctions getInstance() {
        return instance;
    }

    public void setWithCorrections(double leftCimGrabber, double rightCimGrabber, double leftCimCube, double rightCimCube, double leftRedlineCube, double rightRedlineCube) {
        vars.leftCimGrabberMotor.set(0.4 * leftCimGrabber);
        vars.rightCimGrabberMotor.set(0.4 * rightCimGrabber);
        vars.leftCimCubeMotor.set(0.4 * leftCimCube);
        vars.rightCimCubeMotor.set((0.4 * rightCimCube) * 1.3); // Oddball motor
        vars.leftRedlineCubeMotor.set(0.4 * leftRedlineCube);
        vars.rightRedlineCubeMotor.set((0.4 * rightRedlineCube) * 1.1); // Oddball motor/gearbox
    }

    public boolean cubeInRobot() {
        System.out.println(vars.cubeDetector.getRangeInches());
        return vars.cubeDetector.getRangeInches() < vars.cubeThreshhold;
    }

    public double ratioValue(double val) {
        return val - ((vars.driveControl.getRawAxis(vars.ratioAxis) + 1) / 2);
    }
}
