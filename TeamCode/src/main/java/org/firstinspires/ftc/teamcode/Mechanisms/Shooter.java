package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Control.FeedForwardControl;
import org.firstinspires.ftc.teamcode.Control.PIDControl;
import org.firstinspires.ftc.teamcode.Mechanisms.Constants.ShooterConstants;

public class Shooter
{
    private DcMotorEx motorShooter;

    private VoltageSensor voltageSensor;

    private double targetVelocity;
    private double initialVelocity;
    private ElapsedTime accelTimer;
    private double timeToAccel;

    private double velocitySetPoint;
    private double accelSetPoint;


    public void init(HardwareMap hardwareMap)
    {
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        accelTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        // TODO Arrumar o nome do motor
        motorShooter = hardwareMap.get(DcMotorEx.class, "motor_shooter");

        motorShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //TODO Arrumar a direção de tal forma que valor positivo lance o projétil
        motorShooter.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void setPower(double power)
    {
        motorShooter.setPower(power);
    }

    public void setShooterAngularVelocity(double angularVelocity)
    {
        initialVelocity = targetVelocity;
        targetVelocity = angularVelocity;
        timeToAccel = Math.abs(targetVelocity - initialVelocity) / ShooterConstants.SHOOTER_ACCEL;
        accelTimer.reset();
    }

    public void updateShooter()
    {
        double currVelocity = motorShooter.getVelocity(AngleUnit.RADIANS);
        double dir = Math.signum(targetVelocity - initialVelocity);


        if (!isBusy()) {
            velocitySetPoint = targetVelocity;
            accelSetPoint = 0;
        } else {
            velocitySetPoint = initialVelocity + dir * ShooterConstants.SHOOTER_ACCEL * accelTimer.time();
            accelSetPoint = ShooterConstants.SHOOTER_ACCEL;
        }

        PIDControl velocityPID = new PIDControl(ShooterConstants.pidCoefficients);
        FeedForwardControl velocityFF = new FeedForwardControl(ShooterConstants.ffCoefficients);

        setPower(velocityPID.calculate(currVelocity, velocitySetPoint) +
                velocityFF.calculate(velocitySetPoint, accelSetPoint, (int) dir));
    }

    public boolean isBusy()
    {
        return accelTimer.time() < timeToAccel;
    }

    public double getVelocitySetPoint() { return velocitySetPoint;}
    public double getAccelSetPoint() { return accelSetPoint;}
    public double getCurrentVelocity() { return motorShooter.getVelocity(AngleUnit.DEGREES);}

}
