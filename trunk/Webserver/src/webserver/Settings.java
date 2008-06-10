/*
 * Settings.java
 */

package webserver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Verschidene Settings des Webservers werden hier gespeichert,
 * Verwaltung des MultiHoming finder hier statt
 */
public class Settings {
    
    /** Wird 100 Continue verwendet?*/
    public static boolean use100Continue = false;
    
    /** HashMap zum Speichern der Hosts. Wird für MultiHoming benötigt */
    private static HashMap multihomingAllHosts = new HashMap();
    
    /** POST werte werden vom server nicht verarbeitet sondern nur in folgendes 
    * Textfile geschrieben */
    public static File postedContent = new File("c:\\webserver\\post.txt");
    
    
    /**
     * Pflicht Konstruktor
     */
    public Settings() 
    {
    }
    
    
    /**
     * Liefert das Verzeichnis der Dateien für den angegebenen Host
     * @param host Host dessen Root verzeichnis ma möchte
     * @return amgefragtes Rootverzeichnis des Hosts
     */
    public static String getRoot(String host)
    {
        return (String)multihomingAllHosts.get(host);
    }
  
    /** 
     * Fügt einen neue Host/Root Kombination in die HashMap ein
     * @param host Name des Hoste
     * @param root Verzeichnis in dem die dateien des neuen Hosts liegen müssen
     */
    public static void addHost(String host, String root)
    {
        multihomingAllHosts.put(host, root);
    }
    
    /**
     * Löscht den Host und die dazugehörige Root verzeichnis aus
     * der HashMap
     * @param host Host der gelöscht werden soll
     */
    public static void deleteHost(String host)
    {
        multihomingAllHosts.remove(host);
    }
    
    /** Standad Datumsformat, wird einheitlich in allen Headern verwendet*/
    public static SimpleDateFormat timeString=null;
    
    /**
     * Liefert die Aktuelle GMT Zeit für Header
     * @return Aktuelles Datum nach GMT
    */
    public static String getDate()
    {
        if(timeString==null){
            timeString = new java.text.SimpleDateFormat( "E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            timeString.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        return timeString.format( new Date());
    }
    
}
