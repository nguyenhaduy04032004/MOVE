package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivebase;
import static frc.robot.Constants.Drive.*;


public class DriveNMeters extends CommandBase {
  /**
   * Creates a new DriveNMeters.
   */
  double l;
  double s;
  Drivebase __drivebase;
  //boolean isNegative = false;
  boolean set = false;

  double factor = 1;

  double I = 0;

  double ok=0;

  PIDController angle_Controller = new PIDController(angle_kP, angle_kI, angle_kD);
  PIDController distance_Controller = new PIDController(distance_kP, distance_kI, distance_kD);

  public DriveNMeters(Drivebase drivebase, final double length, final double speed) {
    l = length;
    s = speed;
    //isNegative = (length < 0);
    //if (isNegative) s = -s;
    // Use addRequirements() here to declare subsystem dependencies.
    __drivebase = drivebase;
    set = false;
    factor = 1;
    addRequirements(__drivebase);
  }
  double init_angle;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
      init_angle = __drivebase.getGyroYaw();
    __drivebase.setSelectedSensorPosition(0);
    angle_Controller.reset();
    distance_Controller.reset();
    ok=0;

  }
  double prev_s = s;
  double error;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    error = init_angle - __drivebase.getGyroYaw();
    double PI = angle_Controller.output(error);
    SmartDashboard.putNumber("Metric Pos", __drivebase.getSensorMetricPosition());
    double current_speed = s * distance_Controller.output(l - Math.abs(__drivebase.getSensorMetricPosition()));
    //prev_s = current_speed;
    SmartDashboard.putNumber("Current speed: ", current_speed);
    SmartDashboard.putNumber("Current error: ", error);
    __drivebase.tankDrive(current_speed+PI, current_speed-PI);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    __drivebase.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return (l-Math.abs(__drivebase.getSensorMetricPosition()) < distanceErrorTolerance && error < angleErrorTolerence);
  }
}