// Copyright (c) Code Purple - Team 5827, FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class SampleFalcon500Command extends Command {

    private final ShooterSubsystem m_subsystem;
    
    private final XboxController m_controller;

    private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(Constants.kJoystickSlewRate);

    public SampleFalcon500Command(ShooterSubsystem subsystem, XboxController controller)
    {
        addRequirements(subsystem);

        m_subsystem = subsystem;
        m_controller = controller;
    }

    @Override
    public void execute() {
        
        double yAxis = m_controller.getRawAxis(XboxController.Axis.kLeftY.value);

        // apply deadband
        yAxis = (Math.abs(yAxis) < Constants.kJoystickDeadband) ? 0 : yAxis;

        // curve inputs
        yAxis = yAxis * yAxis * yAxis;

        // slew rate limiter
        yAxis = ySpeedLimiter.calculate(yAxis);
        
        m_subsystem.setSpeed(yAxis);
    }
}
