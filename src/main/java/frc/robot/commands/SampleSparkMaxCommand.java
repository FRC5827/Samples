// Copyright (c) Code Purple - Team 5827, FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.SampleSparkMaxSubsystem;

public class SampleSparkMaxCommand extends CommandBase {

    private final SampleSparkMaxSubsystem m_subsystem;

    private final XboxController m_controller;

    private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(Constants.kJoystickSlewRate);

    public SampleSparkMaxCommand(SampleSparkMaxSubsystem subsystem, XboxController controller)
    {
        addRequirements(subsystem);

        m_subsystem = subsystem;
        m_controller = controller;
    }

    @Override
    public void execute() {
        double xAxis = m_controller.getRawAxis(XboxController.Axis.kLeftX.value);

        // apply deadband
        xAxis = (Math.abs(xAxis) < Constants.kJoystickDeadband) ? 0 : xAxis;

        // curve inputs
        xAxis = xAxis * xAxis * xAxis;

        // slew rate limiter
        xAxis = xSpeedLimiter.calculate(xAxis);
        
        m_subsystem.setSpeed(xAxis);
    }
}
