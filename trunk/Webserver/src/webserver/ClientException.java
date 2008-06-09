/*
 * ClientException.java
 */

package webserver;

/**
 *Kann eine Response nicht erfolgreich erstellt werden, werfen die
 * Jeweiligen Funktionen eien Client Exeption mit den Exeption Messages
 * wie 302 oder 402 etc. welche dann von CreateResponse Klasse ausgegeben werden
 */
public class ClientException extends Exception{
/**
 *  Konstruktor, nuztz Konstruktor der Oberklasse, öediglich die ExeptioMessage
 * wird Hier angegeben
 */
    public ClientException(String exeptionMessage) {
        super(exeptionMessage);
    }

}
