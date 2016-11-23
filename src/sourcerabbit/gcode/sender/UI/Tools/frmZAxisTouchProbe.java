/*
Copyright (C) 2015  Nikos Siatras

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 SourceRabbit GCode Sender is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sourcerabbit.gcode.sender.UI.Tools;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.ConnectionHelper;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.Events.MachineStatusEvents.IMachineStatusEventListener;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.Events.MachineStatusEvents.MachineStatusEvent;
import sourcerabbit.gcode.sender.Core.CNCController.GCode.GCodeCommand;
import sourcerabbit.gcode.sender.Core.CNCController.GRBL.GRBLActiveStates;
import sourcerabbit.gcode.sender.Core.CNCController.GRBL.GRBLCommands;
import sourcerabbit.gcode.sender.Core.Threading.ManualResetEvent;
import sourcerabbit.gcode.sender.Core.CNCController.Position.Position2D;
import sourcerabbit.gcode.sender.Core.Settings.TouchProbeSettings;
import sourcerabbit.gcode.sender.UI.UITools.UITools;
import sourcerabbit.gcode.sender.UI.frmControl;

/**
 *
 * @author nsiatras
 */
public class frmZAxisTouchProbe extends javax.swing.JDialog
{

    private final frmControl fMyMain;
    private boolean fShowWarningIfNecessary = true;
    private boolean fMachineTouchedTheProbe = false;
    private final ManualResetEvent fWaitToTouchTheProbe = new ManualResetEvent(false);

    private IMachineStatusEventListener fIMachineStatusEventListener;

