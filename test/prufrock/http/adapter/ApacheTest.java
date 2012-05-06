/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prufrock.http.adapter;

import prufrock.http.Request;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Hashtable;

/**
 *
 * @author david
 */
public class ApacheTest {

    private Request request;
    public ApacheTest() 
    {
        this.request = new Request();
    }

    @BeforeClass
    public static void setUpClass() throws Exception 
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception 
    {
    }

    @Before
    public void setUp() 
    {
        System.out.println("transmitRequest");
        Apache instance = new Apache();
        
        request.setHost("www.iana.org");
        request.setUrl("/domains/example/index.html");
        request.setMethod(Request.HTTP_GET);
        request.getMethod();
        request.setHeaders(new Hashtable<String, String>());
        request.setBody("");
        Apache result = instance.transmitRequest(request);
    }

    @After
    public void tearDown() 
    {
        
    }

    /**
     * Test of getBody on the response set by the Apache object.
     */
    @Test
    public void testGetBody() 
    {
        
        int expResponseLength = 2966;
        
        assertEquals( expResponseLength
                  , this.request.getResponse().getBody().length());;
    }
    
    /**
     * Test of getStatusCode on the response set by the Apache object.
     */
    @Test
    public void testGetStatusCode() 
    {
        
        int expStatusCode     = 200;
        
        assertEquals(expStatusCode
                   , this.request.getResponse().getStatusCode());
    }    
}
