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
 * @author  Simon Schaufelberger
 * Diese Klasse ist fuer die Uebertragung einer Nachricht zum Client verantwortlich
 */
public class Message
{

    // Erzeugt eine neue Instanz einer Nachricht
    Request clientRequest;
    OutputStream outputStream;
    File fileToRead;
    String filePath;
    Interface guiInterface;

    //Konstruktor
    public Message(Request clientRequest, OutputStream outputStream, Interface guiInterface) throws ClientException
    {
    	//Speichere die Objekte in die lokalen Objekte ab
        this.guiInterface = guiInterface;
        this.clientRequest = clientRequest;
        this.outputStream = outputStream;
        //Hole den Dateipfad und ersetzte HTML-formatierte Leerzeichen durch echte Leerzeichen
        String path = clientRequest.getFilePath().replace("%20", " ");
        fileToRead = new File(path);
        
        //Ausgabe auf der GUI
        guiInterface.printMessages("Root: " + fileToRead.getPath());
    }

    /**
     *  Liest die angeforderte Datei ein und gibt die Datei als ByteCode zurueck
     *  @param filePath: Pfad der angefordert wird
     *  @return eingelesene Datei als Bytearray
     *  @throws IOException, falls es zu einem Fehler beim lesen der Datei kommt
     */
    static public byte[] getFile(String filePath) throws IOException
    {
            File fileToRead = new File(filePath);
            //Legt ein Bytearray an
            byte[] dateiInByte = new byte[(int)fileToRead.length()];
            //wandelt die Datei in einen Stream um
            FileInputStream inputStream = new FileInputStream(fileToRead);
            //Liest sie ein...
            inputStream.read(dateiInByte);
            //...und gibt sie als Bytearray zurueck
            return dateiInByte;
    }

