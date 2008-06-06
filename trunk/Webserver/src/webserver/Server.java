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
    Interface gui =null;

    /** Creates a new instance of Server */
    public Server(Interface i)
    {
        this.gui = i;
        try
        {
            socket = new ServerSocket(Settings.port);

        }
        catch (IOException e)
        {
            gui.printMessages("Fehler beim Erstellen des Socket: " + e.getMessage());
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
                con = new Connection(socket.accept(), gui);
                if (con != null)
                    con.start();
            }
            catch (IOException e)
            {
                if (e.getMessage().equals("socket closed"))
                    gui.printMessages("Der Server-Socket wurde geschlossen");
                else
                    gui.printMessages("Fehler beim Oeffnen des Client-Socket: " + e.getMessage());
            }
            catch (NullPointerException e)
            {
                gui.printMessages("Fehler, Port 80 wird bereits benutzt: " + e.toString());
                run = false;
            }
        }
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            gui.printMessages("Fehler beim Schließen des Sockets: " + e.getMessage());
        }

        gui.printMessages("Server wird beendet");
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
            gui.printMessages("Fehler beim Schließen des Sockets: " + e.getMessage());
        }
    }
}
