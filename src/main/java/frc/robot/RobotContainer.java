// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SampleFalcon500Command;
import frc.robot.commands.SampleSparkMaxCommand;
import frc.robot.commands.SampleTalonSrxCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SampleSparkMaxSubsystem;
import frc.robot.subsystems.SampleTalonSrxSubsystem;

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

    private final ShooterSubsystem m_shooterMotors;
    
    /**
     * The container for the robot. Contains subsystems, IO devices, and commands.
     */
    public RobotContainer() {

        double shooterSpeed = 0.65;
        double intakeSpeed = -0.2;

        SmartDashboard.putNumber("shooterSpeed", shooterSpeed);
        SmartDashboard.putNumber("intakeSpeed", intakeSpeed);

        // Shooter motors
        m_shooterMotors = new ShooterSubsystem();
        m_shooterMotors.setDefaultCommand(new SampleFalcon500Command(m_shooterMotors, m_driverInput));

        var kA_button = new JoystickButton(m_driverInput, XboxController.Button.kA.value);
        kA_button.whileTrue(new ShooterCommand(m_shooterMotors, shooterSpeed));

        var kX_button = new JoystickButton(m_driverInput, XboxController.Button.kX.value);
        kX_button.whileTrue(new IntakeCommand(m_shooterMotors, intakeSpeed));
    }
}
