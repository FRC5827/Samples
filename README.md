# Sample Code for Robotics Training

**Prerequisites**
----
In order to compile, there are a number of prerequisites:

WPILib and VSCode:
https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html

CTRE Phoenix library must be installed and enabled as an offline vendor dependency:
https://github.com/CrossTheRoadElec/Phoenix-Releases/releases
</br>

Other vendor dependencies need to be installed using WPILib's online install in VSCode. Click WPI button, then choose "Manage Vendor Libraries", then "Install new libraries (online)" and enter the following URLs:

CTRE Phoenix libraries (if not installed with full install package above)
https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix5-frc2023-latest.json

REV Robotics (Spark Max and NEO, [Code Release](https://github.com/REVrobotics/REV-Software-Binaries/releases/tag/revlib-2023.1.1)):
https://software-metadata.revrobotics.com/REVLib-2023.json

PathPlannerLib:
https://3015rangerrobotics.github.io/pathplannerlib/PathplannerLib.json

navX-MXP (Gyro): 
https://dev.studica.com/releases/2023/NavX.json

</br>

# Connecting to the Robot

The robot's WIFI ID is **5827**. Connect your laptop's Wifi to **5827**.

## Connecting to Limelight.

Open a browser and point it to `http://10.58.27.11:5801/`.

# Hardware Setup

You don't need to these, unless you're dealing with old hardware, or want to reuse hardware. There are here for reference.

## Flashing RoboRIO

1. PLUG THE USB-A CABLE INTO THE USB PORT AND CONNECT TO COMPUTER
2. OPEN THE ROBORIO IMAGING TOOL
3. PUT TEAM NUMBER(5827) IN THE TEAM NUMBER BOX
4. CONNECT TO ROBORIO
5. SELECT DESIRED IMAGE IN THE SELECT IMAGE BOX
6. SELECT THE REFORMAT BUTTON AND WAIT UNTIL ROBORIO IS COMPLETELY FLASHED
YOU HAVE SUCCESSFULLY FLASHED A ROBORIO
FOR MORE INFORMATION GO TO https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/imaging-your-roborio.html

## Flashing and Programming Wireless Radio (OM5P-AN)

The code set of instructions can be found here: https://docs.wpilib.org/en/latest/docs/zero-to-robot/step-3/radio-programming.html

If you're having trouple flashing the Robo RIO, these instructions can be used to flash the firmware manually: https://www.chiefdelphi.com/t/frc-radio-configuration-application-bug-error-finding-npf-device-name-for-adapter-solved/339143

Note:
- Disabling all network drivers except the one you're going to use to flash or configure the device really helps. Simple disable everything else through the Device Manager.
- If you're going to flash the device, consider doing through the command-line (as described in the link). Simply:
  - Open a command-line
  - Go to `C:\Program Files (x86)\FRC Radio Configuration Utility`
  - Run `ap51-flash.exe` to get a list of network adapters
  - Figure out the number of the ethernet adapter you're going to use. A good trick is to run the tool twice, one with the adapter enabled, one disabled, to see what changes.
  - Disconnect the ethernet cable to the radio/device.
  - Run `ap51-flash.exe <adapter-no> <firmware-file>` (e.g. `ap51-flash.exe 3 firmwareOM5PAN.bin`).
  - Reconnect the ethernet cable. Once the device boots up the program should start flashing the new firmware.
