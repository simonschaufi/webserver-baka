/*
 * RFC2616.java
 * 
 * Created on 20.06.2007, 00:30:19
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author fabian
 */
public class RFC2616 {

    public static final String      DEFAULT_CHARSET   = "ISO-8859-1";
    
    public static final String      CRLF              = "\r\n";

    public static final String      CMD_QUIT          = "QUIT";

    public static final String      CMD_MIME          = "MIME";

    public static final String      CMD_CONFIG        = "CONFIG";

    public static final String      CONFIG_FILE       = "jhttpd.conf";

    public static final String      HTTP_METHOD_GET   = "GET";

    public static final String      HTTP_METHOD_HEAD  = "HEAD";

    public static final String      HTTP_METHOD_POST  = "POST";

    public static final String      PRODUCT_NAME      = "jHTTP";

    public static final String      PRODUCT_VERSION   = "0.5";

    public static final String      SIGNATURE         = PRODUCT_NAME + " " + PRODUCT_VERSION + " HTTP 1.1 Server";

    public static final String      HTTP_STATUS_200   = "200 OK";
    
    public static final String      HTTP_STATUS_204   = "204 No Content";
    
    public static final String      HTTP_STATUS_400   = "400 Bad Request";
    
    public static final String      HTTP_STATUS_403   = "403 Forbidden";
    
    public static final String      HTTP_STATUS_404   = "404 Not Found";
    
    public static final String      HTTP_STATUS_412   = "412 Precondition Failed";
    
    public static String            HTTP_STATUS_304   = "304 Not Modified Date: "+Settings.getTheTime();
    
    public static final String      HTTP_STATUS_501   = "501 Not Implemented";

    public RFC2616() {
    }
}
