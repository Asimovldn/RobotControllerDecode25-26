package org.firstinspires.ftc.teamcode.Control;

public class PIDControl
{
    private double kp;
    private double ki;
    private double kd;

    private double proportional = 0;
    private double integral = 0;
    private double derivative = 0;

    private double lastError = 0;

    public PIDControl(PIDCoefficients pidCoefficients)
    {
        this.kp = pidCoefficients.kp;
        this.ki = pidCoefficients.ki;
        this.kd = pidCoefficients.kd;
    }

    public void resetIntegral()
    {
        integral = 0;
    }

    public double calculate(double reference, double setPoint)
    {
        double error = setPoint - reference;

        proportional = error;

        integral += error;

        derivative = error - lastError;

        lastError = error;

        return proportional * kp + integral * ki + derivative * kd;
    }
}
