// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */

  private static final WPI_TalonSRX leftFrontMotor = RobotMap.leftFrontMotor;
  private static final WPI_TalonSRX rightFrontMotor = RobotMap.rightFrontMotor;
  private static final WPI_TalonSRX rightBackMotor = RobotMap.rightBackMotor;
  private static final WPI_TalonSRX leftBackMotor = RobotMap.leftBackMotor;
  
  private static XboxController driverController = Robot.m_robotContainer.driverController;

  public static final int MOTOR_ENCODER_CODES_PER_REV = 2048;
  public static final double DIAMETER_INCHES = 5.0;
  public static final double IN_TO_M = 0.254;

  public static final double WHEEL_DIAMETER = DIAMETER_INCHES * IN_TO_M;
  public static final double WHEEL_CIRCUMFERENCE = DIAMETER_INCHES * Math.PI;

  private static final double GEAR_RATIO = 12.75;

  private static final double TICKS_PER_METER = (MOTOR_ENCODER_CODES_PER_REV *GEAR_RATIO) / (WHEEL_CIRCUMFERENCE);
  private static final double METER_PER_TICKS = 1/TICKS_PER_METER;

  private final int timeoutMs = 10;

  public boolean state_flag_motion_profile = true;

  public DriveSubsystem() {

    resetEncoders();

    leftFrontMotor.set(ControlMode.Follower, leftBackMotor.getDeviceID());
    rightFrontMotor.set(ControlMode.Follower, rightBackMotor.getDeviceID());

    leftBackMotor.setNeutralMode(NeutralMode.Coast);
    rightBackMotor.setNeutralMode(NeutralMode.Coast);

    leftBackMotor.setInverted(false);//false
    leftFrontMotor.setInverted(false);//false
    rightBackMotor.setInverted(true); //true 
    rightFrontMotor.setInverted(true); //true

    /*leftFrontMotor.setNeutralMode(NeutralMode.Coast);
    rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    leftBackMotor.setNeutralMode(NeutralMode.Coast);
    rightBackMotor.setNeutralMode(NeutralMode.Coast);

    leftFrontMotor.setInverted(false); //true
    rightFrontMotor.setInverted(false); //false
    leftBackMotor.setInverted(false); //true
    rightBackMotor.setInverted(true); //false*/

    leftFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftFrontMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    leftFrontMotor.configVelocityMeasurementWindow(16);
    leftFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    rightFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    rightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightFrontMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    rightFrontMotor.configVelocityMeasurementWindow(16);
    rightFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    leftBackMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    leftBackMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftBackMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    leftBackMotor.configVelocityMeasurementWindow(16);
    leftBackMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    rightBackMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    rightBackMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightBackMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    rightBackMotor.configVelocityMeasurementWindow(16);
    rightBackMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    /*leftFrontMotor.configNominalOutputForward(0, timeoutMs);
    leftFrontMotor.configNominalOutputReverse(0, timeoutMs);
    leftFrontMotor.configPeakOutputForward(1, timeoutMs);
    leftFrontMotor.configPeakOutputReverse(-1, timeoutMs);

    leftBackMotor.configNominalOutputForward(0, timeoutMs);
    leftBackMotor.configNominalOutputReverse(0, timeoutMs);
    leftBackMotor.configPeakOutputForward(1, timeoutMs);
    leftBackMotor.configPeakOutputReverse(-1, timeoutMs);

    rightFrontMotor.configNominalOutputForward(0, timeoutMs);
    rightFrontMotor.configNominalOutputReverse(0, timeoutMs);
    rightFrontMotor.configPeakOutputForward(1, timeoutMs);
    rightFrontMotor.configPeakOutputReverse(-1, timeoutMs);

    rightBackMotor.configNominalOutputForward(0, timeoutMs);
    rightBackMotor.configNominalOutputReverse(0, timeoutMs);
    rightBackMotor.configPeakOutputForward(1, timeoutMs);
    rightBackMotor.configPeakOutputReverse(-1, timeoutMs);

    leftFrontMotor.setSensorPhase(true);
    rightFrontMotor.setSensorPhase(false);
    leftBackMotor.setSensorPhase(true);
    rightBackMotor.setSensorPhase(false);*/

    /*leftFrontMotor.setSelectedSensorPosition(0);
    leftBackMotor.setSelectedSensorPosition(0);
    rightFrontMotor.setSelectedSensorPosition(0);
    rightBackMotor.setSelectedSensorPosition(0);*/
      
    /*leftFrontMotor.set(ControlMode.Follower, leftFrontMotor.getDeviceID());
    rightFrontMotor.set(ControlMode.Follower, rightBackMotor.getDeviceID());*/
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setModePercentVoltage() {
    leftFrontMotor.set(ControlMode.PercentOutput, 0);
    rightFrontMotor.set(ControlMode.PercentOutput, 0);
    leftBackMotor.set(ControlMode.PercentOutput, 0);
    rightBackMotor.set(ControlMode.PercentOutput, 0);
  }

  public static void drive(double throttle, double rotate) {
    /*leftBackMotor.set(throttle - rotate); 
    rightBackMotor.set(throttle + rotate);*/
    leftBackMotor.set(throttle + rotate); 
    rightBackMotor.set(throttle - rotate);
  }

  public void stop() {
    drive(0, 0);
  }

  public double getLeftFrontEncoderPosition() {
    return leftFrontMotor.getSelectedSensorPosition();
  }

  public double getRightFrontEncoderPosition() {
    return rightFrontMotor.getSelectedSensorPosition();
  }

  public double getLeftBackEncoderPosition() {
    return leftFrontMotor.getSelectedSensorPosition();
  }

  public double getRightBackEncoderPosition() {
    return rightFrontMotor.getSelectedSensorPosition();
  }

  public double distanceTravelledinTicks() {
    return (getLeftFrontEncoderPosition() + getRightFrontEncoderPosition() + getLeftBackEncoderPosition() + getRightBackEncoderPosition()) / 4;
  }

  public double getLeftEncoderVelocityMetersPerSecond() {
    double leftVelocityMPS = (leftFrontMotor.getSelectedSensorVelocity() * 10);
    leftVelocityMPS = leftVelocityMPS * METER_PER_TICKS;
    return (leftVelocityMPS);
  }

  public double getRightEncoderVelocityMetersPerSecond() {
    double rightVelocityMPS = (rightFrontMotor.getSelectedSensorVelocity() * 10);
    rightVelocityMPS = rightVelocityMPS * METER_PER_TICKS;
    return (rightVelocityMPS);
  }

  public double leftDistanceTravelledinMeters() {
    double left_dist = getLeftFrontEncoderPosition() * METER_PER_TICKS;
    return left_dist;
  }

  public double rightDistanceTravelledinMeters() {
    double right_dist = getRightFrontEncoderPosition() * METER_PER_TICKS;
    return right_dist;
  }

  public double distanceTravelledinMeters() {
    double distanceTravelled = (leftDistanceTravelledinMeters() + rightDistanceTravelledinMeters()) / 2;
    return distanceTravelled;
  }

  public void resetEncoders() {
    leftFrontMotor.setSelectedSensorPosition(0);
    rightFrontMotor.setSelectedSensorPosition(0);
  }
  
}
