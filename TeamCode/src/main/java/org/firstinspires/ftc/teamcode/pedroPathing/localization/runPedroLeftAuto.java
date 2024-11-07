package org.firstinspires.ftc.teamcode.pedroPathing.localization;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

/**
 * This is the CurvedBackAndForth autonomous OpMode. It runs the robot in a specified distance
 * forward and to the left. On reaching the end of the forward Path, the robot runs the backward
 * Path the same distance back to the start. Rinse and repeat! This is good for testing a variety
 * of Vectors, like the drive Vector, the translational Vector, the heading Vector, and the
 * centripetal Vector. Remember to test your tunings on StraightBackAndForth as well, since tunings
 * that work well for curves might have issues going in straight lines.
 *
 * @author Anyi Lin - 10158 Scott's Bots
 * @author Aaron Yang - 10158 Scott's Bots
 * @author Harrison Womack - 10158 Scott's Bots
 * @version 1.0, 3/13/2024
 */
@Config
@Autonomous (name = "runPedroLeftAuto", group = "Autonomous Pathing")
public class runPedroLeftAuto extends OpMode
{
    private Follower follower;

    private Pose startPose = new Pose(9, 84, 0);

    private GeneratedPathPedroLeftAuto gppla;

    private int pathNumber = -1;

    /**
     * This initializes the Follower and creates the forward and backward Paths. Additionally, this
     * initializes the FTC Dashboard telemetry.
     */
    @Override
    public void init()
    {
        follower = new Follower(hardwareMap);
        follower.setMaxPower(0.6);

        follower.setStartingPose(startPose);

        gppla = new GeneratedPathPedroLeftAuto();

        pathNumber = 0;
        follower.followPath(gppla.specimenPlacement);

    }

    /**
     * This runs the OpMode, updating the Follower as well as printing out the debug statements to
     * the Telemetry, as well as the FTC Dashboard.
     */
    @Override
    public void loop()
    {
        follower.update();
        if (!follower.isBusy()) {
            pathNumber++;

            switch (pathNumber) {
                case 1: {
                    telemetry.addLine("specimen completed.");
                    telemetry.update();

                    follower.followPath(gppla.firstSampleGrab);
                    break;
                }
                case 2: {
                    telemetry.addLine("first sample grab");
                    telemetry.update();

                    follower.followPath(gppla.firstSampleRelease);
                    break;
                }
                case 3: {
                    telemetry.addLine("first sample release");
                    telemetry.update();

                    follower.followPath(gppla.secondSampleGrab);
                    break;
                }
                case 4: {
                    telemetry.addLine("second sample grab");
                    telemetry.update();

                    follower.followPath(gppla.secondSampleRelease);
                    break;
                }
                case 5: {
                    telemetry.addLine("second sample release");
                    telemetry.update();

                    follower.followPath(gppla.thirdSampleGrab);
                    break;
                }
                case 6: {
                    telemetry.addLine("third sample grab");
                    telemetry.update();

                    follower.followPath(gppla.thirdSampleRelease);
                    break;
                }
                case 7: {
                    telemetry.addLine("third sample release");
                    telemetry.update();

                    follower.followPath(gppla.park);
                    break;
                }

                default: {
                    requestOpModeStop();
                    break;
                }
            }

        }
    }
}