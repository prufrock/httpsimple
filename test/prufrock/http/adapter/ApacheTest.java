/*
 * A test for the apache adapter
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
        System.out.println("setUp");
        Apache instance = new Apache();
        
        request.setHost("www.iana.org");
        request.setUrl("/domains/example/index.html");
        request.setMethod(Request.HTTP_GET);
        request.getMethod();
        request.setHeaders(new Hashtable<String, String>());
        request.setBody("");
        instance.transmitRequest(request);
    }

    @After
    public void tearDown() 
    {
        System.out.println("tearDown");
        this.request = new Request();
    }

    /**
     * Test of getBody on the response set by the Apache object.
     */
    @Test
    public void testGetBody() 
    {
        System.out.println("testGetBody");
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
        System.out.println("testGetStatusCode");
        int expStatusCode     = 200;
        
        assertEquals(expStatusCode
                   , this.request.getResponse().getStatusCode());
    }
    
    @Test
    public void testGetHeader()
    {
        System.out.println("testGetHeader");
        String expContentType = "text/html; charset=UTF-8";
        
        assertEquals( expContentType
                  , this.request.getResponse().getHeaders().get(
                    prufrock.http.response.Header.CONTENTTYPE)
                   );
    }
}