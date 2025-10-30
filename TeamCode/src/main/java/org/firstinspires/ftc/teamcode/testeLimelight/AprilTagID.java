package org.firstinspires.ftc.teamcode.testeLimelight;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;

public class AprilTagID extends LinearOpMode {

    private Limelight3A limelight;

    public int identifier;

    @Override
    public void runOpMode() {

        limelight.start();
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8);
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        waitForStart();
        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                int id = fr.getFiducialId();
                telemetry.addData("April Tag ID: ", id);
                telemetry.update();
                identifier = id;
            }

            telemetry.addData("AprilTag ID: ", identifier);
            telemetry.update();

        }

    }
}
