// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;

public class ArmSeq extends Command {
    Arm arm;
    Drivetrain drive;
    double angle;
    boolean done;
    double count;
    final double FINISHED_COUNT = 100;
  
  /**
   * Creates a new ArmSeq. This command will drive your your robot for a desired distance at
   * a desired speed.
   *
   * @param speed The speed at which the robot will drive
   * @param inches The number of inches the robot will drive
   * @param drive The drivetrain subsystem on which this command will run
   */
    public ArmSeq(Arm arm, Drivetrain drive, double angle){
    this(arm, angle);
    this.drive = drive;
  }
  public ArmSeq(Arm arm, double angle) {
    this.arm = arm;
    this.angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    count = 0;
    arm.setAngle(angle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(arm.atAngle()){
        count++;
    }
    if(count > FINISHED_COUNT){
       done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.setAngle(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
