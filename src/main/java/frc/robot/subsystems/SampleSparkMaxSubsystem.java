package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class SampleSparkMaxSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor;
    private final SparkMaxPIDController m_pidController;
    private final RelativeEncoder m_encoder;

    public double m_speed;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    
    public SampleSparkMaxSubsystem() {
        
        m_motor = new CANSparkMax(47, MotorType.kBrushless);
        m_pidController = m_motor.getPIDController();
        m_encoder = m_motor.getEncoder();

        // kP = 6e-5; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;
    
        // set PID coefficients
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);
    
        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);
    }

    @Override
    public void periodic()
    {
            // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { m_pidController.setP(p); kP = p; }
        if((i != kI)) { m_pidController.setI(i); kI = i; }
        if((d != kD)) { m_pidController.setD(d); kD = d; }
        if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
        if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
        if((max != kMaxOutput) || (min != kMinOutput)) { 
            m_pidController.setOutputRange(min, max); 
            kMinOutput = min; kMaxOutput = max; 

           m_pidController.setReference(m_speed, CANSparkMax.ControlType.kVelocity);                
        }

        double setPoint = m_speed * maxRPM;
        m_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        
        SmartDashboard.putNumber("SetPoint", setPoint);
        SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());
    }

    public void setSpeed(double speed) {
        m_speed = speed;
    }
}
