package webserver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * @autor Jörg Mogielinski
 * Erstellt aus dem Request eine Antwort. Überladene Konstruktoren
 * für verschiedene Header Typen
 */
public class CreateResponse
{
    Request clientRequest;
    ArrayList<String> httpHeader;
    OutputStream output;
    String persistentConnection;
    WebserverGUI gui;
    File fileToSend;
    String errorHeader;
    String standardIndexPage = "index.html";    
    private String statusCode;
    private String path;


    /**
     * Konstruktoren. Speichern je nach Bedarf/Headertyp ander Atributwerte
     * Outputstream, Request und GUI werden immer mitgegeben. 
     * Für GET das File
     * Für Status-Header der StatusCode
     * Für ErrorHeader der Spezifische Error Header
     */
    //GET
    public CreateResponse(OutputStream out, File file2get, Request request, WebserverGUI i)
    {
        httpHeader = new ArrayList<String>();
        this.output = out;
        this.clientRequest = request;
        this.persistentConnection = request.getPersistentConnection();
        this.fileToSend = file2get;
        this.gui = i;
    }

    //Status zb Für POST oder Midiefied/Unmodified
    public CreateResponse(OutputStream out, Request request, String statusCode, WebserverGUI i)
    {
        httpHeader = new ArrayList<String>();
        this.output = out;
        this.clientRequest = request;
        this.persistentConnection = request.getPersistentConnection();
        this.statusCode = statusCode;

    }

    //ERROR
    public CreateResponse(OutputStream out, String errorHeader, Request request, WebserverGUI i)
    {
        this.gui = i;
        httpHeader = new ArrayList<String>();
        this.output = out;
        this.clientRequest = request;
        this.persistentConnection = request.getPersistentConnection();
        this.errorHeader = errorHeader;
    }

    void setPath(String path)
    {
        gui.printMessages("Path: " + path);
        this.path = path;
    }

    /**
     * Hilfsmethode
     * Fügt der Array-Liste immer eine Zeile des Header hinzu
     * plus carriage Return Line Feed
     */
    public void addHeaderInfo(String text)
    {
        httpHeader.add(text + "\r\n");
    }

    /**
     * Gibt den Generierten Header byteweise in den output Stream
     * und gibt diesen zusätzlich im Message Fenster des GUI aus
     */
    public void sendHeader()
    {
        try
        {
            for (int i = 0; i < httpHeader.size(); i++)
            {
                output.write(httpHeader.get(i).getBytes());
                gui.printMessages((httpHeader.get(i).toString()).trim());
            }

            output.write(("\r\n").getBytes());


        }
        catch (IOException e)
        {
            gui.printMessages("Senden des Response-Headers fehlgesschlagen!");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Fügt die Zeile mit dem Datum in den Header ein
     */
    public void addDate()
    {
        String date = "Date: " + Settings.getDate() + "\r\n";
        httpHeader.add(date);
    }

    /**
     * Prüft ob eine PErsistente Verbindung erhalten werden soll, und setzt
     * entsprechende Header-Einträge.
     * Schließt die Persistente Verbindung bei bedarf auch wieder.
     */
    public void checkForPersistentConnection()
    {
        if (!(persistentConnection.equalsIgnoreCase("")) && !(clientRequest.getVersion().equalsIgnoreCase("HTTP/1.0")))
        {
            if (persistentConnection.equals("Keep-Alive"))
            {
                addHeaderInfo("Connection: keep-alive");
                addHeaderInfo("Keep-Alive: timeout=3, max=100");
            }

            if (persistentConnection.equals("close"))
            {
                addHeaderInfo("Connection: close");
            }

        }
    }

    /**
     * Prüft ob von den Server Settings aus chunked Data aktiviert ist und setzt
     * entsprechend die Header einträge und Splittet die zu versendende Dateien 
     * in die Chunk-Size großen Fragmente.
     * NOTE: Seit wir das eingebaut haben geht es ohne chucked nicht mehr :\
     */
    public void isChunkedData()
    {
        try
        {
                addHeaderInfo("Content-Length: " + fileToSend.length());
                addHeaderInfo("Content-Type: " + fileToSend.toURI().toURL().openConnection().getContentType());
                 System.out.println(fileToSend.toURI().toURL().openConnection().getContentType());
        }
        catch (IOException ioE)
        {
            ioE.printStackTrace();
        }
    }

    /**
     * gekapselte Methode des Request. 
     * @return Gibt die HTTP Version zurück.
     */
    public String getVersion()
    {
        return clientRequest.getVersion();
    }

    /**
     * Text der Fehlerseite in HTML. Wird im Fehlerfall angezeigt
     * und mit dem Enssprechende Fehlercod versehen.
     */
    private String createHTMLErrorPage(String errorType)
    {
        return "<html>\n" +
                " <head>\n" +
                "  <title>" + errorType + "</title>\n" +
                " </head>\n" +
                " <body>\n" +
                "  <h1>Ups, da hat wohl was nicht geklappt: " + errorType + "</h1>\n" +
                "    <p>" +
                "Mögliche Ursache:" +
                "<ul>" +
                "<li>Vertippt</li>" +
                "<li>Seite existiert nicht meht</li>" +
                "<li>Adresse der Seite hat sich geändet</li>"+
                "<li>Der Server ist nicht fehlerfrei Programmier ;)</li>" +
                "</ul>" +
                "  </p>" +                                                                                                                                                                                                                         
                " </body>\n" +
                "</html>\r\n";
    }

    /**
     * anhand des Typs der im request angefordert wurde, wird ein anderer Header
     * als antwort verschickt. Hierbei werden die entsprechenden Werte und Informationen
     * in eine ArrayList geschrieben die dann Bytekonvertiert in den OutputStream 
     * gesendet wird.
     */
    public void generateResponse(String headerType)
    {
        if (headerType.equals("GET"))
        {
            
            addHeaderInfo(getVersion() + " 200 OK");
            
            System.out.println("sdfasdofsdf");addDate();
            checkForPersistentConnection();
            isChunkedData();
          
        }
        else if (headerType.equals("STATUS"))
        {
            addHeaderInfo(getVersion() + " " + statusCode);
            addDate();
            checkForPersistentConnection();
        }
        else if (headerType.equals("ERROR"))
        {
            if (errorHeader.equals("302 Found"))
            {
                addHeaderInfo(getVersion() + " " + errorHeader);
                addDate();
                checkForPersistentConnection();
                addHeaderInfo("Location: " + this.path + standardIndexPage);
                addHeaderInfo("\r\n");
            }
            else
            {
                addHeaderInfo(getVersion() + " " + errorHeader);
                addDate();
                checkForPersistentConnection();
                addHeaderInfo("Content-Type: text/html; charset=iso-8859-1");
                addHeaderInfo("Content-Length: " + (createHTMLErrorPage(errorHeader).getBytes()).length);
                try
                {
                    output.write(createHTMLErrorPage(errorHeader).getBytes());
                    output.write(("\r\n").getBytes());
                    output.flush();
                }
                catch (IOException ioE)
                {
                    gui.printMessages("Fehler beim Schicken der Fehlerstatus " + errorHeader + " :" + ioE.getMessage());
                }
            }
        }
        sendHeader();
    }
}
