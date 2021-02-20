// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

import java.util.Timer;
import java.util.TimerTask;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  DoubleSolenoid intakeSolenoid;
  Joystick driverJoy;
  WPI_VictorSPX intakeMotor;
  long delayToOffSolenoid;


  public Intake() {
    intakeSolenoid = new DoubleSolenoid(PortMap.kIntakeSolenoidA,PortMap.kIntakeSolenoidB);
    intakeMotor = new WPI_VictorSPX(PortMap.kIntakeMotor);

    delayToOffSolenoid = 1000;

    intakeSolenoid.set(Value.kOff);
  }

  // Zamyka intake
  public void intakeClose() {
    intakeSolenoid.set(Value.kReverse);
    
    TimerTask task = new TimerTask() {
      public void run() {
        intakeSolenoid.set(Value.kOff);
      }
    };

    Timer timer = new Timer();
    timer.schedule(task, delayToOffSolenoid);
  }

  // Otwiera Intake
  public void intakeOpen() {
    intakeSolenoid.set(Value.kForward);
    
    TimerTask task = new TimerTask() {
      public void run() {
        intakeSolenoid.set(Value.kOff);
      }
    };

    Timer timer = new Timer();
    timer.schedule(task, delayToOffSolenoid);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
