package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.ColorSensorBench;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensorTest extends OpMode {

    ColorSensorBench bench = new ColorSensorBench();


    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        bench.getDetectedColor(telemetry);
    }

}
