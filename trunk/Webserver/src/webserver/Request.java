/**
 * Request.java
 */
package webserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

/**
 * @autor Jörg Mogielinski
 * Emppängt den Request vom Clienet(Browser) und "parst" den Header
 * und settz anhand der erhaltenen Tokens die Attribute der Klasse
 */
public class Request
{

    DataInputStream inputStream;
    byte[] InputStreamBytes;
    ArrayList<String> receivedHeader;
    Interface gui;
    String version = "";
    String headerType = "";
    String requestedFilePath = null;
    String requestedHost = null;
    String persistentConnection = "";
    String postKeyValueString = "";
    String modifiedSince = "";
    String unmodifiedSince = "";

    /**
     * Konstruktor der den Request parsed und die Tokens entsprechend
     * auswerted und Attribute der Klasse setzt
     * @param innputStream Der InputStream der aud dem Socket eingelesen wurde
     */
    public Request(InputStream inputStream, Interface i)
    {
        this.gui = i;
        this.inputStream = new DataInputStream(inputStream);
        this.receivedHeader = new ArrayList<String>();
        inputStreamToByteArray();
        byteArrayToArrayList();
        getRequestData();
        checkModifiedUnmodifiedSince();
    }

    /**
     * Jede Zeile des Requests wird als aneienandrgehängte chars in ein Feld der
     * ArrayList geschrieben
     */
    private void byteArrayToArrayList()
    {
        final int carriageReturn = 13;
        final int lineFeed = 10;
        int length = InputStreamBytes.length;
        String line = "";
        for (int i = 0; i < length; i++)
        {
            if ((i < length - 2) && (InputStreamBytes[i] != carriageReturn && InputStreamBytes[i + 1] != lineFeed))
                line = line + (char)InputStreamBytes[i];
            else
            {//vorletzte Zeile, oder ende der Zeile hier springt er immer rein
                line = line + (char) InputStreamBytes[i] + (char) InputStreamBytes[i + 1];
                receivedHeader.add(line);
                line = "";
                i++;
            }
        }
    }

    /**
     * Schreibt den Kompletten Request in ein ByteArray.
     */
    private void inputStreamToByteArray()
    {
        try
        {
            int numberOfBytes = inputStream.available();
            InputStreamBytes = new byte[numberOfBytes];
            inputStream.readFully(InputStreamBytes);
        }
        catch (IOException e)
        {
            gui.printMessages("Fehler beim lesen des InputStreams!");
        }
    }

    /**
     * Zerteilt den Imput in Tokens (mittels String-Tokenizer) und Prüft auf 
     * HTTP-Header inhalte
     */
    private void getRequestData()
    {
        String nextToken = "";
        String lineBefore = "";
        for (String singleLine : receivedHeader)
        {
            StringTokenizer stringToken = new StringTokenizer(singleLine, " ");
            nextToken = stringToken.nextToken();
            if (nextToken.equals("GET") || nextToken.equals("POST") || nextToken.equals("HEAD") || nextToken.equals("PUT") || nextToken.equals("DELETE") || nextToken.equals("OPTIONS") || nextToken.equals("TRACE"))
            {
                headerType = nextToken;
                requestedFilePath = stringToken.nextToken();
                version = stringToken.nextToken().replaceAll("\r", "").replaceAll("\n", "");
            }
            else if (nextToken.equals("Host:"))
            {
                requestedHost = (stringToken.nextToken()).trim();
                if(requestedHost.contains(":"))
                requestedHost = requestedHost.substring(0, requestedHost.indexOf(":"));

            }
            else if (lineBefore.equals("/r/n"))
            {
                postKeyValueString = singleLine;
            }
            lineBefore = singleLine;
        }
        if (version.equals(""))
            version = "HTTP/1.0";
    }

    /**
     * Findet IfModiefiedSince und IfUnmidiefiertSince Informationen
     * und speichert dies als Attribute ab
     */
    private void checkModifiedUnmodifiedSince()
    {
        for (String singleLine : receivedHeader)
        {
            //Position des Eintrages Filtern
            int indexOfState = singleLine.indexOf("If-Modified-Since:");
            //Falls in dieser Zeile nicht gesetzt
            if (indexOfState == -1)
                continue;
            //Index des State + 19 Zeichen weiter steht das GMT-Datum
            modifiedSince = singleLine.substring(indexOfState + 19);
        }

        for (String singleLine : receivedHeader)
        {
            int indexOfState = singleLine.indexOf("If-Unmodified-Since:");
            if (indexOfState == -1)
                continue;
            unmodifiedSince = singleLine.substring(indexOfState + 21);
        }
    }

    /** Pfad der Requesteten Datei aus der URL filtern,
     * Anhand des Hostnamens den Entsprechenden Root-Pfad davor setzen
     * @return Pfad aus Root-Pfad\\Dateipfad
     */
    public String getFilePath() throws ClientException
    {
        String completeFilePath;
        if (requestedHost == null)
        {
            if (requestedFilePath.startsWith("http:"))
            {
                StringTokenizer stringTokens = new StringTokenizer(requestedFilePath, "/");
                stringTokens.nextToken();
                requestedHost = stringTokens.nextToken();
                requestedFilePath = requestedFilePath.replaceAll("http://" + requestedHost, "");
            }
            else
                throw new ClientException("400");
        }
        String rootOfHost = Settings.getRoot(requestedHost);
        if (rootOfHost == null)
            throw new ClientException("400");
        completeFilePath = (rootOfHost + requestedFilePath);
        completeFilePath = completeFilePath.replaceAll("/", Matcher.quoteReplacement("\\"));
        return completeFilePath;
    }
   
    /**
     * getter-Methoden
     */
    
    public String getVersion()
    {
        return version;
    }

    public String getKindOfRequest()
    {
        return headerType;
    }

    public String getRequestedFilePath()
    {
        return requestedFilePath;
    }
    
    public String getRequestedHost()
    {
        return requestedHost;
    }

    /**
     * aka keep alive
     */
    public String getPersistentConnection()
    {
        return persistentConnection;
    }

    public String getPostKeyValueString()
    {
        return postKeyValueString;
    }

    public String getModifiedSince()
    {
        return modifiedSince;
    }

    public String getUnModifiedSince()
    {
        return unmodifiedSince;
    }
}
