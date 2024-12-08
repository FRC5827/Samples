// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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

    //Class fields go here - hint: what needs to be run and what do we use to run it?

    /**
     * The container for the robot. Contains subsystems, IO devices, and commands.
     * What does a constructor have to do to the class fields?
     */
    public RobotContainer() {
        

        bindButtons(); //Leave this here, this is to make sure that your bindings are used
    }

    /**
     * configureButtonBindings method to create the button bindings
     * Hint: Use the special things that are not booleans (We went on about this for a while in the meetings) to bind buttons
     */
    private void bindButtons(){

    }
}
