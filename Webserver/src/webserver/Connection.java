/*
 * Connection.java
 *
 * Created on 19. Juni 2007, 10:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
 * @author schwendemann.sebasti
 */
public class Connection extends Thread{
    
    /** Creates a new instance of Connection */
    Socket theSocket;
    BufferedReader input;
    OutputStream out;
    Message theMessage;
    DataInputStream dataInput;
    ClientRequest request;
    
    
    public Connection(Socket aSocket) 
    {
        try
        {   
            theSocket = aSocket;
            theSocket.setKeepAlive(true);
            input = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            out = theSocket.getOutputStream();
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim erstellen der Connection-Klasse: "+e.getMessage());
        }
    }
    
    public void run()
    {
        try{
            while(!(theSocket.isClosed())){
                if(theSocket.getInputStream().available()!=0){
                    sleep(50);
                    read();
                    if(!(request.getKeepAlive().equalsIgnoreCase("Keep-Alive")) || request.getHttpVersion().equalsIgnoreCase("HTTP/1.0"))
                        theSocket.close();
                    System.out.println("Socket: "+theSocket.getPort());
                }
                sleep(10);
            }    
        }catch(IOException e){
            System.out.println("Fehler in der persistenten Verbindung: "+ e.getMessage());
        }catch(InterruptedException e)
        {   
            System.out.println("Fehler, Socket wurde geschlossen: "+e.getMessage());
        }
    }
    
    private void read()
    {        
        try
        {                    
            request = new ClientRequest(theSocket.getInputStream());
            theMessage = new Message(request, out);
            String kindOfMessage = request.getKindOfRequest();
            if(kindOfMessage.equals("GET"))
            {
                if(Settings.use100Continue)
                {
                   theMessage.send100Continue();
                   try
                   {
                     sleep(5000);
                   }
                   catch(InterruptedException e)
                   {
                       System.out.println("100 continue - unable to sleep: "+e.getMessage());
                   }
                }
                theMessage.replyRequest(true);
            }
            else if(kindOfMessage.equals("POST"))
            {              
                theMessage.writePostToFile(request.getPostContent()); 
                new ReplySimpleSatus(out, request, RFC2616.HTTP_STATUS_204).generateResponse();
            }
            else if(kindOfMessage.equals("HEAD"))
            {
                theMessage.replyRequest(false);
            }
            else if(kindOfMessage.equals("PUT") || kindOfMessage.equals("DELETE") || kindOfMessage.equals("OPTIONS")
                    || kindOfMessage.equals("TRACE")){
                throw new ClientException(RFC2616.HTTP_STATUS_501);
            }       
            else
                throw new ClientException(RFC2616.HTTP_STATUS_400);
                
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim Ermitteln des Client-Requests: "+e.getMessage()); 
        }
        catch(ClientException e){
            System.out.println("Fehler: "+e.getMessage());
            new ReplyHeaderErrorStatus(out,e.getMessage(),request).generateResponse();
        }    
    }    
}
