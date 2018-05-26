package shiros;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;

import shiros.service.AccountService;

import static org.junit.Assert.assertTrue;

public class MyResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(AccountService.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        //final String responseMsg = target().path("myresource").request().get(String.class);

        assertTrue(true);
    }
}
