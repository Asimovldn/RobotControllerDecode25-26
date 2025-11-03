package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Drive;

@TeleOp
public class DriveTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        Drive drive = new Drive();
        drive.init(hardwareMap, gamepad1);
        waitForStart();

        while (opModeIsActive())
        {
            drive.update();
        }
    }
}
