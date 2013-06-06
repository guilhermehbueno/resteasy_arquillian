package br.com.resteasy.test;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.resteasy.MessageRestService;
import br.com.resteasy.MessageRestServiceInterface;

import com.acme.jaxrs.rs.JaxRsActivator;

@RunWith(Arquillian.class)
@RunAsClient
public class MessageRestServiceTest {
	
	private static final String RESOURCE_PREFIX = "rest";

    @Deployment(name = "rest", testable=false)
	public static WebArchive createDeployment() {
    	WebArchive war
    	 = ShrinkWrap.create(WebArchive.class,"test.war")
                 .addPackage(MessageRestService.class.getPackage())
                 .addPackage(JaxRsActivator.class.getPackage())
                 .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    	 System.out.println(war.toString(true));
    	 return war;
	}

    @ArquillianResource
    @OperateOnDeployment("rest")
    URL deploymentUrl;


	@Test
	public void testLog() throws Exception {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        String url = deploymentUrl.toString() + RESOURCE_PREFIX;
        System.out.println(url.toString());
        final MessageRestServiceInterface client = ProxyFactory.create(MessageRestServiceInterface.class, url.toString());
        String result = client.printMessage("teste");
        Assert.assertNotNull(result);
        System.out.println(result);
    }
}
