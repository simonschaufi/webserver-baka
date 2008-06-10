
/*
 * Connection.java
 */
package webserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Andreas Paul
 */
public class Connection extends Thread {

    Socket sSocket; //Deklarieren der Socket-Variable
    BufferedReader brInput; //Deklarieren der BufferedReader-Variable
    OutputStream osOutput; //Deklarieren der OutputStream-Variable
    Message mMessage; //Deklarieren der Message-Variable
    DataInputStream dataInput; //Deklarieren der DataInputStream-Variable
    Request cRequest; //Deklarieren der ClientRequest-Variable
    WebserverGUI intfGui; //Deklarieren der Interface-Variable

    public Connection(Socket aSocket, WebserverGUI intf) {
        this.intfGui = intf;
        /*^^^^
         * Durch Aufruf dieses Konstruktors durch die Klasse 'Server' in ihrer 'run'-Methode
         * wird der zu verwendende Socket und das genutzte Objekt der Klasse 'Interface'
         * für ihre 'printMessages'-Methode mitgegeben
         */

        try {
            sSocket = aSocket; // Der durch den Konstruktoraufruf mitgegebene Socket wird zum Socket dieses 'Connection'-Objektes
            sSocket.setKeepAlive(true); //Lässt den Socket offen
            brInput = new BufferedReader(new InputStreamReader(aSocket.getInputStream())); //Der Inputstream des Sockets wird in der BufferedReader-Variable 'brInput' gespeichert
            osOutput = sSocket.getOutputStream(); //Der Outputstream des Sockets wird in der OutputStream-Variable 'osOutput' gespeichert
        } catch (IOException ieEx) { //Abfangen eines möglichen Fehler beim Erstellen des 'Connection'-Objektes
            intfGui.printMessages("Beim Erstellen eines Connection-Objektes ist ein Fehler aufgetretten: " + ieEx.getMessage() + "!");
        }
    }

    @Override
    public void run() { //Die 'run'-Methode der 'Connection'-Klasse
        try {
            while (!(sSocket.isClosed())) { //Solange der Socket nicht geschlossen ist!
                if (sSocket.getInputStream().available() != 0) {

                    /*^^^^
                     * 'sSocket.getInputStream().available()' gibt die zu erwartende Bytes-Anzahl des Inputstream des Sockets wieder.
                     * Wenn diese nicht '0' beträgt führe die klasseninterne Methode 'read' aus.
                     */

                    sleep(100); // Warte bis der Browser genügend Zeit hatte Daten an den Socket zu schicken
                    read(); //Lese
                    if (!(cRequest.getPersistentConnection().equalsIgnoreCase("Keep-Alive")) || cRequest.getVersion().equalsIgnoreCase("HTTP/1.0")) {
                        sSocket.close();
                    }
                    intfGui.printMessages("Socket: " + sSocket.getPort());
                    intfGui.printMessages(" ");
                }
                sleep(50);

            }  //#### Hier endet die while (!(sSocket.isClosed()))-Schleife ####

        } catch (IOException ioEx) {
            intfGui.printMessages("Fehler in der Verbindung: " + ioEx.getMessage() + "!");
        } catch (InterruptedException ieEx) {
            intfGui.printMessages("Socket wurde aufgrund eines Fehlers geschlossen: " + ieEx.getMessage() + "!");
        }
    }

