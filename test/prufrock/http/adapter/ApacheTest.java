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

    public ApacheTest() {
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
     * Test of transmitRequest method, of class Apache.
     */
    @Test
    public void testTransmitRequest() {
        System.out.println("transmitRequest");
        Apache instance = new Apache();
        Apache expResult = instance;
        int expResponseLength = 2966;
        Request request = new Request();
        request.setHost("www.iana.org");
        request.setUrl("/domains/example/index.html");
        request.setMethod(Request.HTTP_GET);
        request.getMethod();
        request.setHeaders(new Hashtable<String, String>());
        request.setBody("");
        Apache result = instance.transmitRequest(request);

        assertEquals(expResult, result);
        assertEquals(expResponseLength, request.getBody().length());
    }
}
