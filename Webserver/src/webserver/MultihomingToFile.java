/*
 * MultihomingToFile.java
 */
package webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**@author JörgMogielinski
 * Multihoming Einträge die im GUI angegeben werden, werden mit dieser Klasse in 
 * ein externes Textfile gespeicher, sodass dies beim Neustarten des Servers wieder
 * zur verfügung steht, und nicht nocheinmal alles neu angegeben werden muss.
 */
public class MultihomingToFile
{

    /**
     * Filename in den Gespeichert werden soll
     */
    private static String filename = "Multihoming.txt";
    
    private static FileWriter filewriter = null;
    private static FileReader filereader = null;

    //standard constructor
    public MultihomingToFile()
    {
    }

    /**
     * Liest das Multihoming-File aus und speichert jede Zeile in einem Array
     * @return Array mit jeder Zeile des Textfiles in einem Feld 
     */
    public static String[] getFileContent()
    {


        String[] txtAll = new String[100];
        String txtLine = null;
        try
        {
            //FileReader soll aus Multihoming.txt lesen
            filereader = new FileReader(filename);

            //BufferedReader soll FileReader benutzen
            BufferedReader bufferedreader = new BufferedReader(filereader);
            int i = 0;

            //Solange die eingelesene Zeile im Textfile nicht leer ist, 
            //Zeile im array Speichern
            do
            {
                txtLine = bufferedreader.readLine();
                if (txtLine != null)
                {
                    txtAll[i] = txtLine;
                    i++;
                }
            } while (txtLine != null);
        }
        catch (IOException e)
        {
        }
        return txtAll;
    }

    /**
     * 
     * Schreibt die Listenelement, übergeben als Array in die Multihoming.txt
     *  @param MultihomingListElements Array, in jedem Feld steht eine Zeile 
     * aus der AWT-List des GUIs
     */
    public static void setFileContent(String[] MultihomingListElements)
    {
        try
        {
            int i = 0;
            filewriter = new FileWriter(filename);
            //Jede Zeile (Feld des Arrays) in eine neue Zeile des Files schreiben
            while (i < MultihomingListElements.length)
            {
                filewriter.write(MultihomingListElements[i] + "\r\n");
                i++;
            }
            filewriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim schreiben in " + filename);
        }
    }
}
