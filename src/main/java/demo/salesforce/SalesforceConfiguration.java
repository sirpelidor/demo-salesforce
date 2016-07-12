package demo.salesforce;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:salesforce.properties")
public class SalesforceConfiguration {
    private Environment environment;

    @Bean(name = "loginSalesforceApi")
    public EnterpriseConnection loginSalesforceApi() throws ConnectionException {
        String username = environment.getProperty("salesforce.username");
        String password = environment.getProperty("salesforce.password");
        return Connector.newConnection(username, password);
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
