/*
 * Host2RootFile.java
 * 
 * Created on 21.06.2007, 18:27:56
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



package webserver;

import java.io.*;
/**
 *
 * @author skolbens
 * Diese Klasse stellt Methoden zur Verfügung um die externe Datei auszulesen und zu beschreiben
 */
public class Host2RootFile {

    //name of the .txt file
    private static String filename = "Host2Root.txt";
    //Filewriter and FileReader for later use
    private static FileWriter FW = null;
    private static FileReader FR = null;
   
    //standard constructor
    public Host2RootFile() {
    }    
    /**
     * 
     * Methode liest die .txt Datei aus
     *  @return ein Host-Root String
     */
    public static String[] readFile(){
        
        String[] sta = new String[100];
        String st = null;
        try{
            FR = new FileReader(filename);
            BufferedReader BR = new BufferedReader(FR);
            int i = 0;
            do{
                st = BR.readLine();
                if(st != null){
                    sta[i] = st;
                    i++;
                }
            }
            while(st != null);
        }
            
        catch (IOException e){
        }
        return sta;
    }
    /**
     * 
     * Methode schreibt die Zeilen in die .txt Datei
     *  @param aString schreibt das String Array in die datei
     * 
     */
    public static void writeFile(String[] aString){
        try{
            int i = 0;
            FW = new FileWriter(filename);
            while(i <= aString.length - 1){
                FW.write(aString[i] + "\r\n");
                i++;
            }
            FW.close();
        }
        catch (IOException e){
            System.out.println("fdsddffd");
        }
    }
}
