package org.firstinspires.ftc.teamcode.Mechanisms;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive
{
    DcMotorEx leftBack;
    DcMotorEx leftFront;
    DcMotorEx rightBack;
    DcMotorEx rightFront;

    Gamepad driveGamepad = null;

    public void init(@NonNull HardwareMap hardwareMap)
    {
        leftBack = hardwareMap.get(DcMotorEx.class, "left_back_drive");
        leftFront = hardwareMap.get(DcMotorEx.class, "left_front_drive");
        rightBack = hardwareMap.get(DcMotorEx.class, "right_back_drive");
        rightFront = hardwareMap.get(DcMotorEx.class, "right_front_drive");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void init(HardwareMap hardwareMap, Gamepad driveGamepad)
    {
        init(hardwareMap);
        this.driveGamepad = driveGamepad;
    }

    public void update()
    {
        if (driveGamepad != null)
        {
            updateTeleOp();
        }
    }

    private void updateTeleOp()
    {
        double multiplier = driveGamepad.left_bumper ? 0.5 : 1;

        double axial = -driveGamepad.left_stick_y;
        double lateral = driveGamepad.left_stick_x;
        double rotational = driveGamepad.right_stick_x;

        double leftBackPower = axial - lateral + rotational;
        double leftFrontPower = axial + lateral + rotational;
        double rightBackPower = axial + lateral - rotational;
        double rightFrontPower = axial - lateral - rotational;

        double max = Math.max(Math.max(Math.max(leftBackPower,leftFrontPower),rightBackPower),
                rightFrontPower);

        if (max > 1.0)
        {
            leftBackPower /= max;
            leftFrontPower /= max;
            rightBackPower /= max;
            rightFrontPower /= max;
        }


        leftBack.setPower(leftBackPower * multiplier);
        leftFront.setPower(leftFrontPower * multiplier);
        rightBack.setPower(rightBackPower * multiplier);
        rightFront.setPower(rightFrontPower * multiplier);

    }

    public void setPower(double leftBackPow, double leftFrontPow, double rightBackPow, double rightFrontPow)
    {
        leftBack.setPower(leftBackPow);
        leftFront.setPower(leftFrontPow);
        rightBack.setPower(rightBackPow);
        rightFront.setPower(rightFrontPow);
    }



}
