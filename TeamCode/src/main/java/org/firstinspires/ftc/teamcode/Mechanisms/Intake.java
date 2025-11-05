package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake
{
    private DcMotorEx intakeMotor;

    public void init(HardwareMap hardwareMap)
    {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "motor_intake");

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void startIntake()
    {
        intakeMotor.setPower(0.5);
    }

    public void stopIntake()
    {
        intakeMotor.setPower(0.0);
    }

    public void startIntakeReverse() { intakeMotor.setPower(-0.5);}

}
