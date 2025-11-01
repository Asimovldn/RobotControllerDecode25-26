package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Mechanisms.Storage;

@TeleOp
public class StorageTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotorEx storageMotor;
        storageMotor = hardwareMap.get(DcMotorEx.class, "storage_motor");
        storageMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        storageMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Storage storage = new Storage();

        storage.init(hardwareMap);

        waitForStart();

        int lastPosition = 0;

        int dir = 1;

        storage.setTargetDegrees(60);

        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        while (opModeIsActive())
        {
           storage.updateStorage();
           telemetry.addData("motorPos", storage.getMotorPosition());
           telemetry.addData("targetPos", storage.getTargetPosition());
           telemetry.addData("targetVel", 90);
           telemetry.addData("motorVel", storage.getMotorVelocity());
           telemetry.addData("error", storage.getTargetPosition() - storage.getMotorPosition());
           telemetry.update();

        }
    }
}
