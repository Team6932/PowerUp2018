package frc.team6932;

public class CustomFunctions {
    private static CustomFunctions instance = new CustomFunctions();
    private static InstanceStorage vars = InstanceStorage.getInstance();

    public void setWithCorrections(double leftCimGrabber, double rightCimGrabber, double leftCimCube, double rightCimCube, double leftRedlineCube, double rightRedlineCube) {
        vars.leftCimGrabberMotor.set(0.4 * leftCimGrabber);
        vars.rightCimGrabberMotor.set(0.4 * rightCimGrabber);
        vars.leftCimCubeMotor.set((0.5 * leftCimCube) + 0.03); // Oddball motor
        vars.rightCimCubeMotor.set(0.5 * rightCimCube);
        vars.leftRedlineCubeMotor.set(0.4 * leftRedlineCube);
        vars.rightRedlineCubeMotor.set((0.4 * rightRedlineCube) + 0.03); // Oddball motor/gearbox
    }

    public boolean cubeInRobot() {
        return vars.cubeDetector.getRangeInches() < vars.cubeThreshhold;
    }

    public static CustomFunctions getInstance() {
        return instance;
    }
}
