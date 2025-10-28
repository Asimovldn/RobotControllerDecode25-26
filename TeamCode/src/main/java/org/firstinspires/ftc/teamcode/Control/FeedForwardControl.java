package org.firstinspires.ftc.teamcode.Control;

public class FeedForwardControl
{
    private double kv;
    private double ka;
    private double ks;

    public FeedForwardControl(double kv, double ka, double ks)
    {
        this.kv = kv;
        this.ka = ka;
        this.ks = ks;
    }

    public double calculate(double velocitySetPoint, double accelSetPoint)
    {
        return velocitySetPoint * kv + accelSetPoint * ka + ks;
    }
}
