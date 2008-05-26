/*
 * Message.java
 *
 * Created on 19. Juni 2007, 11:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;


/**
 *
 * @author schwendemann.sebasti
 */
public class Message {
    
    /** Creates a new instance of Message */
    ClientRequest request;
    OutputStream out;
    File file2get;
    String filePath;
    
    public Message(ClientRequest request, OutputStream out) throws ClientException {
        this.request = request;
        this.out = out;
        file2get = new File(request.getFilePath());
    }
            
    public void replyRequest(boolean isGetRequest) throws ClientException{

        if(file2get.exists()){
            if(file2get.canRead()){
                if(!file2get.isDirectory()){
                    checkModifiedStates();
                    new ReplyHeaderGET(out,file2get,request).generateResponse();
                    if(isGetRequest)
                        sendFile();
                }
                else if(file2get.isDirectory()){
                    throw new ClientException(RFC2616.HTTP_STATUS_403);
                }
            }else{
                throw new ClientException(RFC2616.HTTP_STATUS_403);
            }   
        }else{
            throw new ClientException(RFC2616.HTTP_STATUS_404);
        }
    }
    
    public void writePostToFile(String aPost){
        try{
            FileWriter writer = new FileWriter(Settings.postFile,true);
            writer.write(Settings.getTheTime()+" --> "+aPost+"\n");
            writer.flush();
            writer.close();
        }catch(IOException e){
            System.out.println("Fehler beim Schreiben der Post-Datei: "+e.getMessage());
        }
        
    }
        
