package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Mechanisms.Drive;

@Autonomous
public class Autonomo extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        Drive drive = new Drive();
        drive.init(hardwareMap);

        waitForStart();

        while (opModeIsActive())
        {
            drive.setPower(0.5, 0.5, 0.5, 0.5);
        }
    }
}
