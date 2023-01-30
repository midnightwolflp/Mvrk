package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static com.qualcomm.robotcore.util.ElapsedTime.Resolution.MILLISECONDS;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.BottomCone;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.BottomMidCone;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Claw_Close_Pos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Claw_Open_Pos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset10;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset11;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset15;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset2;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset3;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset4;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_offset9;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_wait12;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_wait13;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_wait14;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_wait5;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_wait6;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Cycle_wait7;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.DropOffPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.FloorPosition;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.GroundJunction;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.MiddleCone;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_offset10;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_offset11;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_offset2;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_offset3;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_offset4;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_offset5;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_wait6;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_wait7;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Preload_wait8;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_CycleEnd;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_CycleStart;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_Preload_Dropoff;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_cyclesToRun;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.MvrkServos.CARTOON;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.MvrkServos.TEACUP;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_Park_Pos1;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_Park_Pos2;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_Park_Pos3;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.Red_Start;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.MvrkMotors.CAT_MOUSE;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.MvrkServos.FLAMETHROWER;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.SlidePower_Down;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.SlidePower_Up;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.TopMidCone;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.TopCone;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.HighJunction;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.LowJunction;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.slide_currentPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.slide_newPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.turretDropoff;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.turretHalfRight;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.turretLeft;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.turretRight;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.turretUp;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.turret_newPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.xSlideDropPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.xSlideOutPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.xSlideInPos;
import static org.firstinspires.ftc.teamcode.Mvrk_Robot.xSlidePickupPos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;

/*
 * This is an example of a more complex path to really test the tuning.
 */


@Config
@Autonomous(name="Autonomous Testing",group = "Autonomous")
public class Mvrk_Autonomous extends LinearOpMode {

    private Pose2d currentPose;

    enum MvrkAllianceField {
        RED,
        BLUE
    }
    class CurrentPoseEstimator {
        Pose2d update() {return Red_CycleStart.myPose2D;}


    }
    private CurrentPoseEstimator poseEstimator = new CurrentPoseEstimator();

    Mvrk_Robot Mavryk = new Mvrk_Robot();

    private static FtcDashboard rykRobot;
    OpenCvWebcam Sauron = null;
    AprilTagDetectionPipeline pipeline;

    private static int iTeleCt = 1;

    // VUFORIA Key
    public static final String VUFORIA_LICENSE_KEY = "AZRnab7/////AAABmTUhzFGJLEyEnSXEYWthkjhGRzu8klNOmOa9WEHaryl9AZCo2bZwq/rtvx83YRIgV60/Jy/2aivoXaHNzwi7dEMGoaglSVmdmzPF/zOPyiz27dDJgLVvIROD8ww7lYzL8eweJ+5PqLAavvX3wgrahkOxxOCNeKG9Tl0LkbS5R11ATXL7LLWeUv5FP1aDNgMZvb8P/u96OdOvD6D40Nf01Xf+KnkF5EXwNQKk1r7qd/hiv9h80gvBXMFqMkVgUyogwEnlK2BfmeUhGVm/99BiwwW65LpKSaLVPpW/6xqz9SyPgZ/L/vshbWgSkTB/KoERiV8MsW79RPUuQS6NTOLY32I/kukmsis3MFst5LP/d3gx";

    // Field Dimensions

    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    // We will define some constants and conversions here
    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = 6 * mmPerInch;          // the height of the center of the target image above the floor
    private static final float halfField        = 72 * mmPerInch;
    private static final float halfTile         = 12 * mmPerInch;
    private static final float oneAndHalfTile   = 36 * mmPerInch;

    final float CAMERA_FORWARD_DISPLACEMENT  = 0.0f * mmPerInch;   // eg: Enter the forward distance from the center of the robot to the camera lens
    final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * mmPerInch;   // eg: Camera is 6 Inches above ground
    final float CAMERA_LEFT_DISPLACEMENT     = 0.0f * mmPerInch;   // eg: Enter the left distance from the center of the robot to the camera lens

    // Class Members
    private OpenGLMatrix lastLocation   = null;
    private VuforiaLocalizer vuforia    = null;
    private VuforiaTrackables targets   = null ;

    private boolean targetVisible       = false;


    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.04;

    // Tag ID 2,9,16 from 36h11 family
    int LEFT = 2;
    int MIDDLE = 9;
    int RIGHT = 20;

