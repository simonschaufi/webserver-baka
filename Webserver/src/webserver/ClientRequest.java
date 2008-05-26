/*
 * ClientRequest.java
 *
 * Created on 22.06.2007, 18:13:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

/**
 *
 * @author fabian
 * Klasse die den Request speichert und parst
 */
public class ClientRequest {
    
    DataInputStream inputStream;
    byte[] bytesOfDataInputStream;
    ArrayList<String> request;
    
    String httpVersion = "";
    String kindOfRequest = "";
    String path = null;
    String host = null;
    String connection = "";
    String postContent= "";
    String IfModified = "";
    String IfUnModified = "";
 /**
 * * 
 * Konstruktor der den Input gleich zerlegt
 * @param in der InputStream des Sockets
 */
    public ClientRequest(InputStream in) {
        this.inputStream = new DataInputStream(in);
        this.request = new ArrayList<String>();
        getBytesInStream();
        requestToArrayList();
        getRequestInformation();
        InitModifiedStates();
    }
    /**
 * Teilt den Input in einzelne Arrays
 */
    private void requestToArrayList(){
        int length = bytesOfDataInputStream.length;
        String line="";
        for(int i=0;i<length;i++){
            if((i < length -2) && (bytesOfDataInputStream[i]!=13 &&bytesOfDataInputStream[i+1]!=10))
                line = line +(char)bytesOfDataInputStream[i];
            else{
                line = line +(char)bytesOfDataInputStream[i]+(char)bytesOfDataInputStream[i+1];
                request.add(line);
                line="";
                i++;
            }
        }
    }
    /**
 * Wandelt den Stream in ein byteArray
 * 
 */
    private void getBytesInStream(){
        try{
        int numberOfBytes = inputStream.available();
        bytesOfDataInputStream = new byte[numberOfBytes];
        inputStream.readFully(bytesOfDataInputStream);
        }catch(IOException e){
            System.out.println("Fehler beim lesen der Bytes im InputStream: "+e.getMessage());
        }
    }
    /**
 *  Teilt den Input in die verschieden Tokens auf
 */
    private void getRequestInformation(){
        String nextToken = "";
        String previousLine="";
        for(String currentLine:request){
            StringTokenizer st = new StringTokenizer(currentLine," ");
            nextToken = st.nextToken();
            if(nextToken.equals("GET") || nextToken.equals("POST") || nextToken.equals("HEAD") 
                    || nextToken.equals("PUT") || nextToken.equals("DELETE") || nextToken.equals("OPTIONS")
                    || nextToken.equals("TRACE")){
                kindOfRequest = nextToken;
                path = st.nextToken();
                httpVersion = st.nextToken().replaceAll("\r", "").replaceAll("\n", "");
            }
            else if(nextToken.equals("Host:")){
                host = (st.nextToken()).trim();
            }
            else if(nextToken.equals("Connection:")){
                connection = (st.nextToken()).trim();
            }
            else if(previousLine.equals("/r/n")){
                postContent=currentLine;
            }
            previousLine = currentLine;
        }
        if(httpVersion.equals(""))
            httpVersion = "HTTP/1.0";
    }
/**
 * 
 *  Ermttelt ob eine Modified Status überprüft werden soll
 */
    private void InitModifiedStates()
    {
        for(String currentLine:request)
        {
            int wo = currentLine.indexOf("If-Modified-Since:");
            if(wo == -1)
                continue;
            IfModified = currentLine.substring(wo + 19);            
        }        
        
        for(String currentLine:request)
        {
            int wo = currentLine.indexOf("If-Unmodified-Since:");
            if(wo == -1)
                continue;            
            IfUnModified = currentLine.substring(wo + 21);         
        }
    }
 /**
 * * 
 * Ermittlung des Dateipfades mit beachtung von Multihoming, sowie DirectURLs
  * @return Der Dateipfad als String
  * @throws ClientException falls Bad Request
 */
     public String getFilePath() throws ClientException
    {
        String filePath;
        if(host == null)
        {
            if(path.startsWith("http:"))
            {
                StringTokenizer st = new StringTokenizer(path, "/");
                st.nextToken();                     
                host = st.nextToken();
                path = path.replaceAll("http://" + host, "");
            }
            else
                throw new ClientException("400");
        }                
        String test = Settings.getRoot(host);
        if(test == null)
                throw new ClientException("400");
        filePath = (Settings.getRoot(host) + path);
        filePath = filePath.replaceAll("/", Matcher.quoteReplacement("\\"));
        return filePath;
    }
    
    public String getHttpVersion(){
        return httpVersion;
    }
    
    public String getKindOfRequest(){
        return kindOfRequest;
    }
    
    public String getPath(){
        return path;
    }
    
    public String getHost(){
        return host;
    }
    
    public String getKeepAlive(){
        return connection;
    }
    
    public String getPostContent(){
        return postContent;
    }
    
    public String getIfModified()
    {
        return IfModified;
    }
    
    public String getIfUnModified()
    {
        return IfUnModified;
    }
}
