package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensorBench {
        NormalizedColorSensor colorSensor;


        public enum DetectedColor {
            GREEN,
            PURPLE,
            UNKNOWN
        }

        public void init(HardwareMap hwMap) {
            colorSensor = hwMap.get(NormalizedColorSensor.class, "Sensor_de_cor");
        }

        public DetectedColor getDetectedColor(Telemetry telemetry) {
            NormalizedRGBA colors = colorSensor.getNormalizedColors();

            float normRed, normGreen, normBlue;

            normRed = colors.red / colors.alpha;
            normGreen = colors.green / colors.alpha;
            normBlue = colors.blue / colors.alpha;

            telemetry.addData("red", normRed);
            telemetry.addData("green", normGreen);
            telemetry.addData("blue", normBlue);

            return DetectedColor.UNKNOWN;

            // add if statements for each color to be detected
        }

}
