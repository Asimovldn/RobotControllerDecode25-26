package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TesteEngrenagem extends LinearOpMode
{
    DcMotor motorTeste;
    @Override
    public void runOpMode() {
        motorTeste = hardwareMap.get(DcMotor.class, "teste");

        waitForStart();
        while (opModeIsActive())
        {
            motorTeste.setPower(gamepad1.left_stick_y);
        }
    }
}
