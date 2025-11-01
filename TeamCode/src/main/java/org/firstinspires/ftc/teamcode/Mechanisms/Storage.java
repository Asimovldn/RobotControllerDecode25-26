package org.firstinspires.ftc.teamcode.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Control.FeedForwardCoefficients;
import org.firstinspires.ftc.teamcode.Control.FeedForwardControl;
import org.firstinspires.ftc.teamcode.Control.PIDCoefficients;
import org.firstinspires.ftc.teamcode.Control.PIDControl;

@Config
public class Storage
{
    DcMotorEx storageMotor;
    Servo liftServo;

    private final int TICK_PER_REV = 288;
    private final double TICK_PER_DEGREE = (int)Math.round(TICK_PER_REV / 360.0);

    private boolean isAtShoot = false;

    private final double servoLiftPosition = 0.5;
    private final double servoDownPosition = 0.91;

    public static PIDCoefficients pidCoefficients = new PIDCoefficients(0,0,0);
    public static FeedForwardCoefficients ffCoefficients = new FeedForwardCoefficients(0.001,0,0);

    private double targetPosition = 0;
    public void init(HardwareMap hardwareMap)
    {
        storageMotor = hardwareMap.get(DcMotorEx.class, "storage_motor");
        storageMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        storageMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftServo = hardwareMap.get(Servo.class, "lift_servo");

        liftServo.setPosition(servoDownPosition);

    }

    public void setTargetDegrees(double degrees)
    {
        targetPosition = degrees;
    }

    public void updateStorage()
    {

        if (Math.abs(getTargetPosition() - getMotorPosition()) > 5)
        {
            FeedForwardControl ffControl = new FeedForwardControl(ffCoefficients);
            storageMotor.setPower(ffControl.calculate(90, 0, (int) Math.signum(getTargetPosition() - getMotorPosition())));
        } else {
            PIDControl pidControl = new PIDControl(pidCoefficients);
            storageMotor.setPower(pidControl.calculate(getMotorPosition(), getTargetPosition()));
        }

    }


    public boolean isBusy()
    {
        return storageMotor.isBusy();
    }

    public double getTargetPosition()
    {
        return targetPosition * TICK_PER_DEGREE;
    }

    public double getMotorVelocity()
    {
        return storageMotor.getVelocity(AngleUnit.DEGREES);
    }

    public int getMotorPosition()
    {
        return  storageMotor.getCurrentPosition();
    }





}