    /**
     *  Gibt enweder die Datei zurueck oder verschiedene Fehlermeldungen and den Browser 
     *  @param isGetRequest: ob es ein GET Request ist
     *  @return void
     *  @throws mehrere ClientExceptions, falls es zu einem Fehler kommt
     */
    public void replyRequest(boolean isGetRequest) throws ClientException
    {
        //Falls Datei existiert
        if (fileToRead.exists())
        {
            //und diese Datei lesbar (Zugriffsrechte gesetzt) ist
            if (fileToRead.canRead())
            {
            	//wenn es kein Ordner ist
                if (!fileToRead.isDirectory())
                {
                    //Abfrage, ob die Datei geaendert wurde
                    checkModifiedStates();
                    //Erzeute eine GET Antwort der Datei
                    new CreateResponse(outputStream, fileToRead, clientRequest, guiInterface).generateResponse("GET");
                    //Wenn der Parameter auf true ist, schick die Datei zum Browser
                    if (isGetRequest)
                        sendFile();
                }
                //wenn es doch ein Ordner ist
                else
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

	//Schreibt POST Request in eine Datei
    public void writePostToFile(String postRequest)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(Settings.postedContent, true);
            fileWriter.write("Zeit: " + Settings.getDate() + "; Inhalt: " + postRequest + "\n");
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException ioEx)
        {
            guiInterface.printMessages("Fehler beim Schreiben der POST-Informationen in die Datei. Fehlermeldung: " + ioEx.getMessage());
        }
    }

	//Sendet eine Datei als OutputStream
    public void sendFile() throws ClientException
    {
        try
        {
            ArrayList<Byte> lineBuffer = new ArrayList<Byte>();
            //Hole den Dateipfad und ersetzte HTML-formatierte Leerzeichen durch echte Leerzeichen
            String path = clientRequest.getFilePath().replace("%20", " ");
            //Hole die Datei und speichere sie in einem Bytearray ab
            byte[] file = getFile(path);
            //Speichere die Dateilaenge ab
            int fileLength = file.length;

            //Wenn in den Einstellungen chunked auf true ist und der Clientreqest der Version 1.1 entspricht
            if (Settings.chunkedData && clientRequest.getVersion().equalsIgnoreCase("HTTP/1.1"))
            {
                //Mache kleine Byte-stuecke
                byte[] chunk = new byte[Settings.chunkedDataSize];
                int i = 0;
                for (i = 0; i < fileLength; i++)
                {
                    //Sobald die chunk_size (oder ein Vielfaches davon) erreicht ist mach wieder ein neues Packet
                    if (i % (Settings.chunkedDataSize) == 0 && i > 0)
                    {
                        outputStream.write(Integer.toHexString(Settings.chunkedDataSize).getBytes());
                        outputStream.write(("\r\n").getBytes());
                        outputStream.write(chunk);
                        outputStream.write(("\r\n").getBytes());
                    }
                    //Hier stehen dann die einzelnen Packete in einem Array drin
                    chunk[i % (Settings.chunkedDataSize)] = file[i];
                }

                outputStream.write(Integer.toHexString((i % Settings.chunkedDataSize)).getBytes());
                outputStream.write(("\r\n").getBytes());

                for (int j = 0; j < (i % Settings.chunkedDataSize); j++)
                {
                    outputStream.write(chunk[j]);
                }
                
                outputStream.write(("\r\n").getBytes());
                //Ende der Datei (Hex 0)
                outputStream.write(Integer.toHexString(0).getBytes());
                outputStream.write(("\r\n").getBytes());
                outputStream.write(("\r\n").getBytes());
            }
            //alle anderen HTTP Versionen
            else
            {
                outputStream.write(file);
                outputStream.write(("\r\n").getBytes());
                outputStream.flush();
            }
        }
        catch (IOException ioEx)
        {
            guiInterface.printMessages("Das Senden der Datei schlug fehl: " + ioEx.getMessage());
        }
    }

	//ueberprueft, ob die Datei geaendert wurde
    private void checkModifiedStates() throws ClientException
    {
    	//Modified Test
        if (!clientRequest.getModifiedSince().equals(""))
        {
            StringTokenizer st = new StringTokenizer(clientRequest.getModifiedSince(), " ");
            st.nextToken();
            int[] DateFromClient = new int[6];
            DateFromClient[2] = Integer.parseInt(st.nextToken()); //Tag
            String month = st.nextToken(); //Monat

            if (month.equals("Jan"))
                DateFromClient[1] = 0;
            else
                if (month.equals("Feb"))
                    DateFromClient[1] = 1;
                else
                    if (month.equals("Mar"))
                        DateFromClient[1] = 2;
                    else
                        if (month.equals("Apr"))
                            DateFromClient[1] = 3;
                        else
                            if (month.equals("May"))
                                DateFromClient[1] = 4;
                            else
                                if (month.equals("Jun"))
                                    DateFromClient[1] = 5;
                                else
                                    if (month.equals("Jul"))
                                        DateFromClient[1] = 6;
                                    else
                                        if (month.equals("Aug"))
                                            DateFromClient[1] = 7;
                                        else
                                            if (month.equals("Sep"))
                                                DateFromClient[1] = 8;
                                            else
                                                if (month.equals("Oct"))
                                                    DateFromClient[1] = 9;
                                                else
                                                    if (month.equals("Nov"))
                                                        DateFromClient[1] = 10;
                                                    else
                                                        if (month.equals("Dec"))
                                                            DateFromClient[1] = 11;
            DateFromClient[0] = Integer.parseInt(st.nextToken()); //Yahr
            DateFromClient[3] = Integer.parseInt(st.nextToken(":").substring(1)); //Stunden
            DateFromClient[4] = Integer.parseInt(st.nextToken(":")); // Min     
            DateFromClient[5] = Integer.parseInt(st.nextToken(" ").substring(1)); // Sek
            int[] DateFromFile = new int[6];
            
            Date fileDate = new Date(fileToRead.lastModified());
            DateFromFile[0] = fileDate.getYear() + 1900;
            DateFromFile[1] = fileDate.getMonth();
            DateFromFile[2] = fileDate.getDay();
            DateFromFile[3] = fileDate.getHours();
            DateFromFile[4] = fileDate.getMinutes();
            DateFromFile[5] = fileDate.getSeconds();
            
            for (int i = 0; i <= 5; i++)
            {
                if (DateFromFile[i] < DateFromClient[i])
                    throw new ClientException("304");
                if (DateFromFile[i] > DateFromClient[i])
                    break;
            }

        }
        //Unmodified test
        if (!clientRequest.getUnModifiedSince().equals(""))
        {
            StringTokenizer st = new StringTokenizer(clientRequest.getUnModifiedSince(), " ");
            st.nextToken();
            int[] DateFromClient = new int[6];
            DateFromClient[2] = Integer.parseInt(st.nextToken()); //Tag
            String month = st.nextToken(); //Monat

            if (month.equals("Jan"))
                DateFromClient[1] = 0;
            else
                if (month.equals("Feb"))
                    DateFromClient[1] = 1;
                else
                    if (month.equals("Mar"))
                        DateFromClient[1] = 2;
                    else
                        if (month.equals("Apr"))
                            DateFromClient[1] = 3;
                        else
                            if (month.equals("May"))
                                DateFromClient[1] = 4;
                            else
                                if (month.equals("Jun"))
                                    DateFromClient[1] = 5;
                                else
                                    if (month.equals("Jul"))
                                        DateFromClient[1] = 6;
                                    else
                                        if (month.equals("Aug"))
                                            DateFromClient[1] = 7;
                                        else
                                            if (month.equals("Sep"))
                                                DateFromClient[1] = 8;
                                            else
                                                if (month.equals("Oct"))
                                                    DateFromClient[1] = 9;
                                                else
                                                    if (month.equals("Nov"))
                                                        DateFromClient[1] = 10;
                                                    else
                                                        if (month.equals("Dec"))
                                                            DateFromClient[1] = 11;
            DateFromClient[0] = Integer.parseInt(st.nextToken()); //Yahr
            DateFromClient[3] = Integer.parseInt(st.nextToken(":").substring(1)); //Stunden
            DateFromClient[4] = Integer.parseInt(st.nextToken(":")); //Min     
            DateFromClient[5] = Integer.parseInt(st.nextToken(" ").substring(1)); //Sek
            
            int[] DateFromFile = new int[6];
            
            Date fileDate = new Date(fileToRead.lastModified());
            DateFromFile[0] = fileDate.getYear() + 1900;
            DateFromFile[1] = fileDate.getMonth();
            DateFromFile[2] = fileDate.getDay();
            DateFromFile[3] = fileDate.getHours();
            DateFromFile[4] = fileDate.getMinutes();
            DateFromFile[5] = fileDate.getSeconds();
            
            for (int i = 0; i <= 5; i++)
            {
                if (DateFromFile[i] != DateFromClient[i])
                    throw new ClientException("412 Precondition Failed");
            }
        }
    }

    public void send100Continue()
    {
        try
        {
            String stringContinue = "HTTP/1.1 100 Continue\r\n\r\n";
            outputStream.write(stringContinue.getBytes());
        }
        catch (IOException ioEx)
        {
            guiInterface.printMessages("100 Continue Fehler");
        }
    }
}