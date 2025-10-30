package org.firstinspires.ftc.teamcode.Control;

public class FeedForwardControl
{
    private double kv;
    private double ka;
    private double ks;

    public FeedForwardControl(FeedForwardCoefficients ffCoefficients)
    {
        this.kv = ffCoefficients.kv;
        this.ka = ffCoefficients.ka;
        this.ks = ffCoefficients.ks;
    }

    public double calculate(double velocitySetPoint, double accelSetPoint, int dir)
    {
        return velocitySetPoint * kv + accelSetPoint * ka + ks * dir;
    }
}
