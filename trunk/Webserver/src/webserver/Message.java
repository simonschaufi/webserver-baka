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
import java.io.FileInputStream;
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
public class Message
{

    /** Creates a new instance of Message */
    ClientRequest request;
    OutputStream output;
    File file2get;
    String filePath;
    Interface gui;

    public Message(ClientRequest request, OutputStream out, Interface i) throws ClientException
    {
        this.gui = i;
        this.request = request;
        this.output = out;
        String path = request.getFilePath().replace("%20", " ");
        file2get = new File(path);
        gui.printMessages("Root: " + file2get.getPath());
    }

    /**
     *  Liest die angeforderte Datei ein und Wandelt sie in ByteCode um damit diese
     * verschickt werden kann
     *  @param filePath Dateiname + Pfad der angefordert wird
     *  @return Eingelesenes File als Byte array
     *  @throws IOException falls das lesen der Datei fehlschlägt
     */
    static public byte[] getFile(String filePath) throws IOException
    {
            File file2get = new File(filePath);
            byte[] dateiInByte = new byte[(int) file2get.length()];
            FileInputStream inputStream = new FileInputStream(file2get);
            inputStream.read(dateiInByte);
            return dateiInByte;
    }
        
    
    public void replyRequest(boolean isGetRequest) throws ClientException
    {

        if (file2get.exists())
        {
            if (file2get.canRead())
            {
                if (!file2get.isDirectory())
                {
                    checkModifiedStates();
                    new CreateResponse(output, file2get, request, gui  ).generateResponse("GET");
                    if (isGetRequest)
                        sendFile();
                }
                else
                    if (file2get.isDirectory())
                    {
                        throw new ClientException("302 Found");
                    }
            }
            else
            {
                throw new ClientException("403 Forbidden");
            }
        }
        else
        {
            throw new ClientException("404 Not Found");
        }
    }

    public void writePostToFile(String aPost)
    {
        try
        {
            FileWriter writer = new FileWriter(Settings.postFile, true);
            writer.write(Settings.getTheTime() + " --> " + aPost + "\n");
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            gui.printMessages("Fehler beim Schreiben der Post-Datei: " + e.getMessage());
        }

    }

    public void sendFile() throws ClientException
    {
        try
        {
            ArrayList<Byte> lineBuffer = new ArrayList<Byte>();
            String path = request.getFilePath().replace("%20", " ");
            byte[] file = getFile(path);
            int fileLength = file.length;

            if (Settings.chunked && !(request.getHttpVersion().equalsIgnoreCase("HTTP/1.0")))
            {

                byte[] chunk = new byte[Settings.chunk_size];
                int i = 0;
                for (i = 0; i < fileLength; i++)
                {

                    if (i % (Settings.chunk_size) == 0 && i > 0)
                    {
                        output.write(Integer.toHexString(Settings.chunk_size).getBytes());
                        output.write(("\r\n").getBytes());
                        output.write(chunk);
                        output.write(("\r\n").getBytes());
                    }
                    chunk[i % (Settings.chunk_size)] = file[i];
                }

                output.write(Integer.toHexString((i % Settings.chunk_size)).getBytes());
                output.write(("\r\n").getBytes());

                for (int j = 0; j < (i % Settings.chunk_size); j++)
                {
                    output.write(chunk[j]);
                }

                output.write(("\r\n").getBytes());
                output.write(Integer.toHexString(0).getBytes());
                output.write(("\r\n").getBytes());
                output.write(("\r\n").getBytes());
            }
            else
            {
                output.write(file);
                output.write(("\r\n").getBytes());
                output.flush();
            }

        }
        catch (IOException e)
        {
            gui.printMessages("Das Senden der Datei schlug fehl: " + e.getMessage());
        }
    }

