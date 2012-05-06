/*
 * An adapter for the org.apache.http library.
 */
package prufrock.http.adapter;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
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
    public static final String CONTENTCHARSET = "UTF-8";
    public static final String USERAGENT      = "HttpComponents/1.1";
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
            HttpProtocolParams.setContentCharset(params, Apache.CONTENTCHARSET);
            HttpProtocolParams.setUserAgent(params, Apache.USERAGENT);
            HttpProtocolParams.setUseExpectContinue(params, true);

            HttpProcessor httpproc = new ImmutableHttpProcessor(
              new HttpRequestInterceptor[] {
                // Required protocol interceptors
                  new RequestContent()
                , new RequestTargetHost()
                // Recommended protocol interceptors
                , new RequestConnControl()
                , new RequestUserAgent()
                , new RequestExpectContinue()
                }
            );
        
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
                BasicHttpRequest httpRequest = new BasicHttpRequest(request.getMethod(), targets[i]);
                
                httpRequest.setParams(params);
                httpexecutor.preProcess(httpRequest, httpproc, context);
                HttpResponse response = httpexecutor.execute(httpRequest, conn, context);
                response.setParams(params);
                httpexecutor.postProcess(response, httpproc, context);
                
                request.addResponse(response.getStatusLine().getStatusCode()
                                   , this.processHeaders(response.getAllHeaders())
                                   , EntityUtils.toString(response.getEntity())
                                   );

                if (!connStrategy.keepAlive(response, context)) {
                    conn.close();
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
    
    private Hashtable<String, String> processHeaders(org.apache.http.Header[] headers)
    {
        Hashtable processedHeaders = new Hashtable<String, String>();

        for(Header header : headers){
            processedHeaders.put( header.getName()
                                , header.getValue());
        }

        return processedHeaders;
    }
}