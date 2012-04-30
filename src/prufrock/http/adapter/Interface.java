/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prufrock.http.adapter;

import java.util.Hashtable;
/**
 *
 * @author david
 */
public interface Interface {
    
    public Interface prepareRequest();
    
    public Interface transmitRequest();
    
    public String getBody();
    
    public Hashtable<String, String> getHeaders();
    
    public int getStatusCode();
    
}
