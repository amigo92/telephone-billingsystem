package sg.edu.nus.iss.billsys.tools;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public abstract class GuiOkCancelDialog extends JDialog {

    public GuiOkCancelDialog (JFrame parent, String title) {
        super (parent, title);
        
        add ("Center", createFormPanel());
        add ("South",  createButtonPanel());
        
        Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    this.setLocation(screenSize.width/2-400, screenSize.height/2-300);
    }

    public GuiOkCancelDialog (JFrame parent) {
        this (parent, "");
    }

    private JPanel createButtonPanel () {
        JPanel p = new JPanel ();

        JButton b;
        ActionListener l;

        b = new JButton ("OK");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                boolean success = performOkAction ();
                if (success) {
                    destroyDialog ();
                }
            }
        };
        b.addActionListener (l);
        p.add (b);

        b = new JButton ("Cancel");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                destroyDialog ();
            }
        };
        b.addActionListener (l);
        p.add (b);

        return p;
    }

    private void destroyDialog () {
        setVisible (false);
        dispose();
    }

    protected abstract JPanel createFormPanel () ;

    protected abstract boolean performOkAction () ;

}
