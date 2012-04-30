/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prufrock.http;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author david
 */
public class Client 
{
    private prufrock.http.adapter.Interface httpClient;
    private List<Request> requests;
    
    public Client()
    {
        this.construct();
    }
    
    public Client(prufrock.http.adapter.Interface httpClient)
    {
        this.construct();
        this.httpClient = httpClient;
    }
    
    private void construct()
    {
        requests = new ArrayList<Request>();
    }
    
    public Request makeHttpRequest(Request request)
    {
        this.requests.add(request);

        int statusCode = 200;
        this.httpClient.prepareRequest();
        this.httpClient.transmitRequest();
        
        request.addResponse(httpClient.getStatusCode()
          , httpClient.getHeaders(), httpClient.getBody());
        return request;
    }
    
    public List<Request> getRequests()
    {
        return requests;
    }
    
    public Client resetRequests()
    {
        requests.clear();
        return this;
    }
}
