/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Webserver extends JFrame
{

    JPanel panelMain = new JPanel();
    JScrollPane scrollPanelMain = new JScrollPane();
    JTextArea TextAreaMain = new JTextArea();
    static Integer portIn = null;

    public Webserver()
    {
        //Fenster initialisieren
        try
        {
            TextAreaMain.setBackground(new Color(00, 00, 00));
            TextAreaMain.setForeground(new Color(0, 255, 100));
            TextAreaMain.setBorder(BorderFactory.createLoweredBevelBorder());
            TextAreaMain.setToolTipText("");
            TextAreaMain.setEditable(true);
            TextAreaMain.setColumns(63);
            TextAreaMain.setRows(19);
            this.setTitle("Webserver");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            scrollPanelMain.getViewport().add(TextAreaMain);
            panelMain.add(scrollPanelMain);
            this.getContentPane().add(panelMain, BorderLayout.EAST);
            this.setVisible(true);
            this.setSize(718, 348);
            this.setResizable(false);
            this.validate();

            new Server(portIn.intValue(), this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            portIn = new Integer(args[0]);
        }
        catch (Exception e)
        {
            portIn = new Integer(80);
        }
        Webserver webserver = new Webserver();
    }
    
    public void send_message_to_window(String s) 
    {
           TextAreaMain.append(s);
      }
}
