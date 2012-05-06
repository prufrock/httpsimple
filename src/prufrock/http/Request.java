/*
 * Holds all of the request related goo.
 */
package prufrock.http;

import java.util.Hashtable;
/**
 *
 * @author david
 */
public class Request 
{
    public static final String HTTP_GET    = "GET";
    public static final String HTTP_PUT    = "PUT";
    public static final String HTTP_POST   = "POST";
    public static final String HTTP_DELETE = "DELETE";
    public static final String HTTP_HEAD   = "HEAD";
    
    private String url;
    private String method;
    private String host;
    private Hashtable<String, String> headers;
    private String body;
    private Response response;
    
    public Request()
    {
        this.url = "";
        this.method = HTTP_GET;
    }

    public Hashtable<String, String> getHeaders()
    {
        return headers;
    }

    public Request setHeaders(Hashtable<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    public String getMethod()
    {
        return this.method;
    }

    public Request setMethod(String method)
    {
        this.method = method;
        return this;
    }

    public String getUrl()
    {
        return url;
    }

    public Request setUrl(String url)
    {
        this.url = url;
        return this;
    }

    public Response getResponse()
    {
        return response;
    }

    public void setResponse(Response response)
    {
        this.response = response;
    }
    
    public Request addResponse(int statusCode
        , Hashtable<String, String> headers, String body)
    {
        this.response = new Response(statusCode, headers, body);
        return this;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body) 
    {
        this.body = body;
    }

    public Request setHost(String host) 
    {
        this.host = host;
        return this;
    }
    
    public String getHost()
    {
        return this.host;
    }
}
