package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(13, 13)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 9)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35.5, 63.5, Math.toRadians(-90)))
//                //preload
//                .lineToLinearHeading(new Pose2d(35.5,0, Math.toRadians(-90)))
//
//                .splineToLinearHeading(new Pose2d(24, 13, Math.toRadians(0)), Math.toRadians(200))
//                .lineToLinearHeading(new Pose2d(53,12, Math.toRadians(0)))

                    .lineToLinearHeading(new Pose2d(35.5,0,Math.toRadians(-90)))
    //                .lineToLinearHeading(Red_Park_Pos2.pose2d())
    //                .lineToLinearHeading(Red_Dropoff.pose2d())
                    .splineToLinearHeading(new Pose2d (24,16, Math.toRadians(0)), Math.toRadians(200))
                    //.lineToSplineHeading(new Pose2d (24,16, Math.toRadians(0)))
                    .lineToLinearHeading(new Pose2d(54,12, 0))
                    .lineToLinearHeading(new Pose2d(24,16, 0))
                    .lineToLinearHeading(new Pose2d(59,12, 0))
                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

