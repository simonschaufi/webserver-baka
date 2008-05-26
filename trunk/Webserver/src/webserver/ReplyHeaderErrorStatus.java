/*
 * ReplyHeaderErrorStatus.java
 * 
 * Created on 22.06.2007, 12:01:11
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author fabian
 */
public class ReplyHeaderErrorStatus extends ReplyHeader{

    String errorHeader;
    /**
     * @param OutputStream; String; ClientRequest
     */
    public ReplyHeaderErrorStatus(OutputStream out, String errorHeader, ClientRequest request) {
        super(out,request);
        this.errorHeader = errorHeader; 
    }
    
    /**
     * @return void
     */
    public void generateResponse(){
        addLine(getHttpVersion()+" "+errorHeader);
        addDate();
        checkKeepAlive();
        addLine("Content-Type: text/html; charset=iso-8859-1");
        addLine("Content-Length: "+(generateErrorPage(errorHeader).getBytes()).length);
        sendHeader();
        try{  
            out.write(generateErrorPage(errorHeader).getBytes());
            out.write(RFC2616.CRLF.getBytes());
            out.flush();
        }catch(IOException e){
            System.out.println("Fehler beim Schicken der Fehlerstatus "+errorHeader+" :"+e.getMessage());
        }
        return;
    }
     
    /**
     * @param String
     * @return String
     */
     private String generateErrorPage(String anError){
                return "<html>\n"+
               " <head>\n"+
               "  <title>"+anError+"</title>\n"+
               " </head>\n"+
               " <body>\n"+
               "  <h1>Following Error occurred: "+anError+"</h1>\n"+
               "                                                                                                                                                                                                                                       "+
               "                                                                                                                                                                                                                                       "+
               "                                                                                                                                                                                                                                       \n"+
               " </body>\n"+
               "</html>"+RFC2616.CRLF;
    }
}
