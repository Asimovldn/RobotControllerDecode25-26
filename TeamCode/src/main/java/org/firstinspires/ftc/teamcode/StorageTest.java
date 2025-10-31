package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
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

        while (opModeIsActive())
        {
            storageMotor.setVelocity(270 * dir, AngleUnit.DEGREES);

            if (storageMotor.getCurrentPosition() == lastPosition + dir * 120 * 288 / 360)
            {
                storageMotor.setVelocity(0);
                lastPosition = storageMotor.getCurrentPosition();
                dir = -dir;

                sleep(3000);
            }
        }
    }
}
