// Copyright (c) Code Purple - Team 5827, FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.SampleFalcon500Subsystem;

public class SampleFalcon500Command extends CommandBase {

    private final XboxController m_controller;
    private final int m_translationAxis;
    private final int m_strafeAxis;
    private final int m_rotationAxis;

    private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(Constants.kJoystickSlewRate);
    private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(Constants.kJoystickSlewRate);
    private final SlewRateLimiter rSpeedLimiter = new SlewRateLimiter(Constants.kJoystickSlewRate);

    SampleFalcon500Subsystem m_subsystem;

    // Driver control
    public SampleFalcon500Command(SampleFalcon500Subsystem subsystem, XboxController controller)
    {
        addRequirements(subsystem);

        m_subsystem = subsystem;
        m_controller = controller;
        m_translationAxis = XboxController.Axis.kLeftY.value;
        m_strafeAxis = XboxController.Axis.kLeftX.value;
        m_rotationAxis = XboxController.Axis.kRightX.value;
    }

    @Override
    public void execute() {
        // xbox controller provides negative values when pushing forward (y axis), so negate
        // negate strafe (left/right x axis stick) as we want positive when pushing left (positive y on field)
        // negate rotation as we want positive value when when pushing left (CCW is postive)
        double yAxis = -m_controller.getRawAxis(m_translationAxis);
        double xAxis = -m_controller.getRawAxis(m_strafeAxis);
        double rAxis = -m_controller.getRawAxis(m_rotationAxis);

        // apply deadband
        yAxis = (Math.abs(yAxis) < Constants.kJoystickDeadband) ? 0 : yAxis;
        xAxis = (Math.abs(xAxis) < Constants.kJoystickDeadband) ? 0 : xAxis;
        rAxis = (Math.abs(rAxis) < Constants.kJoystickDeadband) ? 0 : rAxis;

        // curve inputs
        yAxis = Math.abs(yAxis) * Math.abs(yAxis) * yAxis;
        xAxis = Math.abs(xAxis) * Math.abs(xAxis) * xAxis;
        rAxis = Math.abs(rAxis) * Math.abs(rAxis) * rAxis;

        // uncomment to scale down output
        //yAxis *= 0.5;
        //xAxis *= 0.5;
        //rAxis *= 0.5;

        // slew rate limiter
        yAxis = ySpeedLimiter.calculate(yAxis);
        xAxis = xSpeedLimiter.calculate(xAxis);
        rAxis = rSpeedLimiter.calculate(rAxis);
        
        m_subsystem.setSpeed(yAxis);
    }
}
