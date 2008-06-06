/*
 * Cache.java
 * 
 * Created on 22.06.2007, 12:36:04
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author sebi
 * Klasse dient der Verwaltung des Caches der die Festplattenzugriff minimieren sol
 */
public class Cache
{

    static private HashMap<String, byte[]> cachePlace;
    static private Cache theCache = new Cache();

    /** *
     *  * Konstruktor, initialisiert die hashmap, welche den Cache beinhaltet
     */
    private Cache()
    {
        cachePlace = new HashMap<String, byte[]>();
    }

    /**
     * 
     *  Methode liest die .txt Datei aus
     *  @param filePath Der Dateipfad als String von der Datei die gecacht werden soll
     *  @return Datei gecacht als byte[]
     *  @throws IOException falls das lesen der Datei fehlschlägt
     */
    static public byte[] getFile(String filePath) throws IOException
    {
        if (cachePlace.get(filePath) == null)
        {
            //Überprüfung ob cache über 100 Element
            if (cachePlace.size() > 100)
                cachePlace.clear();
            File file2get = new File(filePath);
            byte[] dateiInByte = new byte[(int) file2get.length()];
            FileInputStream inputStream = new FileInputStream(file2get);
            inputStream.read(dateiInByte);
            cachePlace.put(filePath, dateiInByte);
            return dateiInByte;


        }
        return cachePlace.get(filePath);

    }
}
