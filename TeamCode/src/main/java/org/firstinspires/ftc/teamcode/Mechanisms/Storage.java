package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Storage
{
    DcMotorEx storageMotor;
    Servo liftServo;

    private final int TICK_PER_REV = 288;
    private final int TICK_PER_DEGREE = TICK_PER_REV / 360;

    private boolean isAtShoot = false;

    private double servoLiftPosition = 0.75;
    private double servoDownPosition = 1.0;

    public void init(HardwareMap hardwareMap)
    {
        storageMotor = hardwareMap.get(DcMotorEx.class, "storage_motor");
        storageMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftServo = hardwareMap.get(Servo.class, "lift_servo");

        liftServo.setPosition(0.91);

    }

    public void move60()
    {
        storageMotor.setTargetPosition(storageMotor.getCurrentPosition() + TICK_PER_DEGREE * 60);
        storageMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        storageMotor.setPower(0.1);
    }

    public boolean isBusy()
    {
        return storageMotor.isBusy();
    }






}
