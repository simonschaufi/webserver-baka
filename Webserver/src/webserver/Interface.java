/*
 * Interface.java
 *
 * Created on 20. Juni 2007, 10:45
 */

package webserver;

import java.awt.*;
import java.util.StringTokenizer;
import java.io.*;

/**
 *
 * @author  skolbens
 * Diese Klasse bildet die Oberfläche
 */
public class Interface extends javax.swing.JFrame {
    
    /** Creates new form Interface */
    Server s = null;
    public Interface() {
        super("FaSeSte Web-Server 1.0");
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Host2RootLabel = new javax.swing.JLabel();
        AddNewHost2Root = new javax.swing.JButton();
        DeleteHost2Root = new javax.swing.JButton();
        PortLabel = new javax.swing.JLabel();
        SetPort = new javax.swing.JButton();
        HostText = new java.awt.TextField();
        RootText = new java.awt.TextField();
        PortText = new java.awt.TextField();
        Host2RootList = new java.awt.List();
        Information = new java.awt.Label();
        Portinformation = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        StopServer = new javax.swing.JButton();
        RunServer = new javax.swing.JButton();
        status = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                WindowStartet(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("server settings"));

        Host2RootLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        Host2RootLabel.setText("host 2 root list");

        AddNewHost2Root.setText("add new");
        AddNewHost2Root.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewHost2RootMouseClicked(evt);
            }
        });

        DeleteHost2Root.setText("delete");
        DeleteHost2Root.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteHost2Root_Click(evt);
            }
        });

        PortLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        PortLabel.setText("port");

        SetPort.setText("set");
        SetPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SetPort_Click(evt);
            }
        });

        HostText.setFont(new java.awt.Font("Dialog", 2, 12));
        HostText.setForeground(new java.awt.Color(204, 204, 255));
        HostText.setText("host");
        HostText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HostText_Click(evt);
            }
        });
        HostText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                HostText_LostFocus(evt);
            }
        });

        RootText.setFont(new java.awt.Font("Dialog", 2, 12));
        RootText.setForeground(new java.awt.Color(204, 204, 255));
        RootText.setText("root");
        RootText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RootText_Click(evt);
            }
        });
        RootText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                RootText_LostFocus(evt);
            }
        });

        PortText.setFont(new java.awt.Font("Dialog", 2, 12));
        PortText.setForeground(new java.awt.Color(204, 204, 255));
        PortText.setText("standard port 80 is set");
        PortText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PortText_Click(evt);
            }
        });
        PortText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PortText_FocusLost(evt);
            }
        });

        Information.setForeground(new java.awt.Color(255, 0, 51));

        Portinformation.setForeground(new java.awt.Color(255, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(Host2RootLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .add(HostText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(RootText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 114, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(PortText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                    .add(Information, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .add(Host2RootList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(Portinformation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(SetPort, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(DeleteHost2Root, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(AddNewHost2Root, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(PortLabel))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(Host2RootLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(Host2RootList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 228, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 13, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, HostText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, RootText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(DeleteHost2Root)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 215, Short.MAX_VALUE)
                        .add(AddNewHost2Root)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Information, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(PortLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(PortText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(2, 2, 2)
                        .add(Portinformation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(SetPort))
                .add(21, 21, 21))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("server control"));

        StopServer.setText("stop server");
        StopServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StopServerMouseClicked(evt);
            }
        });

        RunServer.setText("run server");
        RunServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RunServerMouseClicked(evt);
            }
        });

        status.setForeground(new java.awt.Color(255, 0, 51));
        status.setText("stopped");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(RunServer)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(StopServer)
                .add(119, 119, 119)
                .add(status, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(RunServer)
                        .add(StopServer))
                    .add(status, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 437, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * @param Mausevent
 * @return void
 * Methode wird beim Klicken auf den Stop Button aufgerufen
 * StopServerMouseClicked stopt den Server
 */
private void StopServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopServerMouseClicked
    // TODO add your handling code here:
    if(s!= null)
   {       
       s.closeServer();
       s = null;
       status.setText("stopped");
        status.setFont(new Font("",Font.PLAIN,12));
        status.setForeground(Color.RED);
   }
}//GEN-LAST:event_StopServerMouseClicked
/**
 * @param Mausevent
 * @return void
 * Methode wird beim Klicken auf den Start Button aufgerufen
 * RunServerMouseClicked startet einen neuen Server
 */
private void RunServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RunServerMouseClicked
    
    if(s==null){
        s = new Server();
        s.start();
        status.setText("running");
        status.setFont(new Font("",Font.PLAIN,12));
        status.setForeground(Color.GREEN);
    }

}//GEN-LAST:event_RunServerMouseClicked
/**
 * Methode wird nicht mehr verwendet
 */
private void WindowStartet(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WindowStartet

}//GEN-LAST:event_WindowStartet
/**
 * @param WindowEvent
 * @return void
 * Methode wird aufgerufen wenn das Fenster sich öffnet
 * formWindowOpened ließt über die Host2RootList KLasse die Daten aus dem externen .TXT File ein
 */
private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
        String[] Host2RootListArray = Host2RootFile.readFile();     
        int i = 0;
        while(Host2RootListArray[i] != null){
            Host2RootList.add(Host2RootListArray[i]);
            String[] HostAndRoot = SubstringHostRoot(Host2RootListArray[i]);
            Settings.addHost(HostAndRoot[0], HostAndRoot[1]);
            i++;
        }
    
}//GEN-LAST:event_formWindowOpened
/**
 * Methode wird nicht länger benötigt
 */
private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
    // TODO add your handling code here:
}//GEN-LAST:event_formWindowStateChanged
/**
 * @param MausEvent
 * @return void
 * Methode wird beim Klicken auf den SetPort Button aufgerufen
 * SetPort_Click verändert den Port auf dem der Server lauschen soll
 */
private void SetPort_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SetPort_Click
    if(!(PortText.getText().equals("standard port 80 is set"))){
        char[] Zahlen = {'0','1','2','3','4','5','6','7','8','9'};
        char[] PortTextCharArray = PortText.getText().toCharArray();
        boolean check = false;
        for(int i = 0; i <= PortTextCharArray.length - 1; i++)
        {
            for(int j = 0; j <= 9 ; j++){
                if(PortTextCharArray[i] == Zahlen[j]){
                    check = true;
                    break;
                }
                else{
                    check = false;
                }
            }
        }
        if(check == true)
        {
            Settings.port = Integer.parseInt(PortText.getText());
            Portinformation.setText("port set to:" + Settings.port);
            Portinformation.setForeground(Color.BLACK);
        }
        else{
            Portinformation.setForeground(Color.RED);
            Portinformation.setText("Bitte nur Zahlen eingeben");
        }
    }
    else
    {
        Portinformation.setForeground(Color.RED);
        Portinformation.setText("Bitte Portnummer eingeben");
    }
}//GEN-LAST:event_SetPort_Click
/**
 * @param Mausevent
 * @return void
 * Methode wird beim Klicken auf den Delete Button ausgeführt
 * DeleteHost2Root_Click löscht die aktuell markierte Zeile in der Host2RootList
 */
private void DeleteHost2Root_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteHost2Root_Click
    if(Host2RootList.getSelectedItem() != null){
        StringTokenizer st = new StringTokenizer(Host2RootList.getSelectedItem(), " / ");
        Settings.deleteHost(st.nextToken());
        Host2RootList.remove(Host2RootList.getSelectedIndex());
        String[] sta = Host2RootList.getItems();
        Host2RootFile.writeFile(sta);
    }   
}//GEN-LAST:event_DeleteHost2Root_Click
/**
 * @param Mausevent
 * @return
 * Methode wird beim Klicken auf den ADD Button aufgerufen
 * AddNewHost2RootMouseClicke fügt einen Host2RootList Eintrag hinzu
 */
private void AddNewHost2RootMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewHost2RootMouseClicked
    //prüfen ob Text ungleich NULL und einfügen in HashMap
    if(!(HostText.getText().equals("host")) && !(RootText.getText().equals("root"))){
        String Host2Root = HostText.getText() + " / " + RootText.getText();
        if(Settings.getRoot(HostText.getText()) == null){
            Host2RootList.add(Host2Root);
            Settings.addHost(HostText.getText(), RootText.getText());
            Information.setText("");
            String[] sta = Host2RootList.getItems();
            Host2RootFile.writeFile(sta);
        }
        else{
            Information.setText("Host2Root schon in Liste enthalten");
        }
    }
    else{
        Information.setText("Bitte host und root angeben!");
    }
}//GEN-LAST:event_AddNewHost2RootMouseClicked
/**
 * @param FocusEvent
 * @return void
 * Mehtode wird aufgerufen wen die RootText_Textbox den Focus verliert
 * RootTExt_LostFocus ruft die CheckText - Methode auf
 */