    public AprilTagDetection tagOfInterest = null;

    ElapsedTime timer = new ElapsedTime(MILLISECONDS);

    //    public TrajectorySequence Park;
    public TrajectorySequence trajPreLoadDropOff;
    public TrajectorySequence trajCycleDropOffTopCone;
    public TrajectorySequence trajCycleDropOffTopMidCone;
    public TrajectorySequence trajCycleDropOffMiddleCone;
    public TrajectorySequence trajCycleDropOffBottomMidCone;
    public TrajectorySequence trajCycleDropOffBottomCone;
    public TrajectorySequence trajParking;
    public int currentCyclePickupCone = TopCone;

    Mvrk_Robot.AutoState currentAutoState = Mvrk_Robot.AutoState.IDLE;

    public static int pos = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        Mavryk.init(hardwareMap);

        ElapsedTime trajectoryTimer = new ElapsedTime(MILLISECONDS);

        // init Dashboard
        rykRobot = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        double volts = getBatteryVoltage();
        telemetry.addLine(String.format("%d. Battery voltage: %.1f volts", iTeleCt++, volts));

        initMotorsAndServos();
        telemetry.addData("Status: ", "Motors & Servos initialized");
        telemetry.update();

        buildPreloadTrajectory();
        trajCycleDropOffTopCone = buildCycleTrajectory(TopCone); // Note: Drop slides to pick up the next cone, in this case Top Mid
        trajCycleDropOffTopMidCone = buildCycleTrajectory(TopMidCone); // Note: Drop slides to pick up the next cone, in this case Middle
        trajCycleDropOffMiddleCone = buildCycleTrajectory(MiddleCone); // Note: Drop slides to pick up the next cone, in this case BottomMid
        trajCycleDropOffBottomMidCone = buildCycleTrajectory(BottomMidCone); // Note: Drop slides to pick up the next cone, in this case Bottom
        trajCycleDropOffBottomCone = buildCycleTrajectory(BottomCone); // Note: Drop slides to floor to park
        telemetry.addData("Status: ", "Building Pre-load and drop off Trajectories completed");
        telemetry.update();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        Sauron = OpenCvCameraFactory.getInstance().createWebcam(Mavryk.eyeOfSauron, cameraMonitorViewId);
        pipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        Sauron.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        Sauron.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                public void onOpened() {
                    Sauron.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
                    rykRobot.startCameraStream(Sauron, 0);
                }
                public void onError(int errorCode) {
                    return;
                }
           });

        telemetry.addData("Status: ", "Starting April Tag detection");
        telemetry.update();

        // while waiting for the game to start, search for april tags
        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = pipeline.getLatestDetections();
            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");
                    if (tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }
