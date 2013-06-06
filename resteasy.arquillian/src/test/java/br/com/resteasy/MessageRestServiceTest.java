package br.com.resteasy;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

public class MessageRestServiceTest {
	
	@Deployment
	   public static Archive<?> createTestArchive()
	   {
	      WebArchive war = ShrinkWrap.create(WebArchive.class, "RESTEASY-767.war")
	            .addClasses(MessageRestService.class)
	            .addAsWebInfResource("web.xml");
	      System.out.println(war.toString(true));
	      return war;
	   }

	@Test
	public void testPrintMessage() throws Exception {
		ClientRequest request = new ClientRequest("http://localhost:9090/RESTEASY-767/rest/message/nodelay");
		 ClientResponse<?> response = request.get();
	      System.out.println("Status: " + response.getStatus());
	      assertEquals(200, response.getStatus());
	}

}
