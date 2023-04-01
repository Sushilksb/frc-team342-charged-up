// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.gripper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AddressableLEDSubsystem;
import frc.robot.subsystems.GripperSystem;
import frc.robot.subsystems.AddressableLEDSubsystem.ColorType;

public class Hold extends CommandBase {
  private GripperSystem gripperSubsystem;
  private AddressableLEDSubsystem aLedSubsystem;

  /** hold game piece, display led if game piece is in gripper */
  public Hold(GripperSystem gripperSubSystem, AddressableLEDSubsystem aLedSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.gripperSubsystem = gripperSubSystem;
    this.aLedSubsystem = aLedSubsystem;

    addRequirements(gripperSubSystem);
    addRequirements(aLedSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // check whether a piece has been intaked since last outtake
    if (gripperSubsystem.isHolding()) {
      // hold game piece in intake
      gripperSubsystem.spin(0.15);

      // if there is a game piece in the intake, display the red driver light
      if (gripperSubsystem.getCurrentPosition() <= (gripperSubsystem.getLastPosition() + 0.5)) {
        aLedSubsystem.driverColorMethod(ColorType.RED);
        System.out.print("HOLD");
      } else {
        System.out.print("NO");
        aLedSubsystem.LEDOff();
      }
    } else {
      // otherwise just don't spin
      gripperSubsystem.spin(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