private void RootText_LostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RootText_LostFocus
    CheckText(RootText,"root");
}//GEN-LAST:event_RootText_LostFocus
/**
 * @param MausEvent
 * @return void
 * Methode wird aufgerufen wenn in die RootText_Textbox geklickt wird
 * RootText_Click ruft die ChangeSettingsOfTextbox auf
 */
private void RootText_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RootText_Click
    ChangeSettingsOfTextbox(RootText);
}//GEN-LAST:event_RootText_Click
/**
 * @param FocusEvent
 * @return void
 * Mehtode wird aufgerufen wen die PortText_Textbox den Focus erhält
 * PortText_FocusLost fur die CheckText Methode auf
 */
private void PortText_FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PortText_FocusLost
    CheckText(PortText,"standard port 80 is set");
}//GEN-LAST:event_PortText_FocusLost
/**
 * @param MausEvent
 * @return void
 * Methode wird aufgerufen wenn in die PortText_Textbox geklickt wird
 * PortText_Click ruft die ChangeSettingsOfTextbox auf
 */
private void PortText_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PortText_Click
    ChangeSettingsOfTextbox(PortText);
}//GEN-LAST:event_PortText_Click
/**
 * @param FocusEvent
 * @return void
 * Mehtode wird aufgerufen wen die HostText_Textbox den Focus erhält
 * HostText_LostFocus ruft die CheckText Methode auf
 */
