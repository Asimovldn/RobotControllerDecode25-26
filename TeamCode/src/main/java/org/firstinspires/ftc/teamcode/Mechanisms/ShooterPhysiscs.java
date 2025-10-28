package org.firstinspires.ftc.teamcode.Mechanisms;

public class ShooterPhysiscs
{
    private final static double SLOPE_REGRESSION = 0.0;
    private final static double YINTERCEPT_REGRESSION = 0.0;

    public static double calculateWheelVelocity(double projVelocity)
    {
        return SLOPE_REGRESSION * projVelocity + YINTERCEPT_REGRESSION;
    }

    public static double calculateProjectileVelocity(double wheelVelocity)
    {
        return (wheelVelocity - YINTERCEPT_REGRESSION) / SLOPE_REGRESSION;
    }


}
