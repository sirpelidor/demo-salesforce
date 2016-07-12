package demo.salesforce;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SalesforceConfiguration.class})
public class SalesforceConfigurationTest {

    @Autowired
    private EnterpriseConnection loginSalesforceApi;

    @Test
    public void testLogin() {
        assertNotNull(loginSalesforceApi.getConfig().getSessionId());

        try {
            loginSalesforceApi.logout();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }

    }
}