private void HostText_LostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_HostText_LostFocus
    CheckText(HostText,"host");
}//GEN-LAST:event_HostText_LostFocus
/**
 * @param MausEvent
 * @return void
 * Methode wird aufgerufen wenn in die HostText_Textbox geklickt wird
 * HostText_Click ruft die ChangeSettingsOfTextbox Methode auf
 */
private void HostText_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HostText_Click
    ChangeSettingsOfTextbox(HostText);
}//GEN-LAST:event_HostText_Click
/**
 * @param java.awt.Textfield (eine Textbox)
 * @return void
 * Methode wird von den ....Text_Click Methoden aufgerufen
 * ChangeSettingsOfTextbox setzt den Text auf NULL und ändert die Formatierung des Textes
 */
private void ChangeSettingsOfTextbox(java.awt.TextField aTextField){
    aTextField.setText("");
    aTextField.setFont(new Font("",Font.PLAIN,12));
    aTextField.setForeground(Color.BLACK);
}
/**
 * @param java.awt.Textfield (ein Textfeld); String
 * @return void
 * Dies Methode wird von den ...TextLostFocus Mehtoden aufgerufen
 * CheckText überprüft den String in dem übergebenen Textfeld und ändert ihn bei bestimmten Bedinungen
 */
private void CheckText(java.awt.TextField aTextField,String aName){
    if(aTextField.getText().equals("") ){
        aTextField.setText(aName);
        aTextField.setFont(new Font("",Font.ITALIC,12));
        Color C = new Color(204, 204, 255, 0);
        aTextField.setForeground(C);
    }
    else{}
}
/**
 * @param String
 * @return StringArray mit 2 Einträgen
 * Gibt einen Array mit 2 String Einträgen zurück (Host und Root)
 */
private String[] SubstringHostRoot(String aString){
    char aStringArray[] = aString.toCharArray();
    String[] HostAndRootSeperated = new String[2];
    String Host = "";
    String Root = "";
    int Position = aString.indexOf(" / ");
    for(int i = 0; i <= Position - 1; i++){
        Host += aStringArray[i];            
    }
    for(int j = Position + 3; j < aString.length(); j++){
        Root += aStringArray[j];
    }
    HostAndRootSeperated[0] = Host;
    HostAndRootSeperated[1] = Root;
    return HostAndRootSeperated;
}
    /**
     * @param args the command line arguments
*/
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNewHost2Root;
    private javax.swing.JButton DeleteHost2Root;
    private javax.swing.JLabel Host2RootLabel;
    private java.awt.List Host2RootList;
    private java.awt.TextField HostText;
    private java.awt.Label Information;
    private javax.swing.JLabel PortLabel;
    private java.awt.TextField PortText;
    private java.awt.Label Portinformation;
    private java.awt.TextField RootText;
    private javax.swing.JButton RunServer;
    private javax.swing.JButton SetPort;
    private javax.swing.JButton StopServer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label status;
    // End of variables declaration//GEN-END:variables
    
}