//            telemetry.update();
            sleep(20);
        }

        // 1. Calculate Parking Position
        if (tagOfInterest == null ) {
            telemetry.addLine("Tag detected: NONE; Going to Position 1");
            pos = 1;
        } else if (tagOfInterest.id == LEFT) {
            telemetry.addLine("Tag detected: LEFT; Going to Position 1");
            pos = 1;
        } else if (tagOfInterest.id == MIDDLE) {
            telemetry.addLine("Tag detected: MIDDLE; Going to Position 2");
            pos = 2;
        } else {
            telemetry.addLine("Tag detected: RIGHT; Going to Position 3");
            pos = 3;
        }
        telemetry.update();

        buildParkTrajectory(pos);

        // Drop off preload
        trajectoryTimer.reset();
        Mavryk.MecanumDrive.setPoseEstimate(Red_Start.pose2d());
        currentAutoState = Mvrk_Robot.AutoState.PRELOAD;
        Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajPreLoadDropOff);
        boolean bTrajCompleted = false;
        while (opModeIsActive() && !isStopRequested() && !bTrajCompleted ) {
            // Our state machine logic
            // You can have multiple switch statements running together for multiple state machines
            // in parallel. This is the basic idea for subsystems and commands.

            // We essentially define the flow of the state machine through this switch statement
            switch (currentAutoState) {
                case PRELOAD:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        if(Red_cyclesToRun >= 1){
                            currentAutoState = Mvrk_Robot.AutoState.TOPCONE;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajCycleDropOffTopCone);
                        }else{
                            currentAutoState = Mvrk_Robot.AutoState.PARK;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajParking);
                        }
                    }
                    break;
                case TOPCONE:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        if(Red_cyclesToRun >= 2){
                            currentAutoState = Mvrk_Robot.AutoState.TOPMIDCONE;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajCycleDropOffTopMidCone);
                        }else{
                            currentAutoState = Mvrk_Robot.AutoState.PARK;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajParking);
                        }
                    }
                    break;
                case TOPMIDCONE:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        if(Red_cyclesToRun >= 3){
                            currentAutoState = Mvrk_Robot.AutoState.MIDCONE;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajCycleDropOffMiddleCone);
                        }else{
                            currentAutoState = Mvrk_Robot.AutoState.PARK;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajParking);
                        }
                    }
                    break;
                case MIDCONE:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        if(Red_cyclesToRun >= 4){
                            currentAutoState = Mvrk_Robot.AutoState.BOTTOMMIDCONE;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajCycleDropOffBottomMidCone);
                        }else{
                            currentAutoState = Mvrk_Robot.AutoState.PARK;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajParking);
                        }
                    }
                    break;
                case BOTTOMMIDCONE:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        if(Red_cyclesToRun >= 5){
                            currentAutoState = Mvrk_Robot.AutoState.BOTTOMCONE;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajCycleDropOffBottomCone);
                        }else{
                            currentAutoState = Mvrk_Robot.AutoState.PARK;
                            Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                            Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajParking);
                        }
                    }
                    break;
                case BOTTOMCONE:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        currentAutoState = Mvrk_Robot.AutoState.PARK;
                        Mavryk.MecanumDrive.setPoseEstimate(poseEstimator.update());
                        Mavryk.MecanumDrive.followTrajectorySequenceAsync(trajParking);
                    }
                    break;
                case PARK:
                    if (!Mavryk.MecanumDrive.isBusy()) {
                        currentAutoState = Mvrk_Robot.AutoState.IDLE;
                    }
                    break;
                case IDLE:
                    // Do nothing in IDLE
                    // currentAutoState does not change once in IDLE
                    // This concludes the autonomous program
                    bTrajCompleted = false;
                    break;
            }

            Mavryk.MecanumDrive.update();
            Mavryk.TomAndJerrySlide.update();
            Mavryk.TeacupTurret.update();

            // Once we figure out the right steps to drop off the cone & pick up when ready
            // Mavryk.LooneyClaw.update();
            // Mavryk.FlameThrower.update();

            // Print state to telemetry
            telemetry.addData("Currently Running", currentAutoState);
            telemetry.update();

        }
        telemetry.addData("Trajectories completed in: %f seconds", trajectoryTimer.seconds() );
        telemetry.addData("Current Slide Pos: %d", slide_currentPos);
        telemetry.update();

    }

    void buildPreloadTrajectory() {
        telemetry.addLine(String.format("%d. buildPreloadTrajectory", iTeleCt++));

        trajPreLoadDropOff = Mavryk.MecanumDrive.trajectorySequenceBuilder(Red_Start.pose2d())
                .lineToLinearHeading(Red_Preload_Dropoff.pose2d())  // STEP 1
                        .UNSTABLE_addTemporalMarkerOffset(Preload_offset2, () -> {
                            Mavryk.TomAndJerrySlide.setTargetPosition(LowJunction); // STEP 2
                        })
                        .UNSTABLE_addTemporalMarkerOffset(Preload_offset3, () -> {
                            Mavryk.TeacupTurret.setTargetPosition(turretRight);     // STEP 3
                        })
                        .UNSTABLE_addTemporalMarkerOffset(Preload_offset4, () -> {
                            Mavryk.TomAndJerrySlide.setTargetPosition(HighJunction);// STEP 4
                        })
                        .UNSTABLE_addTemporalMarkerOffset(Preload_offset5, () -> {
                            Mavryk.FlameThrower.setPosition(xSlideDropPos);         // STEP 5
                        })
                .addTemporalMarker( () -> {
                    Mavryk.TomAndJerrySlide.setTargetPosition(DropOffPos);              // STEP 6
                })
                .waitSeconds(Preload_wait6)
                .addTemporalMarker( () -> {
                    Mavryk.Looney.setPosition(Claw_Open_Pos);         // STEP 7
                })
                .waitSeconds(Preload_wait7)
                .addTemporalMarker(() -> {
                    Mavryk.FlameThrower.setPosition(xSlideInPos);     // STEP 8
                })
                .waitSeconds(Preload_wait8)
                .lineToLinearHeading(Red_CycleStart.pose2d())         // STEP 9
                        .UNSTABLE_addTemporalMarkerOffset(Preload_offset10, () -> {
                            Mavryk.TomAndJerrySlide.setTargetPosition(LowJunction);                   // STEP 10
                        })

                .build();

        int iNumSegments = trajPreLoadDropOff.size();
        telemetry.addLine(String.format("%d. Preload Trajectory numTrajectory Segments: %d", iTeleCt++, iNumSegments));
        for(int iSeg=0; iSeg<iNumSegments; iSeg++ ) {
            telemetry.addLine(String.format("%d. Preload Trajectory Segment %d Duration: %.3f", iTeleCt++, iSeg,trajPreLoadDropOff.get(iSeg).getDuration()));
        }
        telemetry.addLine(String.format("%d. Preload calculated Duration: %.3f", iTeleCt++, trajPreLoadDropOff.duration()));

        return;
    }

    void buildParkTrajectory(int iPos)
    {
        telemetry.addLine(String.format("%d. buildParkTrajectory", iTeleCt++));

        switch (iPos) {
            case 1:
            default:
                trajParking = Mavryk.MecanumDrive.trajectorySequenceBuilder(Red_CycleStart.pose2d())
                        .lineToLinearHeading(Red_Park_Pos1.pose2d())
                            .UNSTABLE_addTemporalMarkerOffset(Cycle_offset2, () -> {
                                Mavryk.TeacupTurret.setTargetPosition(turretUp);    // STEP 2
                            })
                            .UNSTABLE_addTemporalMarkerOffset(Cycle_offset4, () -> {
                                Mavryk.TomAndJerrySlide.setTargetPosition(FloorPosition);    // STEP 3
                            })
                        .build();
                break;
            case 2:
                trajParking = Mavryk.MecanumDrive.trajectorySequenceBuilder(Red_CycleStart.pose2d())
                         .lineToLinearHeading(Red_Park_Pos2.pose2d())
                            .UNSTABLE_addTemporalMarkerOffset(Cycle_offset2, () -> {
                                Mavryk.TeacupTurret.setTargetPosition(turretUp);    // STEP 2
                            })
                            .UNSTABLE_addTemporalMarkerOffset(Cycle_offset4, () -> {
                                Mavryk.TomAndJerrySlide.setTargetPosition(FloorPosition);    // STEP 3
                            })
                        .build();
                break;
            case 3:
                trajParking = Mavryk.MecanumDrive.trajectorySequenceBuilder(Red_CycleStart.pose2d())
                        .lineToLinearHeading(Red_Park_Pos3.pose2d())
                            .UNSTABLE_addTemporalMarkerOffset(Cycle_offset2, () -> {
                                Mavryk.TeacupTurret.setTargetPosition(turretUp);    // STEP 2
                            })
                            .UNSTABLE_addTemporalMarkerOffset(Cycle_offset4, () -> {
                                Mavryk.TomAndJerrySlide.setTargetPosition(FloorPosition);    // STEP 3
                            })
                        .build();
                break;
        }

        int iNumSegments = trajParking.size();
        telemetry.addLine(String.format("%d. Park Trajectory numTrajectory Segments: %d", iTeleCt++, iNumSegments));
        for(int iSeg=0; iSeg<iNumSegments; iSeg++ ) {
            telemetry.addLine(String.format("%d. Park Trajectory Segment %d Duration: %.3f", iTeleCt++, iSeg,trajParking.get(iSeg).getDuration()));
        }
        telemetry.addLine(String.format("%d. Park Trajectory calculated Duration: %.3f", iTeleCt++, trajParking.duration()));

        return;
    }

    TrajectorySequence buildCycleTrajectory(int iCycleConePickup)
    {
        telemetry.addLine(String.format("%d. buildCycleTrajectory %d", iTeleCt++, iCycleConePickup));
        TrajectorySequence trajSeq = Mavryk.MecanumDrive.trajectorySequenceBuilder(Red_CycleStart.pose2d())
                .lineToLinearHeading(Red_CycleEnd.pose2d()) // STEP 1
                    .UNSTABLE_addTemporalMarkerOffset(Cycle_offset2, () -> {
                        Mavryk.TeacupTurret.setTargetPosition(turretLeft);    // STEP 2
                    })
                    .UNSTABLE_addTemporalMarkerOffset(Cycle_offset3, () -> {
                        Mavryk.TomAndJerrySlide.setTargetPosition(iCycleConePickup);    // STEP 3
                    })
                    .UNSTABLE_addTemporalMarkerOffset(Cycle_offset4, () -> {
                        Mavryk.FlameThrower.setPosition(xSlidePickupPos);    // STEP 4
                    })
                .addTemporalMarker(()->{
                    Mavryk.Looney.setPosition(Claw_Close_Pos);
                })
                .waitSeconds(Cycle_wait5)   // STEP 5
                .addTemporalMarker(()->{
                    Mavryk.TomAndJerrySlide.setTargetPosition(LowJunction);
                })
                .waitSeconds(Cycle_wait6)   //STEP 6
                .addTemporalMarker(()->{
                    Mavryk.FlameThrower.setPosition(xSlideInPos);
                })
                .waitSeconds(Cycle_wait7)   //STEP 7
                .lineToLinearHeading(Red_CycleStart.pose2d())   //STEP 8
                    .UNSTABLE_addTemporalMarkerOffset(Cycle_offset9, () -> {
                        Mavryk.TeacupTurret.setTargetPosition(turretDropoff);   //STEP 9
                    })
                    .UNSTABLE_addTemporalMarkerOffset(Cycle_offset10, () -> {
                        Mavryk.TomAndJerrySlide.setTargetPosition(HighJunction);    //STEP10
                    })
                    .UNSTABLE_addTemporalMarkerOffset(Cycle_offset11, () -> {
                        Mavryk.FlameThrower.setPosition(xSlideOutPos);     //STEP11
                    })
                .addTemporalMarker( () -> {
                    Mavryk.TomAndJerrySlide.setTargetPosition(DropOffPos);    // STEP 12
                })
                .waitSeconds(Cycle_wait12)
                .addTemporalMarker( () -> {
                    Mavryk.Looney.setPosition(Claw_Open_Pos); // STEP 13
                })
                .waitSeconds(Cycle_wait13)
                .addTemporalMarker(() -> {
                    Mavryk.FlameThrower.setPosition(xSlideInPos); // STEP 14
                })
                .waitSeconds(Cycle_wait14)
                .UNSTABLE_addTemporalMarkerOffset(Cycle_offset15, () -> {
                    Mavryk.TomAndJerrySlide.setTargetPosition(LowJunction);  //STEP 15                 // STEP 10
                })
                .build();

        int iNumSegments = trajSeq.size();
        telemetry.addLine(String.format("%d. Cycle %d numTrajectory Segments: %d", iTeleCt++, iCycleConePickup, iNumSegments));
        for(int iSeg=0; iSeg<iNumSegments; iSeg++ ) {
            telemetry.addLine(String.format("%d. Cycle %d Trajectory Segment %d Duration: %.3f", iTeleCt++, iCycleConePickup, iSeg,trajSeq.get(iSeg).getDuration()));
        }
        telemetry.addLine(String.format("%d. Cycle %d Trajectory calculated Duration: %.3f", iTeleCt++, iCycleConePickup, trajSeq.duration()));

        return trajSeq;
    }

    void initMotorsAndServos()
    {
        // Reset Slides - current position becomes 0
        Mavryk.setRunMode(CAT_MOUSE, STOP_AND_RESET_ENCODER);
        Mavryk.setRunMode(CAT_MOUSE, RUN_WITHOUT_ENCODER);
        Mavryk.setPosition(CARTOON, Claw_Close_Pos);
        Mavryk.setPosition(FLAMETHROWER, xSlideInPos);
        Mavryk.setPosition(TEACUP, turret_newPos);
    }

    private double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }

    public void getTag(AprilTagDetectionPipeline pipeline)
    {
        ArrayList<AprilTagDetection> currentDetections = pipeline.getLatestDetections();
        if(currentDetections.size() != 0)
        {
            boolean tagFound = false;

            for(AprilTagDetection tag : currentDetections)
            {
                if(tag.id == LEFT ||  tag.id == MIDDLE || tag.id == RIGHT)
                {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }

            if(tagFound)
            {
                telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                tagToTelemetry(tagOfInterest);
            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }

        }
        else
        {
            telemetry.addLine("Don't see tag of interest :(");

            if(tagOfInterest == null)
            {
                telemetry.addLine("(The tag has never been seen)");
            }
            else
            {
                telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                tagToTelemetry(tagOfInterest);
            }

        }

        telemetry.update();
        sleep(20);
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }

    //    private void EstimateCurrentPose() {
//        // TODO: use vumarks to update current pose
//
//
//        currentPose = Red_Pickup.pose2d();
//
////        //get vuforia position
////        // check all the trackable targets to see which one (if any) is visible.
////        targetVisible = false;
////        for (VuforiaTrackable trackable : allTrackables) {
////            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
////                telemetry.addData("Visible Target", trackable.getName());
////                targetVisible = true;
////
////                // getUpdatedRobotLocation() will return null if no new information is available since
////                // the last time that call was made, or if the trackable is not currently visible.
////                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
////                if (robotLocationTransform != null) {
////                    lastLocation = robotLocationTransform;
////                }
////                break;
////            }
////        }
//
//        // Provide feedback as to where the robot is located (if we know).
//        if (targetVisible) {
//            // express position (translation) of robot in inches.
//            VectorF translation = lastLocation.getTranslation();
//            telemetry.addData("Pos (inches)", "{X, Y, Z} = %.1f, %.1f, %.1f",
//                    translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);
//
//            // express the rotation of the robot in degrees.
//            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
//            telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
//        }
//        else {
//            telemetry.addData("Visible Target", "none");
//        }
//        telemetry.update();
//        //get separate coordinates
//        //set currentPose = -|x|, -|y|
//    }

//    void initVuforia(){
//        // Connect to the camera we are to use.  This name must match what is set up in Robot Configuration
////        webcamName = Mavryk.eyeOfSauron;
//
//        /*
//         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
//         * We can pass Vuforia the handle to a camera preview resource (on the RC screen);
//         * If no camera-preview is desired, use the parameter-less constructor instead (commented out below).
//         * Note: A preview window is required if you want to view the camera stream on the Driver Station Phone.
//         */
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
//
//        parameters.vuforiaLicenseKey = VUFORIA_LICENSE_KEY;
//
//        // We also indicate which camera we wish to use.
//        parameters.cameraName = webcamName;
//
//        // Turn off Extended tracking.  Set this true if you want Vuforia to track beyond the target.
//        parameters.useExtendedTracking = false;
//
//        //  Instantiate the Vuforia engine
//        vuforia = ClassFactory.getInstance().createVuforia(parameters);
//
//        // Load the data sets for the trackable objects. These particular data
//        // sets are stored in the 'assets' part of our application.
//        targets = this.vuforia.loadTrackablesFromAsset("PowerPlay");
//
//        // For convenience, gather together all the trackable objects in one easily-iterable collection */
//        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
//        allTrackables.addAll(targets);
//
//
//        // Name and locate each trackable object
//        identifyTarget(0, "Red Audience Wall",   -halfField,  -oneAndHalfTile, mmTargetHeight, 90, 0,  90);
//        identifyTarget(1, "Red Rear Wall",        halfField,  -oneAndHalfTile, mmTargetHeight, 90, 0, -90);
//        identifyTarget(2, "Blue Audience Wall",  -halfField,   oneAndHalfTile, mmTargetHeight, 90, 0,  90);
//        identifyTarget(3, "Blue Rear Wall",       halfField,   oneAndHalfTile, mmTargetHeight, 90, 0, -90);
//
//        OpenGLMatrix cameraLocationOnRobot = OpenGLMatrix
//                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XZY, DEGREES, 90, 90, 0));
//
//        /**  Let all the trackable listeners know where the camera is.  */
//        for (VuforiaTrackable trackable : allTrackables) {
//            ((VuforiaTrackableDefaultListener) trackable.getListener()).setCameraLocationOnRobot(parameters.cameraName, cameraLocationOnRobot);
//        }
//    }
//    void    identifyTarget(int targetIndex, String targetName, float dx, float dy, float dz, float rx, float ry, float rz) {
//        VuforiaTrackable aTarget = targets.get(targetIndex);
//        aTarget.setName(targetName);
//        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
//    }

}


