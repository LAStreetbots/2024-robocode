package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase {

  private CANSparkMax m_topMotor;
  private CANSparkMax m_bottomMotor;

  private boolean m_launcherRunning;
  private boolean m_launcherAMPRunning;

  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {
    // create two new SPARK MAXs and configure them
    m_topMotor =
        new CANSparkMax(Constants.Launcher.kTopCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_topMotor.setInverted(false);
    m_topMotor.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_topMotor.setIdleMode(IdleMode.kCoast);

    m_topMotor.burnFlash();

    m_bottomMotor =
        new CANSparkMax(Constants.Launcher.kBottomCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_bottomMotor.setInverted(false);
    m_bottomMotor.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_bottomMotor.setIdleMode(IdleMode.kCoast);

    m_bottomMotor.burnFlash();

    m_launcherRunning = false;
    m_launcherAMPRunning = false;
  }

  /**
   * Turns the launcher on. Can be run once and the launcher will stay running or run continuously
   * in a {@code RunCommand}.
   */
  public void runLauncher() {
    m_launcherRunning = true;
    m_launcherAMPRunning = false;
  }

  public void runLauncherWithPower(){

    m_launcherRunning = false;
    m_launcherAMPRunning = true;


    }
  /**
   * Turns the launcher off. Can be run once and the launcher will stay running or run continuously
   * in a {@code RunCommand}.
   */
  public void stopLauncher() {
    m_launcherRunning = false;
    m_launcherAMPRunning = false;
  }

  @Override
  public void periodic() { // this method will be called once per scheduler run
    // set the launcher motor powers based on whether the launcher is on or not
    
    if (m_launcherRunning) { 
      if (m_launcherAMPRunning) { 
        m_topMotor.set(Constants.Launcher.kTopAmpPower); 
        m_bottomMotor.set(Constants.Launcher.kBottomAmpPower); 
      } else { m_topMotor.set(Constants.Launcher.kTopPower); 
        m_bottomMotor.set(Constants.Launcher.kBottomPower); 
      } 
    } else 
    { m_topMotor.set(0.0); 
      m_bottomMotor.set(0.0); }
    
  
    //Code before the one above Incase it gives an error

    //if (m_launcherRunning) {
     // m_topMotor.set(Constants.Launcher.kTopPower);
      //m_bottomMotor.set(Constants.Launcher.kBottomPower);
    //} else {
      //m_topMotor.set(0.0);
      //m_bottomMotor.set(0.0);
   
  }
}

