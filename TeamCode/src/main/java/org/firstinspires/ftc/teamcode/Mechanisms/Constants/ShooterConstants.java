package org.firstinspires.ftc.teamcode.Mechanisms.Constants;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Control.FeedForwardCoefficients;
import org.firstinspires.ftc.teamcode.Control.FeedForwardControl;
import org.firstinspires.ftc.teamcode.Control.PIDCoefficients;
import org.firstinspires.ftc.teamcode.Control.PIDControl;

@Config
public class ShooterConstants
{
    public static double ADMISSABLE_VELOCITY_ERROR = Math.toRadians(10); // rad/s
    public static double SHOOTER_ACCEL = Math.toRadians(360); //rad/s^2
    public static double MAX_ANGULAR_VELOCITY = Math.toRadians(720); //rad/s

    public static PIDCoefficients pidCoefficients = new PIDCoefficients(0.001,0.000001,0.0001);
    public static FeedForwardCoefficients ffCoefficients = new FeedForwardCoefficients(0.042,0.055,0.0001);
}
