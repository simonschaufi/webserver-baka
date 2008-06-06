/*
 * Server.java
 *
 * Created on 19. Juni 2007, 10:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package webserver;

import java.io.*;
import java.net.*;

/**
 *
 * @author schwendemann.sebasti
 */
public class Server extends Thread
{

    public ServerSocket socket;
    Connection con;
    Boolean run = true;

    /** Creates a new instance of Server */
    public Server()
    {
        try
        {
            socket = new ServerSocket(Settings.port);

        }
        catch (IOException e)
        {
            System.out.println("Fehler beim Erstellen des Socket: " + e.getMessage());
        }

    }

    /**
     * @return void
     */
    public void run()
    {
        while (run)
        {
            try
            {
                con = new Connection(socket.accept());
                if (con != null)
                    con.start();
            }
            catch (IOException e)
            {
                if (e.getMessage().equals("socket closed"))
                    System.out.println("Der Server-Socket wurde geschlossen");
                else
                    System.out.println("Fehler beim Oeffnen des Client-Socket: " + e.getMessage());
            }
            catch (NullPointerException e)
            {
                System.out.println("Fehler, Port 80 wird bereits benutzt: " + e.toString());
            }
        }
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim Schließen des Sockets: " + e.getMessage());
        }

        System.out.println("Server wird beendet");
    }

    /**
     * @return void
     */
    public void closeServer()
    {
        try
        {
            run = false;
            socket.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim Schließen des Sockets: " + e.getMessage());
        }
    }
}
