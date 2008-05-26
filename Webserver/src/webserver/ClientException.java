/*
 * ClientException.java
 * 
 * Created on 19.06.2007, 21:08:29
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package webserver;

/**
 *
 * @author Fabian Breuer
 * Eigene Klasse um Fehler zu schmeißen z.B. 400
 */
public class ClientException extends Exception{
/**
 * * 
 * Konstruktor 
 * @param anException der FehlerCode
 */
    public ClientException(String anException) {
        super(anException);
    }

}
