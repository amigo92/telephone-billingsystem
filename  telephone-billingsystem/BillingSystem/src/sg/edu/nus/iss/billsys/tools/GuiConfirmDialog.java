package sg.edu.nus.iss.billsys.tools;


import javax.swing.*;

public abstract class GuiConfirmDialog extends GuiOkCancelDialog {

    private JLabel       messageLabel;

    public GuiConfirmDialog (JFrame parent, String title, String message) {
        super (parent, title);
        messageLabel.setText (message);
    }

    protected JPanel createFormPanel () {
        JPanel p = new JPanel ();
        messageLabel = new JLabel ();
        p.add (messageLabel);
        return p;
    }

}