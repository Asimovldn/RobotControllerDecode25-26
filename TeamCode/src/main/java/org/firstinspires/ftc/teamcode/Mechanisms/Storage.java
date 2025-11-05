package org.firstinspires.ftc.teamcode.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Control.FeedForwardCoefficients;
import org.firstinspires.ftc.teamcode.Control.FeedForwardControl;
import org.firstinspires.ftc.teamcode.Control.PIDCoefficients;
import org.firstinspires.ftc.teamcode.Control.PIDControl;
import org.firstinspires.ftc.teamcode.Pipeline.StoragePipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera2;

@Config
public class Storage
{
    private DcMotorEx storageMotor;
    private Servo liftServo;

    private OpenCvCamera camera;

    private final int TICK_PER_REV = 288;
    private final double TICK_PER_DEGREE = (int)Math.round(TICK_PER_REV / 360.0);

    private boolean isAtShoot = false;

    private final double servoLiftPosition = 0.3;
    private final double servoDownPosition = 1.0;

    public static PIDCoefficients pidCoefficients = new PIDCoefficients(0,0,0);
    public static FeedForwardCoefficients ffCoefficients = new FeedForwardCoefficients(0.001,0,0.04);

    private double targetPosition = 0;

    StoragePipeline pipeline;

    private double waitTime = 30; // ms;

    public static double motorPower = 0.0;

    private final int TICKS_PER_60_DEG = 45;
    public enum ARTIFACT
    {
        GREEN,
        PURPLE,
        NONE
    }

    ARTIFACT currGoalArtifact = ARTIFACT.NONE;

    int artifactsSearched = 0;

    boolean isSearching = false;

    public void init(HardwareMap hardwareMap)
    {
        storageMotor = hardwareMap.get(DcMotorEx.class, "storage_motor");
        storageMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        storageMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftServo = hardwareMap.get(Servo.class, "lift_servo");

        liftServo.setPosition(servoDownPosition);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        camera = OpenCvCameraFactory.getInstance().createInternalCamera2(OpenCvInternalCamera2.CameraDirection.BACK, cameraMonitorViewId);

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

    }

    public void openCamera()
    {
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Usually this is where you'll want to start streaming from the camera (see section 4)
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                pipeline = new StoragePipeline();
                camera.setPipeline(pipeline);

            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }


    public void runToPosition(int position)
    {
        storageMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        storageMotor.setTargetPosition(position);
        storageMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        storageMotor.setPower(motorPower);
    }

    public void searchArtifact()
    {
        if (!isAtShoot)
        {
            runToPosition(storageMotor.getCurrentPosition() + TICKS_PER_60_DEG);
            isAtShoot = true;
        }

        if (!storageMotor.isBusy() && isSearching)
        {
            artifactsSearched++;
            if (pipeline.getAreaState() == currGoalArtifact)
            {
                
            }
        }
    }

    public void setGoalArtifact(ARTIFACT artifact)
    {
        currGoalArtifact = artifact;
        isSearching = true;
        artifactsSearched = 0;
    }

    public void setVelocity(double vel)
    {
        FeedForwardControl ffControl = new FeedForwardControl(ffCoefficients);
        storageMotor.setPower(ffControl.calculate(vel, 0, 1));
    }

    public void liftArtifact()
    {
        liftServo.setPosition(servoLiftPosition);
    }

    public void lowServoArtifact()
    {
        liftServo.setPosition(servoDownPosition);
    }

    public boolean isBusy()
    {
        return Math.abs(getTargetPosition() - getMotorPosition()) > 10;
    }

    public double getTargetPosition()
    {
        return targetPosition * TICK_PER_DEGREE;
    }

    public double getMotorVelocity()
    {
        return storageMotor.getVelocity(AngleUnit.DEGREES);
    }

    public int getMotorPosition()
    {
        return  storageMotor.getCurrentPosition();
    }

    public ARTIFACT getShooterArtifact()
    {
        if (pipeline == null)
        {
            return null;
        }
        return pipeline.getAreaState();
    }




}
