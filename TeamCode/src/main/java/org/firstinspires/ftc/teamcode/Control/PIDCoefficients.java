package org.firstinspires.ftc.teamcode.Control;

public class PIDCoefficients
{
    public double kp;
    public double ki;
    public double kd;

    public PIDCoefficients(double kp, double ki, double kd)
    {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

}
