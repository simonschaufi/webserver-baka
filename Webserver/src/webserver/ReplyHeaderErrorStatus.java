/*
 * ReplyHeaderErrorStatus.java.
 */
package webserver;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 *
 */
public class ReplyHeaderErrorStatus extends CreateResponse
{

    String errorHeader;
    private String Path;

    /**
     * @param OutputStream; String; ClientRequest
     */
    public ReplyHeaderErrorStatus(OutputStream out, String errorHeader, ClientRequest request, Interface i)
    {
        super(out, request, i);
        this.errorHeader = errorHeader;
    }

    
    void setPath(String path)
    {
        gui.printMessages("Path: " + path);
        this.Path = path;
    }
    
    /**
     * @return void
     */
    public void generateResponse()
    {
        if (errorHeader.equals("302 Found"))
        {
            addLine(getHttpVersion() + " " + errorHeader);
            addDate();
            checkKeepAlive();
            addLine("Location: " + this.Path + "index.html");
            addLine("\r\n");
            sendHeader();
        }
        else
        {
            addLine(getHttpVersion() + " " + errorHeader);
            addDate();
            checkKeepAlive();
            addLine("Content-Type: text/html; charset=iso-8859-1");
            addLine("Content-Length: " + (generateErrorPage(errorHeader).getBytes()).length);
            sendHeader();
            try
            {
                output.write(generateErrorPage(errorHeader).getBytes());
                output.write(("\r\n").getBytes());
                output.flush();
            }
            catch (IOException e)
            {
                gui.printMessages("Fehler beim Schicken der Fehlerstatus " + errorHeader + " :" + e.getMessage());
            }
        }
        return;
    }

    /**
     * @param String
     * @return String
     */
    private String generateErrorPage(
            String anError)
    {
        return "<html>\n" +
                " <head>\n" +
                "  <title>" + anError + "</title>\n" +
                " </head>\n" +
                " <body>\n" +
                "  <h1>Following Error occurred: " + anError + "</h1>\n" +
                "                                                                                                                                                                                                                                       " +
                "                                                                                                                                                                                                                                       " +
                "                                                                                                                                                                                                                                       \n" +
                " </body>\n" +
                "</html>\r\n";
    }
}
