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

public class Shooter
{
    private DcMotorEx motorShooter;

    private final PIDControl velocityCorrectionPID = new PIDControl(0,0,0);
    private final FeedForwardControl velocityFF = new FeedForwardControl(0,0,0);

    private VoltageSensor voltageSensor;

    final static double ADMISSABLE_VELOCITY_ERROR = 10; // rad/s
    final static double SHOOTER_ACCEL = 10; //rad/s^2

    public void init(HardwareMap hardwareMap)
    {
        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        motorShooter = hardwareMap.get(DcMotorEx.class, "motor_shooter");

        motorShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //TODO Arrumar a direção de tal forma que valor positivo lance o projétil
        motorShooter.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private void setPower(double power)
    {
        motorShooter.setPower(power*12/voltageSensor.getVoltage());
    }

    private void controleDeVelocidade()
    {
        double x = 2;
    }

    private boolean isAccelerating = false;
    ElapsedTime accelTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    double initialVelocity = 0.0;

    // Velocidade em rad/s
    public void setShooterAngularVelocity(double angularVelocity)
    {
        double velocity = 0;
        double accel = 0;
        if (Math.abs(angularVelocity - motorShooter.getVelocity(AngleUnit.RADIANS)) < ADMISSABLE_VELOCITY_ERROR)
        {
            isAccelerating = false;
            // PID e FF
            accel = 0;
            velocity = angularVelocity + velocityCorrectionPID.calculate(motorShooter.getVelocity(AngleUnit.RADIANS), angularVelocity);
        } else {
            // Acelera/Desacelera em direção ao alvo com FF

            if (!isAccelerating)
            {
                accelTimer.reset();
                initialVelocity = motorShooter.getVelocity(AngleUnit.RADIANS);
            }

            double dir = Math.signum(angularVelocity - motorShooter.getVelocity(AngleUnit.RADIANS));

            accel = SHOOTER_ACCEL * dir;
            velocity = initialVelocity + accel * accelTimer.time();

            isAccelerating = true;

        }

        setPower(velocityFF.calculate(velocity, accel));
    }


}
