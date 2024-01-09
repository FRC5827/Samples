package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class SampleFalcon500Subsystem extends SubsystemBase {
    private final WPI_TalonFX m_motor = new WPI_TalonFX (48);
    private final PIDController m_pid = new PIDController(.3, 0, 0 );

    private final WPI_TalonFX m_motor2 = new WPI_TalonFX (49);
    private final PIDController m_pid2 = new PIDController(.3, 0, 0 );


    public SampleFalcon500Subsystem() {
        m_motor.setNeutralMode(NeutralMode.Brake);
        m_pid.setSetpoint(0);
        m_motor2.setNeutralMode(NeutralMode.Brake);
        m_pid2.setSetpoint(0);
    }

    @Override
    public void periodic()
    {
        double motorSpeed = m_motor.get();
        double calculatedSpeed = m_pid.calculate(motorSpeed);
        m_motor.set(ControlMode.PercentOutput, calculatedSpeed);

        double motorSpeed2 = m_motor2.get();
        double calculatedSpeed2 = m_pid2.calculate(motorSpeed2);
        m_motor2.set(ControlMode.PercentOutput, calculatedSpeed2);
    }

    public void setSpeed(double speed) {
        double calculatedSpeed = MathUtil.clamp(speed, -1, 1);    
        m_pid.setSetpoint(calculatedSpeed);
        m_pid2.setSetpoint(calculatedSpeed);
    }
}
