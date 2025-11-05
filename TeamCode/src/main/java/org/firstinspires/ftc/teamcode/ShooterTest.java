package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.Mechanisms.Storage;

@TeleOp
public class ShooterTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {

        Shooter shooter = new Shooter();
        shooter.init(hardwareMap);

        Storage storage = new Storage();
        storage.init(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("velocitySetPoint", 0);
        telemetry.addData("accelSetPoint", 0);
        telemetry.addData("motorVelocity", 0);
        telemetry.addData("current", shooter.getMotorCurrent());

        telemetry.update();

        shooter.setShooterAngularVelocity(Math.toRadians(1300));

        while (opModeInInit())
        {
            shooter.update();
            telemetry.addData("velocitySetPoint", shooter.getVelocitySetPoint());
            telemetry.addData("accelSetPoint", shooter.getAccelSetPoint());
            telemetry.addData("motorVelocity", shooter.getCurrentVelocity());
            telemetry.addData("current", shooter.getMotorCurrent());

            telemetry.update();
        }
        waitForStart();

        storage.liftArtifact();
        while (opModeIsActive())
        {
            shooter.update();
            telemetry.addData("velocitySetPoint", shooter.getVelocitySetPoint());
            telemetry.addData("accelSetPoint", shooter.getAccelSetPoint());
            telemetry.addData("motorVelocity", shooter.getCurrentVelocity());
            telemetry.addData("current", shooter.getMotorCurrent());

            telemetry.update();

            if (shooter.launchedArtifact())
            {
                break;
            }
        }
    }
}
