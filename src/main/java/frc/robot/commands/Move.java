/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivebase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Move extends SequentialCommandGroup {
  /**
   * Creates a new Move.
   */
  public Move(Drivebase drivebase, double x, double y) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    // Add your commands in the super() call, e.g.
    super();

    // goc giua (a, 0) va (x, y)
    double angle = Math.toDegrees(Math.atan(y/x));
    addCommands(new TurnToAngle(drivebase, angle));
    addCommands(new DriveNMeters(drivebase, Math.sqrt(x*x+y*y), 0.4));
  }
}
