/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class Drivebase extends SubsystemBase 
{
  /**
   * Creates a new Drivebase.
   */
  public final WPI_TalonSRX LeftMaster  = new WPI_TalonSRX(Ports.LEFT_MASTER_PORT);
  private final WPI_TalonSRX LeftSlave   = new WPI_TalonSRX(Ports.LEFT_SLAVE_PORT);
  public final WPI_TalonSRX RightMaster = new WPI_TalonSRX(Ports.RIGHT_MASTER_PORT);
  private final WPI_TalonSRX RightSlave  = new WPI_TalonSRX(Ports.RIGHT_SLAVE_PORT);
  
  private final SpeedControllerGroup LeftMotors = new SpeedControllerGroup(LeftMaster, LeftSlave);
  private final SpeedControllerGroup RightMotors = new SpeedControllerGroup(RightMaster, RightSlave);

  private final DifferentialDrive Drive = new DifferentialDrive(LeftMotors, RightMotors);

  private final AHRS Gyro = new AHRS();

  public Drivebase() 
  {
    Gyro.reset();
    RightMaster.setSensorPhase(true);

    setMaxOutput(0.5);
 //   RightMotors.setInverted(true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed)
  {
    Drive.tankDrive(leftSpeed, rightSpeed, false);
  }

  public void arcadeDrive(double xSpeed, double zRotation)
  {
    Drive.arcadeDrive(xSpeed, zRotation, false);
  }
  
  public void Stop()
  {
    Drive.stopMotor();
  }

  public void setSelectedSensorPosition(int sensorPos)
  {
      LeftMaster.setSelectedSensorPosition(sensorPos);
      RightMaster.setSelectedSensorPosition(sensorPos);
  }

  public double getSensorMetricPosition()
  {
    return (LeftMaster.getSelectedSensorPosition() + RightMaster.getSelectedSensorPosition()) / 2.0 / Wheel.UNITS_PER_ROUND * Wheel.WHEEL_RADIUS;
  }

  public void setMaxOutput(double maxOutput)
  {
    Drive.setMaxOutput(maxOutput);
  }

  public void resetGyro()
  {
    Gyro.reset();
  }

  public double getGyroYaw()
  {
    return Gyro.getYaw();
  }
  
  public double getTurnRate()
  {
    return Gyro.getRate();
  }

  public void setSafetyEnabled(boolean enabled) {
    Drive.setSafetyEnabled(enabled);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //SmartDashboard.putNumber("Left pos", getSensorMetricPosition());
  }

}
