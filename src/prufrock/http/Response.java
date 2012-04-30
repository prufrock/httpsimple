/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prufrock.http;
import java.util.Hashtable;
/**
 *
 * @author david
 */
public class Response 
{
    private int statusCode;
    private Hashtable<String, String> responseHeaders;
    private String body;
    
    public Response()
    {
        this.statusCode = 0;
        this.body = "";
    }
    
    public Response(int statusCode
        , Hashtable<String, String> responseHeaders, String body)
    {
        this.statusCode = statusCode;
        this.responseHeaders = responseHeaders;
        this.body = body;
    }
    
    public Response setResponseHeaders(Hashtable<String, String> responseHeaders)
    {
        this.responseHeaders = responseHeaders;
        return this;
    }
    
    public Hashtable<String, String> getResponseHeaders()
    {
        return this.responseHeaders;
    }
    
    public String getBody()
    {
        return this.body;
    }
    
    public Response setBody(String body)
    {
        this.body = body;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    
}