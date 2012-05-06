/*
 * Holds all of the response details.
 */
package prufrock.http;
import java.util.Hashtable;
/**
 *
 * @author david
 */
public class Response 
{
    public static final String CONNECTION        = "Connection";
    public static final String CONTENTENCODING  = "Content-Encoding";
    public static final String CONTENTLENGTH    = "Content-Length";
    public static final String CONTENTTYPE       = "Content-Type";
    public static final String DATE              = "Date";
    public static final String LASTMODIFIED      = "Last-Modified";
    public static final String SERVER            = "Server";
    public static final String VARY              = "Vary";
    
    private int statusCode;
    private Hashtable<String, String> headers;
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
        this.headers = responseHeaders;
        this.body = body;
    }
    
    public Response setHeaders(Hashtable<String, String> responseHeaders)
    {
        this.headers = responseHeaders;
        return this;
    }
    
    public Hashtable<String, String> getHeaders()
    {
        return this.headers;
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