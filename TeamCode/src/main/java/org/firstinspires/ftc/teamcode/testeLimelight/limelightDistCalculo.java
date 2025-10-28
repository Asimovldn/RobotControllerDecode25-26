package org.firstinspires.ftc.teamcode.testeLimelight;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class limelightDistCalculo extends OpMode {

    private Limelight3A limelight;

    private double h2; // in cm

    private double h1; // in cm

    private double a1; // mounting angle

    private double distância;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8);
    }

    @Override
    public void start() {
        limelight.start();
    }

    @Override
    public void loop() {

    }


}
