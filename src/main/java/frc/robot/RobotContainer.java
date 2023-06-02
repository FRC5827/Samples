// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.SampleFalcon500Command;
import frc.robot.subsystems.SampleFalcon500Subsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // Controllers
    private final XboxController m_driverInput = new XboxController(0);

    private final SampleFalcon500Subsystem m_subsystem;

    /**
     * The container for the robot. Contains subsystems, IO devices, and commands.
     */
    public RobotContainer() {
        m_subsystem = new SampleFalcon500Subsystem();
        m_subsystem.setDefaultCommand(new SampleFalcon500Command(m_subsystem, m_driverInput));
    }
}
