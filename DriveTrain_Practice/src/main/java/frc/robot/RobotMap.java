// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotMap {
  /** Creates a new RobotMap. */
    // Use addRequirements() here to declare subsystem dependencies.
    public static final int LEFT_FRONT_PORT = 1;
    public static final int LEFT_BACK_PORT = 3;
    public static final int RIGHT_FRONT_PORT = 2;
    public static final int RIGHT_BACK_PORT = 4;

    public static WPI_TalonFX leftFrontMotor = new WPI_TalonFX(LEFT_FRONT_PORT);
    public static WPI_TalonFX leftBackMotor = new WPI_TalonFX(LEFT_BACK_PORT);
    public static WPI_TalonFX rightFrontMotor = new WPI_TalonFX(RIGHT_FRONT_PORT);
    public static WPI_TalonFX rightBackMotor = new WPI_TalonFX(RIGHT_BACK_PORT);

    public static PigeonIMU arm_imu = new PigeonIMU(0);
    public static PigeonIMU drive_imu = new PigeonIMU(1); 
}
