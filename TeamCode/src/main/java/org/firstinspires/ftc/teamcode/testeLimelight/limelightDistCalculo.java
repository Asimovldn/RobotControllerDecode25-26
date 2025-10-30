package org.firstinspires.ftc.teamcode.testeLimelight;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class limelightDistCalculo extends OpMode {

    private Limelight3A limelight;

    private final double h2 = 75; // height of target (inches)

    private final double h1 = 24; // height of camera  (inches)

    private final double a1 = 6.37438269675366061426525612185; // 0.221 radians (?) or 12.662 degrees (?)

    private double ty; // angle from camera

    private double distancia;


    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8);
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);
    }

    @Override
    public void start() {
        limelight.start();
    }

    @Override
    public void loop() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            ty = result.getTy();

            telemetry.addData("Target Y", ty);
        } else {
            telemetry.addData("Target Y", "No target found");
        }
        double distCm = getDistancia(result.getTy());
        telemetry.addData("Distancia", distCm);
        telemetry.update();

    }

    public double getDistancia(double ty) {
        distancia = (h2 - h1) / Math.tan(Math.toRadians(a1 + ty));
        return distancia;
    }

}