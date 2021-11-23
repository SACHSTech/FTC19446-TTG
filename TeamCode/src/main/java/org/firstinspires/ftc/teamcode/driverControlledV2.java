package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



//blah blah blah

@TeleOp
public class driverControlledV2 extends LinearOpMode {
    //this compiles on AS so use this exact process when defining motor objects
    /*DcMotor motorLF = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor motorLB = hardwareMap.dcMotor.get("motorBackLeft");
    DcMotor motorRF = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor motorRB = hardwareMap.dcMotor.get("MotorBackRight"); */

    //this don't compile on AS when it works on onBot...

    @Override
    public void runOpMode() {
        DcMotor motorLF = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorLB = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorRF = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorRB = hardwareMap.get(DcMotor.class, "motorBackRight");
        DcMotor Intake = hardwareMap.get(DcMotor.class, "poggy");
        DcMotor Arm = hardwareMap.get(DcMotor.class, "arm");
        //DcMotor Carousel = hardwareMap.get(DcMotor.class, "carousel");

        waitForStart();

        if (isStopRequested()) return;

        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()) {
            double y, x, rx;

            //carousel
       //     if(gamepad2.right_bumper) {
         //       Carousel.setPower(1);


            //intake
            Intake.setPower(gamepad2.right_stick_y);

            //Arm
            Arm.setPower(gamepad2.left_stick_y);
            //happy pen

            //halve speed
            if (gamepad1.right_bumper) {
                y = -gamepad1.left_stick_y / 2;
                x = (gamepad1.left_stick_x * 1.1) / 2; // Counteract imperfect strafing
                rx = gamepad1.right_stick_x / 2;
            }

            //move speed
            else {
                y = -gamepad1.left_stick_y;
                x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                rx = gamepad1.right_stick_x;
            }

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorLF.setPower(frontLeftPower);
            motorLB.setPower(backLeftPower);
            motorRF.setPower(frontRightPower);
            motorRB.setPower(backRightPower);

            telemetry.addData("LF Power:", motorLF.getPower());
            telemetry.addData("LB Power:", motorLB.getPower());
            telemetry.addData("RF Power:", motorRF.getPower());
            telemetry.addData("RB Power:", motorRB.getPower());
            telemetry.addData( "Intake Power:", Intake.getPower());
            telemetry.addData( "Arm Power: ", Arm.getPower());
            // telemetry.addData("Carousel Turner", Carousel);
            telemetry.update();
        }
    }
}