    public frmZAxisTouchProbe(frmControl parent, boolean modal)
    {
        super(parent, modal);
        fMyMain = parent;
        initComponents();

        // Set form in middle of frmControl
        Position2D pos = UITools.getPositionForDialogToOpenInMiddleOfParentForm(parent, this);
        this.setLocation((int) pos.getX(), (int) pos.getY());

        // Fix jSpinnerHeighOfProbe to work with system decimal point
        jSpinnerHeighOfProbe.setEditor(new JSpinner.NumberEditor(jSpinnerHeighOfProbe, "##.##"));
        UITools.FixSpinnerToWorkWithSystemDecimalPoint(jSpinnerHeighOfProbe);

        InitEvents();

        jSpinnerHeighOfProbe.setValue(TouchProbeSettings.getHeightOfProbe());

        UpdateUIOnMachineStatusChange(ConnectionHelper.ACTIVE_CONNECTION_HANDLER.getActiveState());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButtonTouch = new javax.swing.JButton();
        jLabelWarning = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinnerHeighOfProbe = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Z Axis Touch Probe");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosed(java.awt.event.WindowEvent evt)
            {
                formWindowClosed(evt);
            }
        });

        jButtonTouch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonTouch.setText("Touch the Probe");
        jButtonTouch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonTouchActionPerformed(evt);
            }
        });

        jLabelWarning.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelWarning.setForeground(new java.awt.Color(204, 0, 0));
        jLabelWarning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWarning.setText("The machine's status must be Idle to use the \"Touch Probe\" operation.");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourcerabbit/gcode/sender/UI/Images/ZTouchProbe/ZAxisTouchProbe.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 75, 127));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Endmill Distance from Probe");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 75, 127));
        jLabel15.setText("D:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("1mm to 50mm");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Set endmill 1mm to 50mm above the touch probe");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 75, 127));
        jLabel8.setText("H:");

        jSpinnerHeighOfProbe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinnerHeighOfProbe.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 500.0d, 0.10000000149011612d));
        jSpinnerHeighOfProbe.setValue(19.2);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 75, 127));
        jLabel7.setText("mm");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("The total height of the touch probe");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 75, 127));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Height of Probe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(10, 10, 10)
                                        .addComponent(jSpinnerHeighOfProbe, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel20)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonTouch, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jSpinnerHeighOfProbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jLabelWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTouch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InitEvents()
    {
        // Call UpdateUIOnMachineStatusChange with the current machine status
        UpdateUIOnMachineStatusChange(ConnectionHelper.ACTIVE_CONNECTION_HANDLER.getActiveState());

        // Machine status events
        ConnectionHelper.ACTIVE_CONNECTION_HANDLER.getMachineStatusEventsManager().AddListener(fIMachineStatusEventListener = new IMachineStatusEventListener()
        {
            @Override
            public void MachineStatusChanged(MachineStatusEvent evt)
            {
                final int activeState = evt.getMachineStatus();
                UpdateUIOnMachineStatusChange(activeState);
            }
        });

    }

    private void UpdateUIOnMachineStatusChange(int machineStatus)
    {
        switch (machineStatus)
        {
            case GRBLActiveStates.IDLE:
                jButtonTouch.setEnabled(true);
                jLabelWarning.setText("");
                break;

            case GRBLActiveStates.RUN:
                jButtonTouch.setEnabled(false);
                if (fShowWarningIfNecessary)
                {
                    jLabelWarning.setVisible(true);
                }
                break;
            case GRBLActiveStates.HOLD:
            case GRBLActiveStates.ALARM:
            case GRBLActiveStates.RESET_TO_CONTINUE:
                jButtonTouch.setEnabled(false);
                 jLabelWarning.setText("The machine's status must be Idle to use the \"Touch Probe\" operation.");
                fWaitToTouchTheProbe.Set();
                break;

            case GRBLActiveStates.MACHINE_TOUCHED_PROBE:
                fMachineTouchedTheProbe = true;
                fWaitToTouchTheProbe.Set();
                break;
        }
    }

    private void jButtonTouchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTouchActionPerformed
    {//GEN-HEADEREND:event_jButtonTouchActionPerformed

        // Check if Z is negative
        if (ConnectionHelper.ACTIVE_CONNECTION_HANDLER.getWorkPosition().getZ() < 0)
        {
            JOptionPane.showMessageDialog(this, "Your machine's Z axis level is bellow 0.\nSet your Z axis above 0 and try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (jButtonTouch.getText().equals("Click to Stop!"))
        {
            try
            {
                ConnectionHelper.ACTIVE_CONNECTION_HANDLER.SendDataImmediately_WithoutMessageCollector(GRBLCommands.COMMAND_SOFT_RESET);
            }
            catch (Exception ex)
            {
            }
            jButtonTouch.setText("Touch the Probe");
            return;
        }
        else
        {
            jButtonTouch.setText("Click to Stop!");
        }

        Thread th = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                final int distance = 200; // Travel 200mm MAX!
                final int feedRate = 80; // 80mm/min

                // Set last settings to TOUCH_PROBE_SETTINGS
                TouchProbeSettings.setDistanceFromProbe(distance);
                TouchProbeSettings.setHeightOfProbe((double) jSpinnerHeighOfProbe.getValue());

                try
                {
                    fShowWarningIfNecessary = false;

                    // Step 1
                    // Move the endmill towards the probe until they touch each other.
                    fWaitToTouchTheProbe.Reset();
                    fMachineTouchedTheProbe = false;
                    String response = MoveEndmillToProbe(distance, feedRate);
                    fWaitToTouchTheProbe.WaitOne();
                    if (!response.equals("ok") || !fMachineTouchedTheProbe)
                    {
                        MachineFailedToTouchTheProbe();
                        return;
                    }

                    // Step 2
                    // Move the endmill 0.5 mm back
                    String moveZStr = "G21G91G0Z0.5";
                    GCodeCommand moveZCommand = new GCodeCommand(moveZStr);
                    response = ConnectionHelper.ACTIVE_CONNECTION_HANDLER.SendGCodeCommandAndGetResponse(moveZCommand);
                    if (!response.equals("ok"))
                    {
                        MachineFailedToTouchTheProbe();
                        return;
                    }

                    // Step 3
                    // Touch the probe with slow feedrate
                    fWaitToTouchTheProbe.Reset();
                    fMachineTouchedTheProbe = false;
                    response = MoveEndmillToProbe(1, 30);
                    fWaitToTouchTheProbe.WaitOne();
                    if (!response.equals("ok") || !fMachineTouchedTheProbe)
                    {
                        MachineFailedToTouchTheProbe();
                        return;
                    }
                    else
                    {
                        // The endmill touched the touch probe twice !
                        // Set the Z position equal to the probe height
                        fMachineTouchedTheProbe = false;

                        try
                        {
                            double probeHeight = (double) jSpinnerHeighOfProbe.getValue();

                            String response2 = SetZAxisPosition(probeHeight);
                            if (response2.equals("ok"))
                            {
                                MachineTouchedTheProbeSucessfully();
                            }
                            else
                            {
                                MachineFailedToTouchTheProbe();
                            }
                        }
                        catch (Exception ex)
                        {
                            MachineFailedToTouchTheProbe();
                        }
                    }

                }
                catch (Exception ex)
                {
                    MachineFailedToTouchTheProbe();
                }
            }
        });
        th.start();
    }//GEN-LAST:event_jButtonTouchActionPerformed

    /**
     * Move the endmill to touch the probe
     *
     * @param distance
     * @param feedRate
     * @return
     */
    private String MoveEndmillToProbe(int distance, int feedRate)
    {
        fMachineTouchedTheProbe = false;
        String gCodeStr = "G38.2Z-" + distance + "F" + feedRate;
        final GCodeCommand command = new GCodeCommand(gCodeStr);

        return ConnectionHelper.ACTIVE_CONNECTION_HANDLER.SendGCodeCommandAndGetResponse(command);
    }

    private String SetZAxisPosition(double value)
    {
        String commandStr = "G92 X0 Y0 Z" + String.valueOf(value);
        GCodeCommand commandSetZ = new GCodeCommand(commandStr);
        if (ConnectionHelper.ACTIVE_CONNECTION_HANDLER.SendGCodeCommandAndGetResponse(commandSetZ).equals("ok"))
        {
            // All good!
            // Move the Z 0.5mm higher
            String moveZStr = "G21G91G0Z0.5";
            GCodeCommand moveZCommand = new GCodeCommand(moveZStr);
            return ConnectionHelper.ACTIVE_CONNECTION_HANDLER.SendGCodeCommandAndGetResponse(moveZCommand);
        }
        else
        {
            return "ERROR";
        }
    }

    private void MachineFailedToTouchTheProbe()
    {
        fShowWarningIfNecessary = true;
        JOptionPane.showMessageDialog(this, "Machine failed to touch the probe!", "Error", JOptionPane.ERROR_MESSAGE);
        jButtonTouch.setText("Touch the Probe");
    }

    private void MachineTouchedTheProbeSucessfully()
    {
        fShowWarningIfNecessary = false;
        JOptionPane.showMessageDialog(this, "Machine touched the probe sucessfully!");
        jButtonTouch.setText("Touch the Probe");
    }


    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
        ConnectionHelper.ACTIVE_CONNECTION_HANDLER.getMachineStatusEventsManager().RemoveListener(fIMachineStatusEventListener);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonTouch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelWarning;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinnerHeighOfProbe;
    // End of variables declaration//GEN-END:variables
}
