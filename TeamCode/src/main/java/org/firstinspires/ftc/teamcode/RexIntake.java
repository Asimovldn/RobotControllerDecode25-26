package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class RexIntake extends LinearOpMode {

    private DcMotor rexCoreMotor;

    @Override
    public void runOpMode() {

        rexCoreMotor = hardwareMap.get(DcMotor.class, "rexCoreMotor");

        rexCoreMotor.setDirection(DcMotor.Direction.FORWARD);

        rexCoreMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rexCoreMotor.setPower(1.0);
    }
}
