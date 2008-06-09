/*
 * Settings.java
 * 
 * Created on 19.06.2007, 15:21:26
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Beschreibung der Einstellungsmöglichkeiten des WebServer
 * @author Sebastian Schwendemann
 */
public class Settings {
    /** Verwendeter Port des WebServer */
//    public static int port = 80;
    /** Größe des verwendeten Sende-Puffers */
    //public static int bufferSize = 500;
    /** Gibt an ob der WebServer seine Daten nach RFC2616(HTTP_1.1) "Chunked" versendet */
    public static boolean chunked = true;
    /** Gibt die Größe eines sog. Chunks an */
    public static int chunk_size = 20;
    /** Gibt an ob 100Continue nach RFC2616 verwendet wird oder nicht */
    public static boolean use100Continue = false;
    
    /** HashMap zum Speichern der Hosts. Wird für MultiHoming benötigt */
    private static HashMap hosts = new HashMap();
   /** Gibt den Speicherplatz der POST-Request an. Diese werden in der entsprechenden *.txt Datei gespeichert */
    
    public static File postFile = new File("c:\\a\\post.txt");
    /** Einfaches Datumsformat zum Formatieren der Datumsausgabe in den HTTP-Headern */
    public static SimpleDateFormat theTime=null;
    
    public Settings() {
    }
    /** 
     * Hinzufügen einer neuen WebSeite, die gehostet werden soll
     * @param host Der entsprechende Host
     * @param root Das Wurzelverzeichnis für den neuen Host
     */
    public static void addHost(String host, String root)
    {
        hosts.put(host, root);
    }
    /**
     * Das Wurzelverzeichnis des entsprechenden Host ermitteln
     * @param host Der entsprechende Host
     * @return Das Wurzelverzeichnis des entsprechenden Host
     */
    public static String getRoot(String host)
    {
        return (String)hosts.get(host);
    }
    
    /**
     * Löschen eines Host aus der Hosting-Liste
     * @param host Der entsprechende Host
     */
    public static void deleteHost(String host){
        hosts.remove(host);
    }
    /**
     * Ermitteln des aktuellen Zeitstempels für die Reply-Header
     * @return Den aktuellen Zeitstempel, formatiert nach Vorgabe
    */
    public static String getDate(){
        if(theTime==null){
            theTime = new java.text.SimpleDateFormat( "E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            theTime.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        return theTime.format( new Date());
    }
    
}
