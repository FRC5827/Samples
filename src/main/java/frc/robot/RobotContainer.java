// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RunMotorCommand;
import frc.robot.commands.SampleFalcon500Command;
import frc.robot.commands.SampleSparkMaxCommand;
import frc.robot.subsystems.SampleFalcon500Subsystem;
import frc.robot.subsystems.SampleSparkMaxSubsystem;

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

    private final SampleFalcon500Subsystem m_subsystem1;
    private final SampleSparkMaxSubsystem m_subsystem2;
    
    /**
     * The container for the robot. Contains subsystems, IO devices, and commands.
     */
    public RobotContainer() {
        m_subsystem1 = new SampleFalcon500Subsystem();
        m_subsystem1.setDefaultCommand(new SampleFalcon500Command(m_subsystem1, m_driverInput));

        m_subsystem2 = new SampleSparkMaxSubsystem();
        m_subsystem2.setDefaultCommand(new SampleSparkMaxCommand(m_subsystem2, m_driverInput));

        var button = new JoystickButton(m_driverInput, XboxController.Button.kA.value);
        button.whileTrue(new RunMotorCommand(m_subsystem1));
    }
}
