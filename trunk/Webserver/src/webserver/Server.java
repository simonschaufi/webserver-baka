/*
 * Server.java
 */
package webserver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Andreas Paul
 * 
 * Diese Klasse virtualisiert den Webserver
 * 
 * Sie ist für die Verwaltung (Öffnen, Schliessen, Fehlerabfangen) der Sockets zuständig
 * Ausserdem Erstellt diese Klasse Objekte der Klasse 'Connection' und führt ihre 'run'-Methode aus
 */
public class Server extends Thread {

    public ServerSocket sSocket; //Deklaration des zu öffnenden Sockets
    Connection aConnection; //Die Connection die später geöffnet wird
    Boolean bRun = true; //Die Variable die 'true' gesetzt wird beim Starten und auf 'false' beim Beenden des Webserver über das GUI
    WebserverGUI intfGui = null; //Das hier verwendete GUI für die Methode 'printMessages'
    static int port = 80;

    public Server(WebserverGUI intf) {

        this.intfGui = intf; //Zuweisung des verwendeten GUIs

        try {
            sSocket = new ServerSocket(port); //Erstellen des Sockets

        } catch (IOException ex) { //Ggf. Fehlermeldung beim Öffnen des Sockets abfangen

            intfGui.printMessages("Fehler beim Erstellen des Socket: " + ex.getMessage());
        }

    }

    @Override
    public void run() { //Die 'run'-Methode der 'Server'-Klasse

        while (bRun) { /* <<<---
             * Solange der Webserver vom Benutzer durch den Button in 'Interface' gestartet wurde
             * Kann durch den Stopbutton in 'Interface' der die 'Server'-Methode 'closeServer' aufruft gestoppt werden
             */
            try {
                aConnection = new Connection(sSocket.accept(), intfGui);

                /* ^^^^
                 * Öffnen des Sockets und übergabe des GUIs
                 * an die 'Connection'-Klasse für Methode 'printMessages'
                 */

                if (aConnection != null) {

                    /*^^^^
                     * Wenn die Connection erfolgreich geöffnet,
                     *  starte die 'run' Methode in der Klasse 'Connection'
                     */

                    aConnection.start(); //Starten der 'run' Methode in der Klasse 'Connection'
                }

            } catch (IOException ex) { //Abfangen der möglichen Fehler
                if (ex.getMessage().equals("socket closed")) {
                    intfGui.printMessages("Der Server-Socket wurde geschlossen!");

                } else {
                    intfGui.printMessages("Fehler beim Oeffnen des Client-Socket: " + ex.getMessage() + "!");
                }
            } catch (NullPointerException ex) {

                intfGui.printMessages("Fehler!!! Port 80 wird bereits verwendet: " + ex.toString() + "!");
                bRun = false;
            }

        } //#### Hier endet die while(bRun)-Schleife ####


        try {
            sSocket.close();
        /* ^^^^
         * Da 'bRun' jetzt false aufgrund des Aufrufes
         * der 'closeServer'-Methode von 'Server' durch den Stopknopf
         */

        } catch (IOException ex) {
            intfGui.printMessages("Fehler beim Schließen des Sockets: " + ex.getMessage() + "!");
        }

        intfGui.printMessages("Server wird beendet!");
    }

    public void closeServer() {
        /* ^^^^
         * Wird von der Methode 'ButtonStopMouseClicked' der Klasse 'Interface'
         * aufgerufen und beendet die while(bRun)-Schleife in der 'Server'-Methode 'run'
         */
        try {

            bRun = false; // while(bRun)-Schleife stoppen
            sSocket.close(); //Den Socket schliessen

        } catch (IOException ex) { //Fehlerabfangen

            intfGui.printMessages("Fehler beim Schließen des Sockets: " + ex.getMessage() + "!");
        }
    }
}
