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
public abstract class ReplyHeader {

    ClientRequest request;
    ArrayList<String> head;
    OutputStream out;
    String keepAlive;
    
    /**
     * @param OutputStream; ClientRequest
     * 
     */ 
    public ReplyHeader(OutputStream out, ClientRequest request) {
        head =  new ArrayList<String>();
        this.out = out;
        this.request = request;
        this.keepAlive = request.getKeepAlive();
        
    }
    
    /**
     * @param String
     * @return void
     */
    public void addLine(String aLine){
        head.add(aLine+RFC2616.CRLF);
    }
    
    /**
     * @return void
     */
    public void sendHeader(){
        try{
        for(int i=0;i<head.size();i++){
            out.write(head.get(i).getBytes());
            System.out.write(head.get(i).getBytes());
        }
        out.write(RFC2616.CRLF.getBytes());
    }
        catch(IOException e)
        {
            System.out.println("Fehler beim Senden des Response-Headers: "+e.getMessage());
        }
    }
    /**
     * @return void
     * 
     */
 public void addDate(){
        String line = "Date: " + Settings.getTheTime() +RFC2616.CRLF;
        head.add(line);
    }  
 
 /**
  * @return void
  */
 public void checkKeepAlive(){
     if(!(keepAlive.equalsIgnoreCase("")) && !(request.getHttpVersion().equalsIgnoreCase("HTTP/1.0"))){
            if(keepAlive.equals("Keep-Alive")){
                addLine("Connection: keep-alive");
                addLine("Keep-Alive: timeout=3, max=100");
            }
            if(keepAlive.equals("close")){
                addLine("Connection: close");
            }       
        }
 }
 
 /**
  * @return void
  */
 public String getHttpVersion(){
    return request.getHttpVersion();
 }
 
 /**
  * @return void
  */
 public abstract void generateResponse();

}
