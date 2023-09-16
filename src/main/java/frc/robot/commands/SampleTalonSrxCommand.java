// Copyright (c) Code Purple - Team 5827, FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.SampleTalonSrxSubsystem;

public class SampleTalonSrxCommand extends CommandBase {

    private final SampleTalonSrxSubsystem m_subsystem;

    private final XboxController m_controller;

    private final SlewRateLimiter rSpeedLimiter = new SlewRateLimiter(Constants.kJoystickSlewRate);

    // Driver control
    public SampleTalonSrxCommand(SampleTalonSrxSubsystem subsystem, XboxController controller)
    {
        addRequirements(subsystem);

        m_subsystem = subsystem;
        m_controller = controller;
    }

    @Override
    public void execute() {

        double rAxis = m_controller.getRawAxis(XboxController.Axis.kRightX.value);

        // apply deadband
        rAxis = (Math.abs(rAxis) < Constants.kJoystickDeadband) ? 0 : rAxis;

        // curve inputs
        rAxis = rAxis * rAxis * rAxis;

        // slew rate limiter
        rAxis = rSpeedLimiter.calculate(rAxis);
        
        m_subsystem.setSpeed(rAxis);
    }
}
