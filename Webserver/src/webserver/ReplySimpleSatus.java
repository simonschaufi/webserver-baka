/*
 * ReplySimpleSatus.java
 * 
 * Created on 23.06.2007, 12:17:27
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

import java.io.OutputStream;

/**
 *
 * @author sebi
 */
public class ReplySimpleSatus extends ReplyHeader{

    private String statusCode;
    /**
     * @param OutputStream; ClientRequest; String
     */
    public ReplySimpleSatus(OutputStream out, ClientRequest request, String statusCode) {
        super(out,  request);
        this.statusCode = statusCode;
        
    }
    /**
     * @return void
     */
     public  void generateResponse()
     {
        addLine(getHttpVersion()+" "+statusCode);
        addDate();
        checkKeepAlive();
        sendHeader();
        return;
     }
}
