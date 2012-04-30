/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prufrock.http;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import prufrock.http.adapter.Testable;

/**
 *
 * @author david
 */
public class ClientTest {
    
    public ClientTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeHttpRequest method, of class Client.
     */
    @Test
    public void testMakeHttpRequest() {
        System.out.println("makeHttpRequest");
        Request request = new Request();
        Client instance = new Client(new Testable());
        Request expResult = request;
        int expStatusCode = 200;
        String expContentLength = "1162";
        String expBody = "OK";
        Request result = instance.makeHttpRequest(request);
        
        assertEquals(expResult, result);
        assertEquals(expStatusCode, result.getResponse().getStatusCode());
        assertEquals(expContentLength
            , result.getResponse().getResponseHeaders().get("Content-Length"));
    }

    /**
     * Test of getRequests method, of class Client.
     */
    @Test
    public void testGetRequests() {
        System.out.println("getRequests");
        Client instance = new Client();
        int expResult = 0;
        List result = instance.getRequests();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of resetRequests method, of class Client.
     */
    @Test
    public void testResetRequests() {
        System.out.println("resetRequests");
        Client instance = new Client();
        int expResult = 0;
        instance.resetRequests();
        List<Request> result = instance.getRequests();
        assertEquals(expResult, result.size());
    }
}