    private void read() {
        
        try {
            cRequest = new Request(sSocket.getInputStream(), intfGui);
            /*^^^^
             * Erstelle ein neues Objekt der Klasse 'ClientRequest'
             * Gebe diesem Objekt den momentanen Inputstream des Sockets mit
             * und das verwendete GUI für die 'GUI'-Methode 'printMessages'
             */  

            mMessage = new Message(cRequest, osOutput, intfGui);
            /*^^^^
             * Erstelle ein neues Objekt der Klasse 'Message'
             * Gebe diesem Objekt den momentanen Outputstream des Sockets mit,
             * das zuvor erstellte 'ClientRequest'-Objekt 'cRequest'
             * und das verwendete GUI für die 'GUI'-Methode 'printMessages'
             */

            String kOfMsgReq = cRequest.getKindOfRequest();
            
            /*^^^^
             * Holt sich die Art des Clientrequestes durch die 'ClientRequest'-Methode 'getKindOfRequest'
             * und speichert es als lokale String-Variable
             * 
             * In 'kOfMsgReq' kann: GET, POST, HEAD, PUT, TRACE, ,DELTE, OPTIONS stehen
             */

            if (kOfMsgReq.equals("GET")) { // Wenn die Art des Clientrequestes ein 'GET' ist
                
                if (Settings.use100Continue) {
                    /*^^^^
                     * In den Einstellungen des Webserver ist die Verwendung von 100 HTTP-Statuscodes erlaubt
                     * Die laufende Anfrage an den Server wurde noch nicht zurückgewiesen.
                     * Der Client kann nun mit der (potentiell sehr großen) Anfrage fortfahren.
                     */

                    mMessage.send100Continue();
                    /*^^^^
                     * Die Methode 'send100Continue' der 'Message' wird aufgerufen
                     * Diese sendet folgendes an den Outputstream: 
                     * String con = "HTTP/1.1 100 Continue" + ("\r\n") + ("\r\n");
                     */

                    try {
                        sleep(5000); //Der Server-Thread versucht 5 Sekunden zu schlafen
                    } catch (InterruptedException ieEx) { //Abfangen, wenn der Server-Thread durch ein 'send100Continue' unterbrochen wird
                        intfGui.printMessages("unable to sleep -> 100 continue : " + ieEx.getMessage() + "!");
                    }

                } //#### Hier endet Settings.use100Continue-IF-Abfrage ####


                mMessage.replyRequest(true);

            /* ^^^^
             * Ruft die Methode 'replyRequest' der Klasse 'Message' auf
             * 
             * Diese überprüft, ob die vom Clienten angeforderte Datei:
             * a) existiert, b) gelesen werden kann und c) kein Ordner ist.
             *
             * Ausserdem wird durch den überprüft, ob die Datei nach der letzten Anforderung geändert wurde
             * Wenn ja wird ein HTTP-Status-Code '304' versendet
             * --> "Indicates the resource has not been modified since last requested."
             * 
             * Zum Schluss wird die angeforderte Datei gesendet.
             */

            } else if (kOfMsgReq.equals("POST")) {  // Wenn die Art des Clientrequestes ein 'POST' ist
                mMessage.writePostToFile(cRequest.getPostKeyValueString());
                /* ^^^^
                 * Hier wird der vom Clienten versendete 'POST'-Inhalt in einer Datei gespeichert
                 */

                new CreateResponse(osOutput, cRequest, "204  -> No Content", intfGui).generateResponse("STATUS");
            /*^^^^
             * Die Anfrage wurde erfolgreich durchgeführt, die Antwort enthält jedoch bewusst keine Daten.
             */

            } else if (kOfMsgReq.equals("HEAD")) {  // Wenn die Art des Clientrequestes ein 'HEAD' ist
                mMessage.replyRequest(false);
            /* ^^^^
             * Ruft die Methode 'replyRequest' der Klasse 'Message' auf
             * 
             * Diese überprüft, ob die vom Clienten angeforderte Datei:
             * a) existiert, b) gelesen werden kann und c) kein Ordner ist.
             *
             * Ausserdem wird durch den überprüft, ob die Datei nach der letzten Anforderung geändert wurde
             * Wenn ja wird ein HTTP-Status-Code '304' versendet.
             * --> "Indicates the resource has not been modified since last requested."
             * 
             * Die eigntliche Datei wird NICHT (!) gesendet,
             * sondern nur 'content-type' und 'content-length'.
             */

            } else if (kOfMsgReq.equals("PUT") || kOfMsgReq.equals("DELETE") || kOfMsgReq.equals("OPTIONS") || kOfMsgReq.equals("TRACE")) {
                // Wenn die Art des Clientrequestes etwas anderes als die zuvor abgeprüften ist
                throw new ClientException("501  -> Not Implemented");
            /*^^^^
             * Da diese Funktionen nicht implementiert sind wird der entsprechende HTTP-Status-Code '501' gesendet
             * 
             * "Die Funktionalität, um die Anfrage zu bearbeiten, wird von diesem Server nicht bereitgestellt. 
             * Ursache ist zum Beispiel eine unbekannte oder nicht unterstützte HTTP-Methode."
             * 
             */

            } else { // Wenn in der Variable 'kOfMsgReq' kein bekannter (hier abgeprüfter) String vorhanden ist 
                throw new ClientException("400  -> Bad Request");
            }

        } //#### Hier endet das try der 'read'-Methode #### catch (IOException e) {
        catch (IOException ioEx) {
            //Abfangen von fehlerhaften Interpretation des Client-Requests
            intfGui.printMessages("Beim Ermitteln des Client-Requests ist ein Fehler aufgetretten: " + ioEx.getMessage());

        } catch (ClientException clEx) {
            //Gibt den Fehler aus, wenn 'kOfMsgReq' nicht POST, GET oder HEAD war
            intfGui.printMessages("Fehler: " + clEx.getMessage());

            CreateResponse cResponse = new CreateResponse(osOutput, clEx.getMessage(), cRequest, intfGui);

            cResponse.setPath(cRequest.getRequestedFilePath());
            /* ^^^^
             * Ändert die 'path' Variable des Objektes 'cResponse' zu der 'path' Variable des Objektes 'cRequest'
             */

            cResponse.generateResponse("ERROR");
        }
    }
}

    