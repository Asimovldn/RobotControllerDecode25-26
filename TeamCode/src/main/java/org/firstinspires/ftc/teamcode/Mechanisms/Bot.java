package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Bot
{
    Drive drive = new Drive();
    Intake intake = new Intake();
    Shooter shooter = new Shooter();
    Storage storage = new Storage();

    private boolean isAcceleratingShooter = false;

    private boolean isLiftUp = false;

    public void init(HardwareMap hardwareMap, Gamepad gamepad)
    {
        drive.init(hardwareMap, gamepad);
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        storage.init(hardwareMap);

        storage.openCamera();
    }

    public void startIntake()
    {
        intake.startIntake();
    }

    public void stopIntake()
    {
        intake.stopIntake();
    }

    public void startIntakeReverse()
    {
        intake.startIntakeReverse();
    }

    public void setGoalArtifact(Storage.ARTIFACT artifact)
    {
        storage.setGoalArtifact(artifact);
    }

    public void update()
    {
        drive.update();
        storage.update();
        shooter.update();

        if (isLiftUp)
        {
            if (shooter.launchedArtifact())
            {
                isLiftUp = false;
                storage.lowServoArtifact();
                isAcceleratingShooter = false;
            }
        }
    }

    public void setShooterVelocity(double vel)
    {
        shooter.setShooterAngularVelocity(Math.toRadians(vel));
    }

    public void stopShooter()
    {
        shooter.setShooterAngularVelocity(Math.toRadians(0));
    }


    public void liftStorage()
    {
        storage.liftArtifact();
    }

    public void lowStorage()
    {
        storage.lowServoArtifact();
    }


    public void goToNextIntake()
    {
        //storage.goToNextIntake();
    }

    public boolean launchedArtifact()
    {
        return shooter.launchedArtifact();
    }





}