    public void sendFile() throws ClientException{
        try{
            ArrayList<Byte> lineBuffer = new ArrayList<Byte>();
            byte[] file = Cache.getFile(request.getFilePath());
            int fileLength = file.length;
            
            if(Settings.chunked && !(request.getHttpVersion().equalsIgnoreCase("HTTP/1.0"))){
                
                byte[] chunk = new byte[Settings.chunk_size];
                int i=0;
                for(i=0;i<fileLength;i++){   
                        
                    if(i%(Settings.chunk_size)==0 && i>0){
                        out.write(Integer.toHexString(Settings.chunk_size).getBytes());
                        out.write(RFC2616.CRLF.getBytes());
                        out.write(chunk);
                        out.write(RFC2616.CRLF.getBytes());
                    }
                    chunk[i%(Settings.chunk_size)] = file[i];
                }
                
                out.write(Integer.toHexString((i%Settings.chunk_size)).getBytes());
                out.write(RFC2616.CRLF.getBytes());  
                    
                for(int j=0;j<(i%Settings.chunk_size);j++)
                    out.write(chunk[j]);
                    
                out.write(RFC2616.CRLF.getBytes());  
                out.write(Integer.toHexString(0).getBytes());
                out.write(RFC2616.CRLF.getBytes());
                out.write(RFC2616.CRLF.getBytes());
            }else{
                out.write(file);             
                out.write(RFC2616.CRLF.getBytes());
                out.flush(); 
            }
            
        }catch(IOException e){System.out.println("Das Senden der Datei schlug fehl: "+e.getMessage());}
    }

private void checkModifiedStates() throws ClientException
    {       
        //modified test
        if(!request.getIfModified().equals(""))
        {            
            StringTokenizer st = new StringTokenizer(request.getIfModified(), " ");
            st.nextToken();
            int []DateFromClient = new int[6];
            DateFromClient[2] = Integer.parseInt(st.nextToken()); //Tag
            String Smonth = st.nextToken();//Monat
            
            if(Smonth.equals("Jan"))
                DateFromClient[1] = 0;
            else if(Smonth.equals("Feb"))
                DateFromClient[1]  = 1;
            else if(Smonth.equals("Mar"))
                DateFromClient[1]  = 2;
            else if(Smonth.equals("Apr"))
                DateFromClient[1]  = 3;
            else if(Smonth.equals("May"))
                DateFromClient[1]  = 4;
            else if(Smonth.equals("Jun"))
                DateFromClient[1]  = 5;
            else if(Smonth.equals("Jul"))
                DateFromClient[1]  = 6;
            else if(Smonth.equals("Aug"))
                DateFromClient[1]  = 7;
            else if(Smonth.equals("Sep"))
                DateFromClient[1]  = 8;
            else if(Smonth.equals("Oct"))
                DateFromClient[1]  = 9;
            else if(Smonth.equals("Nov"))
                DateFromClient[1]  = 10;
            else if(Smonth.equals("Dec"))
                DateFromClient[1]  = 11;
            DateFromClient[0]  = Integer.parseInt(st.nextToken()); //Yahr
            DateFromClient[3]  = Integer.parseInt(st.nextToken(":").substring(1)); //Stunden
            DateFromClient[4]  = Integer.parseInt(st.nextToken(":")); // min     
            DateFromClient[5]  = Integer.parseInt(st.nextToken(" ").substring(1)); // sek
            int [] DateFromFile = new int[6];
            Date fileDate = new Date(file2get.lastModified());
            DateFromFile[0] = fileDate.getYear()+1900;
            DateFromFile[1] = fileDate.getMonth();
            DateFromFile[2] = fileDate.getDay();
            DateFromFile[3] = fileDate.getHours();
            DateFromFile[4] = fileDate.getMinutes();
            DateFromFile[5] = fileDate.getSeconds();
            for(int ik= 0;ik <=5;ik++)
            {
                if(DateFromFile[ik] <DateFromClient[ik])
                    throw new ClientException("304"); 
                if(DateFromFile[ik] >DateFromClient[ik])
                        break;
            }
               
        }
        //Unmodified test
        if(!request.getIfUnModified().equals(""))
        {                   
            StringTokenizer st = new StringTokenizer(request.getIfUnModified(), " ");
            st.nextToken();
            int []DateFromClient = new int[6];
            DateFromClient[2] = Integer.parseInt(st.nextToken()); //Tag
            String Smonth = st.nextToken();//Monat
            
            if(Smonth.equals("Jan"))
                DateFromClient[1] = 0;
            else if(Smonth.equals("Feb"))
                DateFromClient[1]  = 1;
            else if(Smonth.equals("Mar"))
                DateFromClient[1]  = 2;
            else if(Smonth.equals("Apr"))
                DateFromClient[1]  = 3;
            else if(Smonth.equals("May"))
                DateFromClient[1]  = 4;
            else if(Smonth.equals("Jun"))
                DateFromClient[1]  = 5;
            else if(Smonth.equals("Jul"))
                DateFromClient[1]  = 6;
            else if(Smonth.equals("Aug"))
                DateFromClient[1]  = 7;
            else if(Smonth.equals("Sep"))
                DateFromClient[1]  = 8;
            else if(Smonth.equals("Oct"))
                DateFromClient[1]  = 9;
            else if(Smonth.equals("Nov"))
                DateFromClient[1]  = 10;
            else if(Smonth.equals("Dec"))
                DateFromClient[1]  = 11;
            DateFromClient[0]  = Integer.parseInt(st.nextToken()); //Yahr
            DateFromClient[3]  = Integer.parseInt(st.nextToken(":").substring(1)); //Stunden
            DateFromClient[4]  = Integer.parseInt(st.nextToken(":")); // min     
            DateFromClient[5]  = Integer.parseInt(st.nextToken(" ").substring(1)); // sek
            int [] DateFromFile = new int[6];
            Date fileDate = new Date(file2get.lastModified());
            DateFromFile[0] = fileDate.getYear()+1900;
            DateFromFile[1] = fileDate.getMonth();
            DateFromFile[2] = fileDate.getDay();
            DateFromFile[3] = fileDate.getHours();
            DateFromFile[4] = fileDate.getMinutes();
            DateFromFile[5] = fileDate.getSeconds();
            for(int ik= 0;ik <=5;ik++)
            {
                if(DateFromFile[ik] !=DateFromClient[ik])
                    throw new ClientException(RFC2616.HTTP_STATUS_412);     
            }  
        }
}

public void send100Continue(){
    try{
            String con = "HTTP/1.1 100 Continue" + RFC2616.CRLF +RFC2616.CRLF;
            out.write(con.getBytes());
        }
        catch(IOException e)
        {
        System.out.println("100 Continue Fehler");
        }
    }
}