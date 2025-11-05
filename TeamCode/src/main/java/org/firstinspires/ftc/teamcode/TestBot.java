package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Bot;
import org.firstinspires.ftc.teamcode.Mechanisms.Storage;

@TeleOp
public class TestBot extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        Bot bot = new Bot();
        bot.init(hardwareMap, gamepad1);
        waitForStart();

        while (opModeIsActive())
        {
           if (gamepad2.left_bumper)
           {
               bot.startIntake();
           } else if (gamepad2.right_bumper) {
               bot.startIntakeReverse();
           } else {
               bot.stopIntake();
           }

           if (gamepad2.a)
           {
               bot.setGoalArtifact(Storage.ARTIFACT.GREEN);
           } else if (gamepad2.b)
           {
               bot.setShooterVelocity(-100);
           } else if (gamepad2.x) {
               bot.setShooterVelocity(1300);
           } else if (gamepad2.y) {
               bot.liftStorage();
           }



           if (bot.launchedArtifact())
           {
               bot.lowStorage();
               bot.stopShooter();
           }



           bot.update();
        }
    }
}
