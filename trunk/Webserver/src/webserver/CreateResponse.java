/*
 * ReplyHeader.java
 * 
 * Created on 22.06.2007, 11:22:51
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author fabian
 */
public abstract class CreateResponse
{

//    public static final String DEFAULT_CHARSET = "ISO-8859-1";
//    public static final String EMPTY = "\r\n";
//    public static final String CMD_QUIT = "QUIT";
//    public static final String CMD_MIME = "MIME";
//    public static final String CMD_CONFIG = "CONFIG";
//    public static final String CONFIG_FILE = "jhttpd.conf";
//    public static final String HTTP_METHOD_GET = "GET";
//    public static final String HTTP_METHOD_HEAD = "HEAD";
//    public static final String HTTP_METHOD_POST = "POST";
//    public static final String PRODUCT_NAME = "jHTTP";
//    public static final String PRODUCT_VERSION = "0.5";
//    public static final String SIGNATURE = PRODUCT_NAME + " " + PRODUCT_VERSION + " HTTP 1.1 Server";
//    public static final String HTTP_STATUS_200 = "200 OK";
//    public static final String HTTP_STATUS_204 = "204 No Content";
//    public static final String HTTP_STATUS_302 = "302 Found";
//    public static final String HTTP_STATUS_400 = "400 Bad Request";
//    public static final String HTTP_STATUS_403 = "403 Forbidden";
//    public static final String HTTP_STATUS_404 = "404 Not Found";
//    public static final String HTTP_STATUS_412 = "412 Precondition Failed";
//    public static String HTTP_STATUS_304 = "304 Not Modified Date: " + Settings.getTheTime();
//    public static final String HTTP_STATUS_501 = "501 Not Implemented";
    
    ClientRequest request;
    ArrayList<String> httpHeader;
    OutputStream output;
    String keepAlive;
    Interface gui;

    /**
     * Konstruktor. Speichert den Outputstream, den aktuellen Request und das GUI
     * als Attribute ab.
     * @param OutputStream; ClientRequest
     * 
     */
    public CreateResponse(OutputStream out, ClientRequest request, Interface i)
    {
        this.gui = i;
        httpHeader = new ArrayList<String>();
        this.output = out;
        this.request = request;
        this.keepAlive = request.getKeepAlive();

    }

    /**
     * Hilfsmethode
     * Fügt der Array-Liste immer eine Zeile des Header hinzu
     */
    public void addLine(String text)
    {
        httpHeader.add(text + "\r\n");
    }

    /**
     * @return void
     */
    public void sendHeader()
    {
        try
        {
            for (int i = 0; i < httpHeader.size(); i++)
            {
                output.write(httpHeader.get(i).getBytes());
                System.out.write(httpHeader.get(i).getBytes());
                gui.printMessages((httpHeader.get(i).toString()).trim());
            }

            output.write(("\r\n").getBytes());


        }
        catch (IOException e)
        {
            gui.printMessages("Fehler beim Senden des Response-Headers: " + e.getMessage());
        }

    }

    /**
     * @return void
     * 
     */
    public void addDate()
    {
        String line = "Date: " + Settings.getTheTime() + "\r\n";
        httpHeader.add(line);
    }

    /**
     * @return void
     */
    public void checkKeepAlive()
    {
        if (!(keepAlive.equalsIgnoreCase("")) && !(request.getHttpVersion().equalsIgnoreCase("HTTP/1.0")))
        {
            if (keepAlive.equals("Keep-Alive"))
            {
                addLine("Connection: keep-alive");
                addLine("Keep-Alive: timeout=3, max=100");
            }

            if (keepAlive.equals("close"))
            {
                addLine("Connection: close");
            }

        }
    }

    /**
     * @return void
     */
    public String getHttpVersion()
    {
        return request.getHttpVersion();
    }

    /**
     * @return void
     */
    public abstract void generateResponse();
}
