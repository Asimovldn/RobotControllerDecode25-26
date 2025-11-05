package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Storage;

@TeleOp
public class StorageSpeedTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        Storage storage = new Storage();
        storage.init(hardwareMap);
        waitForStart();

        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);


        while (opModeIsActive())
        {
            storage.setVelocity(120);
            telemetry.addData("targetVel", 120);
            telemetry.addData("motorVel", storage.getMotorVelocity());
            telemetry.update();
        }
    }
}