    private void checkModifiedStates() throws ClientException
    {
        //modified test
        if (!request.getIfModified().equals(""))
        {
            StringTokenizer st = new StringTokenizer(request.getIfModified(), " ");
            st.nextToken();
            int[] DateFromClient = new int[6];
            DateFromClient[2] = Integer.parseInt(st.nextToken()); //Tag
            String Smonth = st.nextToken();//Monat

            if (Smonth.equals("Jan"))
                DateFromClient[1] = 0;
            else
                if (Smonth.equals("Feb"))
                    DateFromClient[1] = 1;
                else
                    if (Smonth.equals("Mar"))
                        DateFromClient[1] = 2;
                    else
                        if (Smonth.equals("Apr"))
                            DateFromClient[1] = 3;
                        else
                            if (Smonth.equals("May"))
                                DateFromClient[1] = 4;
                            else
                                if (Smonth.equals("Jun"))
                                    DateFromClient[1] = 5;
                                else
                                    if (Smonth.equals("Jul"))
                                        DateFromClient[1] = 6;
                                    else
                                        if (Smonth.equals("Aug"))
                                            DateFromClient[1] = 7;
                                        else
                                            if (Smonth.equals("Sep"))
                                                DateFromClient[1] = 8;
                                            else
                                                if (Smonth.equals("Oct"))
                                                    DateFromClient[1] = 9;
                                                else
                                                    if (Smonth.equals("Nov"))
                                                        DateFromClient[1] = 10;
                                                    else
                                                        if (Smonth.equals("Dec"))
                                                            DateFromClient[1] = 11;
            DateFromClient[0] = Integer.parseInt(st.nextToken()); //Yahr
            DateFromClient[3] = Integer.parseInt(st.nextToken(":").substring(1)); //Stunden
            DateFromClient[4] = Integer.parseInt(st.nextToken(":")); // min     
            DateFromClient[5] = Integer.parseInt(st.nextToken(" ").substring(1)); // sek
            int[] DateFromFile = new int[6];
            Date fileDate = new Date(file2get.lastModified());
            DateFromFile[0] = fileDate.getYear() + 1900;
            DateFromFile[1] = fileDate.getMonth();
            DateFromFile[2] = fileDate.getDay();
            DateFromFile[3] = fileDate.getHours();
            DateFromFile[4] = fileDate.getMinutes();
            DateFromFile[5] = fileDate.getSeconds();
            for (int ik = 0; ik <= 5; ik++)
            {
                if (DateFromFile[ik] < DateFromClient[ik])
                    throw new ClientException("304");
                if (DateFromFile[ik] > DateFromClient[ik])
                    break;
            }

        }
        //Unmodified test
        if (!request.getIfUnModified().equals(""))
        {
            StringTokenizer st = new StringTokenizer(request.getIfUnModified(), " ");
            st.nextToken();
            int[] DateFromClient = new int[6];
            DateFromClient[2] = Integer.parseInt(st.nextToken()); //Tag
            String Smonth = st.nextToken();//Monat

            if (Smonth.equals("Jan"))
                DateFromClient[1] = 0;
            else
                if (Smonth.equals("Feb"))
                    DateFromClient[1] = 1;
                else
                    if (Smonth.equals("Mar"))
                        DateFromClient[1] = 2;
                    else
                        if (Smonth.equals("Apr"))
                            DateFromClient[1] = 3;
                        else
                            if (Smonth.equals("May"))
                                DateFromClient[1] = 4;
                            else
                                if (Smonth.equals("Jun"))
                                    DateFromClient[1] = 5;
                                else
                                    if (Smonth.equals("Jul"))
                                        DateFromClient[1] = 6;
                                    else
                                        if (Smonth.equals("Aug"))
                                            DateFromClient[1] = 7;
                                        else
                                            if (Smonth.equals("Sep"))
                                                DateFromClient[1] = 8;
                                            else
                                                if (Smonth.equals("Oct"))
                                                    DateFromClient[1] = 9;
                                                else
                                                    if (Smonth.equals("Nov"))
                                                        DateFromClient[1] = 10;
                                                    else
                                                        if (Smonth.equals("Dec"))
                                                            DateFromClient[1] = 11;
            DateFromClient[0] = Integer.parseInt(st.nextToken()); //Yahr
            DateFromClient[3] = Integer.parseInt(st.nextToken(":").substring(1)); //Stunden
            DateFromClient[4] = Integer.parseInt(st.nextToken(":")); // min     
            DateFromClient[5] = Integer.parseInt(st.nextToken(" ").substring(1)); // sek
            int[] DateFromFile = new int[6];
            Date fileDate = new Date(file2get.lastModified());
            DateFromFile[0] = fileDate.getYear() + 1900;
            DateFromFile[1] = fileDate.getMonth();
            DateFromFile[2] = fileDate.getDay();
            DateFromFile[3] = fileDate.getHours();
            DateFromFile[4] = fileDate.getMinutes();
            DateFromFile[5] = fileDate.getSeconds();
            for (int ik = 0; ik <= 5; ik++)
            {
                if (DateFromFile[ik] != DateFromClient[ik])
                    throw new ClientException("412 Precondition Failed");
            }
        }
    }

    public void send100Continue()
    {
        try
        {
            String con = "HTTP/1.1 100 Continue" + ("\r\n") + ("\r\n");
            output.write(con.getBytes());
        }
        catch (IOException e)
        {
            gui.printMessages("100 Continue Fehler");
        }
    }
}
