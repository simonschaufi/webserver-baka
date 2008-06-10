/*
 * Interface.java
 *
 */
package webserver;

import java.awt.*;
import java.util.StringTokenizer;
import java.io.*;

/**
 * @autor Andreas Paul, JörgMogielinski, Simon Schaufelberger, Netbeans
 * GUI wird erstellt
 */
public class WebserverGUI extends javax.swing.JFrame
{
    Server server = null;

    /**
     * Ausgabefunktion für Messages im Gui, sowie Fokus setzten auf das zuletzt
     * geschriebenen Element.
     * @param message Nachricht die Ausgegeben werden soll
     */
    public void printMessages(String message)
    {
        this.ListMessages.add(message);
        this.ListMessages.makeVisible(this.ListMessages.getItemCount() - 1);
    }

    /**
     * Konstruktor für das Interface, ruft Netbeans-methode initComponents auf,
     * Konstruktor der Oberklasse setzt den Window-Titel fest
     */
    public WebserverGUI()
    {
        super("MSP Webserver");
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelAll = new javax.swing.JPanel();
        LabelMultihoming = new javax.swing.JLabel();
        ButtonDeleteMultihoming = new javax.swing.JButton();
        LabelPort = new javax.swing.JLabel();
        TextFieldPort = new java.awt.TextField();
        ListMultihoming = new java.awt.List();
        Information = new java.awt.Label();
        Portinformation = new java.awt.Label();
        ButtonRun = new javax.swing.JButton();
        ButtonStop = new javax.swing.JButton();
        LabelStatus = new java.awt.Label();
        LabelDefault = new javax.swing.JLabel();
        LabelMessages = new javax.swing.JLabel();
        ListMessages = new java.awt.List();
        ButtonSet = new javax.swing.JButton();
        PanelMultihoming = new javax.swing.JPanel();
        ButtonAdd = new javax.swing.JButton();
        TextFieldRoot = new java.awt.TextField();
        TextFieldHost = new java.awt.TextField();
        LabelHost = new javax.swing.JLabel();
        LabelRoot = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        LabelMultihoming.setFont(new java.awt.Font("Tahoma", 1, 12));
        LabelMultihoming.setText("Multihoming");

        ButtonDeleteMultihoming.setText("delete");
        ButtonDeleteMultihoming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonDeleteMultihoming_Click(evt);
            }
        });

        LabelPort.setFont(new java.awt.Font("Tahoma", 0, 12));
        LabelPort.setText("port");

        TextFieldPort.setForeground(new java.awt.Color(0, 0, 0));

        ListMultihoming.setFont(new java.awt.Font("Dialog", 1, 12));

        Information.setForeground(new java.awt.Color(255, 0, 51));

        Portinformation.setForeground(new java.awt.Color(255, 0, 0));

        ButtonRun.setText("run server");
        ButtonRun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonRunMouseClicked(evt);
            }
        });

        ButtonStop.setText("stop server");
        ButtonStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonStopMouseClicked(evt);
            }
        });

        LabelStatus.setFont(new java.awt.Font("Dialog", 1, 12));
        LabelStatus.setForeground(new java.awt.Color(255, 0, 51));
        LabelStatus.setText("stopped");

        LabelDefault.setText("Default is port 80");

        LabelMessages.setFont(new java.awt.Font("Tahoma", 1, 12));
        LabelMessages.setText("Server Messages");

        ListMessages.setBackground(new java.awt.Color(0, 0, 102));
        ListMessages.setFont(new java.awt.Font("Dialog", 0, 14));
        ListMessages.setForeground(new java.awt.Color(102, 204, 0));

        ButtonSet.setText("set");
        ButtonSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonSet_Click(evt);
            }
        });

        org.jdesktop.layout.GroupLayout PanelAllLayout = new org.jdesktop.layout.GroupLayout(PanelAll);
        PanelAll.setLayout(PanelAllLayout);
        PanelAllLayout.setHorizontalGroup(
            PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelAllLayout.createSequentialGroup()
                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelAllLayout.createSequentialGroup()
                        .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(Portinformation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelAllLayout.createSequentialGroup()
                                .add(37, 37, 37)
                                .add(Information, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(PanelAllLayout.createSequentialGroup()
                                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, LabelMultihoming, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, ListMultihoming, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(LabelMessages, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                                    .add(ListMessages, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                                    .add(PanelAllLayout.createSequentialGroup()
                                        .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(PanelAllLayout.createSequentialGroup()
                                                .add(LabelPort)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(TextFieldPort, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(ButtonSet, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, PanelAllLayout.createSequentialGroup()
                                                .add(ButtonRun)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(ButtonStop)))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(LabelStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(LabelDefault))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 77, Short.MAX_VALUE)))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(ButtonDeleteMultihoming, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelAllLayout.setVerticalGroup(
            PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, PanelAllLayout.createSequentialGroup()
                .addContainerGap()
                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(LabelMultihoming)
                    .add(LabelMessages))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(ListMessages, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .add(ListMultihoming, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelAllLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(ButtonDeleteMultihoming)
                            .add(ButtonRun)
                            .add(ButtonStop))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(PanelAllLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(LabelStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PanelAllLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(ButtonSet)
                    .add(TextFieldPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(LabelPort)
                    .add(LabelDefault))
                .add(39, 39, 39)
                .add(Information, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(67, 67, 67)
                .add(Portinformation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelMultihoming.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Multihoming - Add new site", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        ButtonAdd.setText("Add");
        ButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonAddMouseClicked(evt);
            }
        });

        TextFieldRoot.setForeground(new java.awt.Color(0, 0, 0));

        TextFieldHost.setForeground(new java.awt.Color(0, 0, 0));

        LabelHost.setText("Host");

        LabelRoot.setText("Root");

        org.jdesktop.layout.GroupLayout PanelMultihomingLayout = new org.jdesktop.layout.GroupLayout(PanelMultihoming);
        PanelMultihoming.setLayout(PanelMultihomingLayout);
        PanelMultihomingLayout.setHorizontalGroup(
            PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelMultihomingLayout.createSequentialGroup()
                .addContainerGap()
                .add(PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(LabelRoot)
                    .add(LabelHost))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(TextFieldHost, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(TextFieldRoot, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(ButtonAdd)
                .addContainerGap())
        );
        PanelMultihomingLayout.setVerticalGroup(
            PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PanelMultihomingLayout.createSequentialGroup()
                .add(21, 21, 21)
                .add(PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(TextFieldHost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(LabelHost))
                .add(13, 13, 13)
                .add(PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PanelMultihomingLayout.createSequentialGroup()
                        .add(ButtonAdd, 0, 0, Short.MAX_VALUE)
                        .add(14, 14, 14))
                    .add(PanelMultihomingLayout.createSequentialGroup()
                        .add(3, 3, 3)
                        .add(PanelMultihomingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(LabelRoot)
                            .add(TextFieldRoot, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, PanelAll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, PanelMultihoming, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 566, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(PanelAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(29, 29, 29)
                .add(PanelMultihoming, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Stop Button: Server schließen, und "vernichten"
     */
    private void ButtonStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonStopMouseClicked

        if (server != null)
        {
            server.closeServer();
            server = null;
            LabelStatus.setText("stopped");
            LabelStatus.setFont(new Font("", Font.BOLD, 12));
            LabelStatus.setForeground(Color.RED);
        }
}//GEN-LAST:event_ButtonStopMouseClicked
/**
 *Startbutton: Neuer Server intialisieren
 */
private void ButtonRunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonRunMouseClicked
    
    if(server==null){
        server = new Server(this);
        server.start();
        LabelStatus.setText("running");
        LabelStatus.setFont(new Font("",Font.BOLD,12));
        LabelStatus.setForeground(Color.BLUE);
        printMessages("Server wurde auf Port " + server.sSocket.getLocalPort() + " gestartet...");
        printMessages(" ");
    }

}//GEN-LAST:event_ButtonRunMouseClicked

/**
* Wird das Programm gestartet und das GUI öffnet sich, dann wird der Inhalt der
 * Multihoming.txt sofort in die Multihoming Liste geschrieben.
 */
private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
        String[] MultihomingArray = MultihomingToFile.getFileContent();     
        int i = 0;
        while(MultihomingArray[i] != null){
            ListMultihoming.add(MultihomingArray[i]);
            String[] HostAndRoot = SubstringHostRoot(MultihomingArray[i]);
            Settings.addHost(HostAndRoot[0], HostAndRoot[1]);
            i++;
        }
    
}//GEN-LAST:event_formWindowOpened
/**
 * Beim klicken auf "Set" wird geprüft ob der Server läuft, ist dies nicht der
 * Fall wird der Port auf dem der Server läuft auf den eingegebenen wert gesetzt.
 */
private void ButtonSet_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSet_Click
    if(LabelStatus.getText().equals("running"))
     {
         printMessages("Port kann nicht geänder werden wenn der Server läuft.");
     }
    else{
    if(!(TextFieldPort.getText().equals("")))
    {
     
        try{
            Server.port = Integer.parseInt(TextFieldPort.getText());
            printMessages("Port auf " + Server.port + " gesetzt");
        }
        catch(NumberFormatException e)
        {
            printMessages("Port-Eingabe war keine Zahl!");
        }
    }
    else
    {
              Server.port = 80;  
    }
    }
}//GEN-LAST:event_ButtonSet_Click
/**
 * Methode wird beim Klicken auf den Delete Button ausgeführt
 * DeleteHost2Root_Click löscht die aktuell markierte Zeile in der Host2RootList
 */
private void ButtonDeleteMultihoming_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDeleteMultihoming_Click
    if(ListMultihoming.getSelectedItem() != null){
        StringTokenizer stringToken = new StringTokenizer(ListMultihoming.getSelectedItem(), " / ");
        Settings.deleteHost(stringToken.nextToken());
        ListMultihoming.remove(ListMultihoming.getSelectedIndex());
        String[] MultihomingItems = ListMultihoming.getItems();
        MultihomingToFile.setFileContent(MultihomingItems);
    }   
}//GEN-LAST:event_ButtonDeleteMultihoming_Click
/**

 /**
* Fügt neuen Host/Root kombination in die Multihoming liste dazu
 */
private void ButtonAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAddMouseClicked
    //prüfen ob Text ungleich NULL und einfügen in HashMap
    if(!(TextFieldHost.getText().equals("")) && !(TextFieldRoot.getText().equals(""))){
        String Host2Root = TextFieldHost.getText() + " / " + TextFieldRoot.getText();
        if(Settings.getRoot(TextFieldHost.getText()) == null){
            ListMultihoming.add(Host2Root);
            Settings.addHost(TextFieldHost.getText(), TextFieldRoot.getText());
            
            String[] sta = ListMultihoming.getItems();
            MultihomingToFile.setFileContent(sta);
        } }
}//GEN-LAST:event_ButtonAddMouseClicked

/**
 *Zerteil eiene Host/Root eintrag aus der Multihoming liste in
 * zwei seperate Strings für Host und Root, und gibt diese in einem
 * String-Array zurück
 * @return Atring Array mit 2 feldern: 0 = Host, 1 = Root
 */
private String[] SubstringHostRoot(String MultihomingItem){
    char stringAsChar[] = MultihomingItem.toCharArray();
    String[] HostAndRoot = new String[2];
    String Host = "";
    String Root = "";
    int Position = MultihomingItem.indexOf(" / ");
    for(int i = 0; i <= Position - 1; i++){
        Host += stringAsChar[i];            
    }
    for(int j = Position + 3; j < MultihomingItem.length(); j++){
        Root += stringAsChar[j];
    }
    HostAndRoot[0] = Host;
    HostAndRoot[1] = Root;
    return HostAndRoot;
}
    /**
     * @param args the command line arguments
*/
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WebserverGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonDeleteMultihoming;
    private javax.swing.JButton ButtonRun;
    private javax.swing.JButton ButtonSet;
    private javax.swing.JButton ButtonStop;
    private java.awt.Label Information;
    private javax.swing.JLabel LabelDefault;
    private javax.swing.JLabel LabelHost;
    private javax.swing.JLabel LabelMessages;
    private javax.swing.JLabel LabelMultihoming;
    private javax.swing.JLabel LabelPort;
    private javax.swing.JLabel LabelRoot;
    private java.awt.Label LabelStatus;
    private java.awt.List ListMessages;
    private java.awt.List ListMultihoming;
    private javax.swing.JPanel PanelAll;
    private javax.swing.JPanel PanelMultihoming;
    private java.awt.Label Portinformation;
    private java.awt.TextField TextFieldHost;
    private java.awt.TextField TextFieldPort;
    private java.awt.TextField TextFieldRoot;
    // End of variables declaration//GEN-END:variables
    
}