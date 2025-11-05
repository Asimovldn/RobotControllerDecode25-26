package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;

@TeleOp
public class TesteControleVel extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        Shooter shooter = new Shooter();
        shooter.init(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("velocitySetPoint", 0);
        telemetry.addData("accelSetPoint", 0);
        telemetry.addData("motorVelocity", 0);

        telemetry.update();
        waitForStart();

        ElapsedTime waitTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        shooter.setShooterAngularVelocity(Math.toRadians(1500));

        while (opModeIsActive())
        {

            shooter.updateShooter();
            telemetry.addData("velocitySetPoint", Math.toDegrees(shooter.getVelocitySetPoint()));
            telemetry.addData("accelSetPoint", shooter.getAccelSetPoint());
            telemetry.addData("motorVelocity", shooter.getCurrentVelocity());

            telemetry.update();


        }


    }
}
