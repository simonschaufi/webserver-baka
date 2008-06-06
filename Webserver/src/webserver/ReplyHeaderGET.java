package webserver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Reply200Header.java
 * 
 * Created on 22.06.2007, 11:42:20
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import javax.activation.MimetypesFileTypeMap;

/**
 *
 * @author fabian
 */
public class ReplyHeaderGET extends CreateResponse{
   
    File file2get;
    String keepAlive;
    Interface gui;
    
    
    /**
     * @param OutputStream; File; ClientRequest
     */
    public ReplyHeaderGET(OutputStream out, File file2get,ClientRequest request, Interface i) {
        super(out,request, i);
        this.file2get = file2get;
        this.gui = i;
    }
    
    /**
     * @return void
     */
    public void sendHeader() {
        super.sendHeader();
    }
    
    /**
     * @return void
     */
    public void generateResponse(){
        addLine(getHttpVersion()+" 200 OK");
        addDate();
        checkKeepAlive();
        checkChunked();
        sendHeader();        
    }
    
    /**
     * @return void
     */
    public void checkChunked(){
        try{
        if(Settings.chunked && !(request.getHttpVersion().equalsIgnoreCase("HTTP/1.0"))){

            addLine("Content-Type: "+file2get.toURI().toURL().openConnection().getContentType());
            addLine("Transfer-Encoding: chunked");
        }else{
            addLine("Content-Length: "+file2get.length());
            addLine("Content-Type: "+file2get.toURI().toURL().openConnection().getContentType());
        } 
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
