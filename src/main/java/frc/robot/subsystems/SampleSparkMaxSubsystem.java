package frc.robot.subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.ClosedLoopSlot;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public final class SampleSparkMaxSubsystem extends SubsystemBase {
    private final SparkMax m_motor;
    private final SparkMaxConfig m_config;
    private final RelativeEncoder m_encoder;
    private final SparkClosedLoopController m_pidController;

    public double m_speed;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    
    public SampleSparkMaxSubsystem() {
        m_motor = new SparkMax(47, MotorType.kBrushless);
        m_config = new SparkMaxConfig();
        m_encoder = m_motor.getEncoder();
        m_pidController = m_motor.getClosedLoopController();

        // kP = 6e-5; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;
    
        // set PID coefficients
        m_config.idleMode(IdleMode.kBrake);
        m_config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                           .pidf(kP, kI, kD, kFF, ClosedLoopSlot.kSlot0)
                           .iZone(kIz)
                           .outputRange(kMinOutput, kMaxOutput);
        m_motor.configure(m_config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
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
        if(p != kP || i != kI || d != kD || iz != kIz || ff != kFF || max != kMaxOutput || min != kMinOutput) {
            kP = p;
            kI = i;
            kD = d;
            kIz = iz;
            kFF = ff;
            kMaxOutput = max;
            kMinOutput = min;
            m_config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                           .pidf(kP, kI, kD, kFF, ClosedLoopSlot.kSlot0)
                           .iZone(kIz)
                           .outputRange(kMinOutput, kMaxOutput);
            m_motor.configure(m_config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        }
        
        SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());
    }

    public void setSpeed(double speed) {
        m_speed = speed;
        m_pidController.setReference(speed * maxRPM, ControlType.kVelocity);
        SmartDashboard.putNumber("SetPoint", speed * maxRPM);
    }
}
