package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class SampleTalonFXSubsystem extends SubsystemBase {
    private final TalonFX m_motor;
    private final TalonFXConfiguration m_config;
    private final VelocityVoltage m_velocityRequest;

    public double m_speed;
    public double kP, kI, kD;

    public SampleTalonFXSubsystem() {
        m_motor = new TalonFX(48);
        m_config = new TalonFXConfiguration();
        m_velocityRequest = new VelocityVoltage(0);
        
        kP = .3;
        kI = 0;
        kD = 0;

        m_config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        m_config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;
        m_config.Slot0.withKP(kP).withKI(kI).withKD(kD);

        m_motor.setControl(m_velocityRequest);
    }

    @Override
    public void periodic() {
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if(p != kP || i != kI || d != kD) {
            kP = p;
            kI = i;
            kD = d;
            m_config.Slot0.withKP(kP).withKI(kI).withKD(kD);
            m_motor.setControl(m_velocityRequest);
        }
    }

    public void setSpeed(double speed) {
        m_motor.setControl(m_velocityRequest.withVelocity(speed));
    }
}
