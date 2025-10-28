package org.firstinspires.ftc.teamcode.testeLimelight;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@TeleOp
public class LLmountAngulo extends OpMode {
    private static final String LIMELIGHT_URL = "http://limelight.local:5807/target";

    private Limelight3A limelight;

    private final double h2 = 29.5; // height of target (inches)

    private double h1 = 11.81; // height of camera  (inches)

    private double a1; // mounting angle

    private double a2 = getTy(); // angle from camera

    private double distancia;

    private double arcTan;

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
        telemetry.addData("MountingAngle", getArcTan(a1));
    }

    public double getDistancia(double distancia) {
        distancia = (h2-h1) / Math.tan(Math.toRadians(a1)+Math.toRadians(a2));
        return distancia;
    }

    private double getTy() {
        try { URL url = new URL(LIMELIGHT_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // opens an http connection
            conn.setRequestMethod("GET"); //request type; retrieving data
            conn.setConnectTimeout(200); //prevents robot freeze if no response
            conn.setReadTimeout(200); //prevents robot freeze if no response

            BufferedReader reader = new BufferedReader( // buffers the text; reads it faster
                    new InputStreamReader(conn.getInputStream()) //gets limelight stream; the values we want
            );           //^ InputStreamReader will turn the stream obtained from bytes into a string
            StringBuilder response = new StringBuilder();  //creates a new empty stringBuilder to hold the .json object as text
            String line;
            while ((line = reader.readLine()) != null) { //reads the buffered text line by line
                response.append(line); //appends each line to the empty stringBuilder
            }
            reader.close(); //closes reader when done

            JSONObject json = new JSONObject(response.toString()); //parses the string into a JSON object
            return json.getDouble("ty"); //extracts "ty" value from objects and returns it


        } catch (Exception e) { //catches errors
            telemetry.addData("ErrorDetected: ", e.getMessage()); //error debugging message
            return 0.0; //fallback value (?)
        }
    }

    public double getArcTan(double arcTan) {
        arcTan = Math.atan((h2 - h1) / distancia) - a2;
        return a1 = arcTan;
    }


}
