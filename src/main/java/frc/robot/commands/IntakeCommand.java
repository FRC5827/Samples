// Copyright (c) Code Purple - Team 5827, FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeCommand extends Command {

    private final ShooterSubsystem m_subsystem;

    private final Timer m_timer;
    private final double m_maxSpeed;

    public IntakeCommand(ShooterSubsystem subsystem, double maxSpeed)
    {
        addRequirements(subsystem);

        m_subsystem = subsystem;
        m_maxSpeed = maxSpeed;

        m_timer = new Timer();
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();

        double intakeSpeed = SmartDashboard.getNumber("intakeSpeed", m_maxSpeed);

        m_subsystem.setSpeed(intakeSpeed);
    }

    @Override
    public void execute() {
        if (this.isFinished()) {
            m_subsystem.setSpeed(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.setSpeed(0);

        m_timer.stop();
    }

    @Override
    public boolean isFinished() {
        return m_timer.hasElapsed(1.0);
    }    
}
