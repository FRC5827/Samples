package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class SampleFalcon500Subsystem extends SubsystemBase {
    private final WPI_TalonFX m_motor = new WPI_TalonFX (48);
    private final PIDController m_pid = new PIDController(.3, 0.01, 0.01 );

    public double m_speed;

    public SampleFalcon500Subsystem() {
        m_motor.setNeutralMode(NeutralMode.Coast);
        m_pid.setSetpoint(0);
    }

    @Override
    public void periodic()
    {
        double motorSpeed = m_motor.get();
        double calculatedSpeed = m_pid.calculate(motorSpeed);
        m_motor.set(ControlMode.PercentOutput, calculatedSpeed);
    }

    public void setSpeed(double speed) {
        double calculatedSpeed = MathUtil.clamp(speed, -1, 1);    
        m_speed = calculatedSpeed;
        m_pid.setSetpoint(calculatedSpeed);
    }
}
