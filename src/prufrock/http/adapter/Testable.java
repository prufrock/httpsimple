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
public class Testable implements prufrock.http.adapter.Interface {

    public Testable prepareRequest() 
    {
        return this;
    }

    public Testable transmitRequest() 
    {
        return this;
    }
    
    public int getStatusCode()
    {
        return 200;
    }
    
    public String getBody()
    {
        return "OK!";    
    }
    
    public Hashtable<String, String> getHeaders()
    {
        Hashtable headers = new Hashtable<String, String>();
        headers.put("Content-Length", "1162");
        
        return headers;
    }
}
