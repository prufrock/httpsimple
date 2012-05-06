/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prufrock.http.adapter;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Hashtable;
import org.apache.http.*;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author david
 */
public class Apache {
    private String body;
    private prufrock.http.Request request;
    public Apache()
    {
        
    }
    
    public Apache transmitRequest(prufrock.http.Request request)
    {
        try {
        HttpParams params = new SyncBasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);

        HttpProcessor httpproc = new ImmutableHttpProcessor(new HttpRequestInterceptor[] {
                // Required protocol interceptors
                new RequestContent(),
                new RequestTargetHost(),
                // Recommended protocol interceptors
                new RequestConnControl(),
                new RequestUserAgent(),
                new RequestExpectContinue()});
        
        HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
        
        HttpContext context = new BasicHttpContext(null);
        HttpHost host = new HttpHost(request.getHost(), 80);

        DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
        ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

        try {
            
            String[] targets = {request.getUrl()};
            
            for (int i = 0; i < targets.length; i++) {
                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket, params);
                }
                BasicHttpRequest httpRequest = new BasicHttpRequest("GET", targets[i]);
                System.out.println(">> Request URI: " + httpRequest.getRequestLine().getUri());
                
                httpRequest.setParams(params);
                httpexecutor.preProcess(httpRequest, httpproc, context);
                HttpResponse response = httpexecutor.execute(httpRequest, conn, context);
                response.setParams(params);
                httpexecutor.postProcess(response, httpproc, context);
                System.out.println("<< Response: " + response.getStatusLine());
                request.addResponse(response.getStatusLine().getStatusCode(), new Hashtable<String, String>(), EntityUtils.toString(response.getEntity()));
                //System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println("==============");
                if (!connStrategy.keepAlive(response, context)) {
                    conn.close();
                } else {
                    System.out.println("Connection kept alive...");
                }
            }
        }   catch (UnknownHostException ex) {
                Logger.getLogger(Apache.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Apache.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            conn.close();
        }
    } catch(Exception e) {
        
    }      
        return this;
    }
    
    private HttpUriRequest getHttpMethod(String method, String url, String body)
    {
        System.out.println("getHttpMethod method: ");
        HttpUriRequest httpMethod = new HttpGet(url);
//        if(method.compareTo("post") == 0){
//            HttpPost post = new HttpPost(url);
//            System.out.println("httpPost: " + post.toString());
//            
//            try {
//                post.setEntity(new StringEntity(body));
///                httpMethod = (HttpUriRequest)post;
//                httpMethod.setHeader("Content-Type", "application/json");
//            } catch( Exception e){
//                System.out.println("getHttpMethod error: " + e.getStackTrace());
//            }
//        }
        System.out.println("httpMethod: " + httpMethod.toString());
        return httpMethod;
    }    
}
